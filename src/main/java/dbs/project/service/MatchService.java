package dbs.project.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dbs.project.collections.filter.FilterLineUpEvent;
import dbs.project.collections.filter.FilterMatchEndEvent;
import dbs.project.dao.MatchDao;
import dbs.project.entity.GroupMatch;
import dbs.project.entity.Match;
import dbs.project.entity.MatchEvent;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.TournamentGroup;
import dbs.project.entity.event.MatchEndEvent;
import dbs.project.entity.event.player.CardEvent;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.entity.event.player.LineUpEvent;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.exception.NewPlayerHasPlayedBefore;
import dbs.project.exception.NoMatchWhistleEvent;
import dbs.project.exception.NoMinuteSet;
import dbs.project.exception.NotInSameTeam;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.exception.PlayerDoesNotPlayForTeam;
import dbs.project.exception.PlayersTeamNotInMatch;
import dbs.project.exception.TeamLineUpComplete;
import dbs.project.exception.TiedMatch;
import dbs.project.service.event.GoalEventService;
import dbs.project.service.event.LineUpEventService;
import dbs.project.service.event.SubstitutionEventService;
import dbs.project.util.Collections;
import dbs.project.util.MatchMinute;
import dbs.project.util.Tuple;

public class MatchService {

	/**
	 * Substitutes player1 with player2
	 * 
	 * @param out
	 * @param in
	 * @param minute
	 * @throws NewPlayerHasPlayedBefore
	 * @throws PlayerDoesNotPlay
	 * @throws NotInSameTeam
	 */
	public static void substitutePlayers(Player out, Player in, Integer minute,
			Match match) throws NewPlayerHasPlayedBefore, PlayerDoesNotPlay,
			NotInSameTeam {

		if (PlayerService.playerHasPlayed(in, match))
			throw new NewPlayerHasPlayedBefore();

		if (!PlayerService.playerHasPlayed(out, match))
			throw new PlayerDoesNotPlay();

		boolean sameTeam = false;
		for (Team t : out.getTeams()) {
			if (in.getTeams().contains(t)) {
				sameTeam = true;
			}

		}
		if (sameTeam)
			throw new NotInSameTeam();

		SubstitutionEvent substitution = new SubstitutionEvent(match, minute,
				out, in);
		match.addEvent(substitution);

		MatchDao.save(match);
	}

	/**
	 * inserts Player to Match(the LineUp)
	 * 
	 * @param player
	 * @param match
	 * @throws TeamLineUpComplete
	 * @throws PlayersTeamNotInMatch
	 */
	public static void insertPlayerToMatch(Player player, Match match)
			throws PlayerDoesNotPlayForTeam, TeamLineUpComplete {
		Team team = null;
		if (match.getHostTeam().getPlayers().contains(player))
			team = match.getHostTeam();
		else if (match.getGuestTeam().getPlayers().contains(player))
			team = match.getGuestTeam();
		else
			throw new PlayerDoesNotPlayForTeam();

		if (getLineupSize(team, match) > 11)
			throw new TeamLineUpComplete();

		MatchEvent event = new LineUpEvent(match, player, team);
		match.addEvent(event);
		MatchDao.save(match);
	}

	private static int getLineupSize(Team team, Match match) {
		return getLineupForTeam(team, match).size();
	}

	/**
	 * returns a String with the names of the Teams and the result style: Host -
	 * Guest 0 : 0
	 * 
	 * @param match
	 * @return
	 */
	public static String getResult(Match match) {
		Tuple<Integer, Integer> goals = getGoalsByTeam(match.getHostTeam(),
				match);

		return match.getHostTeam().getName() + " - "
				+ match.getGuestTeam().getName() + " " + goals.getFirst()
				+ " : " + goals.getSecond() + " ";
	}

	/**
	 * Insert Goal into match
	 * 
	 * @param goal
	 * @param player
	 * @param match
	 * @throws PlayerDoesNotPlay
	 */
	public static void insertGoal(GoalEvent goal, Player player, Match match)
			throws PlayerDoesNotPlay, NoMinuteSet {

		if (goal.getMinute() == null)
			throw new NoMinuteSet();

		goal.setInvolvedPlayer(player);
		goal.setMatch(match);
		match.addEvent(goal);

		MatchDao.save(match);
	}

	/**
	 * Get the points a team receives in a match
	 * 
	 * @param team
	 * @param match
	 * @return
	 */
	public static int getPointsByTeam(Team team, Match match) {
		if (!match.isPlayed())
			return 0;

		Tuple<Integer, Integer> goals = getGoalsByTeam(team, match);
		if (goals.getFirst() > goals.getSecond())
			return 3;
		else if (goals.getFirst() == goals.getSecond())
			return 1;
		else
			return 0;
	}

	/**
	 * get the goals for a team in a tuple (ownGoals,enemyGoals)
	 * 
	 * @param team
	 * @param match
	 * @return
	 */
	public static Tuple<Integer, Integer> getGoalsByTeam(Team team, Match match) {
		// Tuple<Integer, Integer> goals = new Tuple<Integer, Integer>();
		// int goalsScored, goalsAgainst;

		// List<MatchEvent> goalEvents = Collections.filter(match.getEvents(),
		// new FilterGoalEvent());
		//
		// List<MatchEvent> realGoals = Collections.filter(goalEvents,
		// new FilterEventsByOwnGoal(team));
		// goalsScored = realGoals.size();
		// goalsAgainst = goalEvents.size() - goalsScored;

		Tuple<Integer, Integer> goals = GoalEventService
				.getGoalsForMatchByTeam(team, match);

		goals.setFirst(goals.getFirst());
		goals.setSecond(goals.getSecond());

		return goals;
	}

	public static Team getWinner(Match match) throws TiedMatch {
		if (MatchService.isTied(match))
			throw new TiedMatch();

		if (getPointsByTeam(match.getHostTeam(), match) == 3)
			return match.getHostTeam();

		return match.getGuestTeam();
	}

	public static Team getLooser(Match match) throws TiedMatch {
		if (MatchService.isTied(match))
			throw new TiedMatch();

		if (getPointsByTeam(match.getHostTeam(), match) == 3)
			return match.getGuestTeam();

		return match.getHostTeam();
	}

	private static boolean isTied(Match match) {
		return getPointsByTeam(match.getHostTeam(), match) == 0 ? true : false;
	}

	public static MatchMinute getFinalWhistleTime(Match match)
			throws NoMatchWhistleEvent {

		ArrayList<MatchEndEvent> end = new ArrayList<MatchEndEvent>();
		Collections.filterAndChangeType(match.getEvents(),
				new FilterMatchEndEvent(), end);

		if (end.size() < 1)
			throw new NoMatchWhistleEvent();

		MatchEndEvent finalWhislte = end.get(end.size() - 1);
		return finalWhislte.getMinute();
	}

	public static List<Player> getHostLineup(Match match) {
		return getLineupForTeam(match.getHostTeam(), match);
	}

	public static List<Player> getGuestLineup(Match match) {
		return getLineupForTeam(match.getGuestTeam(), match);
	}

	public static List<Player> getLineup(Match match) {
		List<LineUpEvent> lineupEvents = new LinkedList<LineUpEvent>();
		Collections.filterAndChangeType(match.getEvents(),
				new FilterLineUpEvent(), lineupEvents);

		List<Player> players = new LinkedList<Player>();
		for (LineUpEvent event : lineupEvents) {
			players.add(event.getInvolvedPlayer());
		}
		return players;
	}

	public static List<Player> getLineupForTeam(Team team, Match match) {
		return LineUpEventService.getPlayersByMatchForTeam(match, team);
	}

	public static List<Player> getLineupByMatch(Match match) {
		return LineUpEventService.getPlayersByMatch(match);
	}

	public static void setLineup(List<Player> players, Match match)
			throws PlayerDoesNotPlayForTeam, TeamLineUpComplete {
		for (Player player : players) {
			insertPlayerToMatch(player, match);
		}
	}

	public static void addCard(int minute, Player affectedPlayer, String color,
			Match match) {

		CardEvent card = new CardEvent(match, minute, affectedPlayer, color);

		match.addEvent(card);
		MatchDao.save(match);
	}

	public static List<Tuple<Player, Player>> getSubstitutedPlayersByTeam(
			Team team, Match match) {
		// List<MatchEvent> teamEvents = new LinkedList<MatchEvent>();
		// teamEvents = Collections.filter(match.getEvents(),
		// new FilterEventsByTeam(team));
		//
		// List<SubstitutionEvent> substitutionEvents = new
		// LinkedList<SubstitutionEvent>();
		// Collections.filterAndChangeType(teamEvents,
		// new FilterSubstitutionEvent(), substitutionEvents);
		//
		// List<Tuple<Player, Player>> players = new LinkedList<Tuple<Player,
		// Player>>();
		// for (SubstitutionEvent event : substitutionEvents)
		// players.add(new Tuple<Player, Player>(event.getInvolvedPlayer(),
		// event.getNewPlayer()));
		//
		// System.out.println("getSubstitutedPlayersByTeam: " + players.size());
		return SubstitutionEventService.getSubstituedPlayersByTeam(team, match);
	}

	public static void setFinalWhistle(int i, Match match) {
		MatchEndEvent end = new MatchEndEvent(match, 90);
		match.addEvent(end);
		match.setPlayed(true);
		if (match instanceof GroupMatch) {
			TournamentGroup group = ((GroupMatch) match).getGroup();
			if (GroupService.areAllMatchesPlayed(group))
				KnockoutMatchService.updatesKnockoutTree(group);
		}

		MatchDao.save(match);
	}

	public static void getPlayingPlayersForMatch(Match match) {
		List<Player> players = getLineupByMatch(match);
		int i = -1;
		for (Tuple<Player, Player> substitution : SubstitutionEventService
				.getSubstitutedPlayersForMatch(match)) {
			if ((i = players.indexOf(substitution.getFirst())) >= 0) {
				players.remove(i);
				players.add(substitution.getSecond());
			}
		}

	}
}
