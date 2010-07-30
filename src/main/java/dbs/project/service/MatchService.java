package dbs.project.service;

import java.util.LinkedList;
import java.util.List;

import dbs.project.dao.MatchDao;
import dbs.project.dao.event.LineUpEventDao;
import dbs.project.dao.event.MatchEndEventDao;
import dbs.project.dao.event.SubstitutionEventDao;
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
import dbs.project.exception.TeamNotSet;
import dbs.project.exception.TiedMatch;
import dbs.project.util.MatchMinute;
import dbs.project.util.Substitution;
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
	 * add player to lineup
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

	/**
	 * calculates the number of players of a team's lineup
	 * 
	 * @param team
	 * @param match
	 * @return
	 */
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
	 * @throws TeamNotSet 
	 * @throws PlayerDoesNotPlayForTeam 
	 */
	public static void insertGoal(GoalEvent goal, Player player, Match match)
			throws PlayerDoesNotPlay, NoMinuteSet, TeamNotSet, PlayerDoesNotPlayForTeam {

		if (goal.getMinute() == null)
			throw new NoMinuteSet();

		if(goal.getScorringTeam()==null)
			throw new TeamNotSet();
		
		List<Player> players = MatchService.getPlayingPlayersForMatch(match,goal.getMinute());
		if(!players.contains(player))
			throw new PlayerDoesNotPlay();
		
		if(!player.getTeams().contains(goal.getScorringTeam()))
			throw new PlayerDoesNotPlayForTeam();
		
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
		Tuple<Integer, Integer> goals = TeamService
				.getGoalsForMatchByTeam(team, match);

		goals.setFirst(goals.getFirst());
		goals.setSecond(goals.getSecond());

		return goals;
	}

	/**
	 * gives the winning team of a match
	 * 
	 * @param match
	 * @return
	 * @throws TiedMatch
	 */
	public static Team getWinner(Match match) throws TiedMatch {
		if (MatchService.isTied(match))
			throw new TiedMatch();

		if (getPointsByTeam(match.getHostTeam(), match) == 3)
			return match.getHostTeam();

		return match.getGuestTeam();
	}

	/**
	 * gives the looser team of a match
	 * 
	 * @param match
	 * @return
	 * @throws TiedMatch
	 */
	public static Team getLooser(Match match) throws TiedMatch {
		if (MatchService.isTied(match))
			throw new TiedMatch();

		if (getPointsByTeam(match.getHostTeam(), match) == 3)
			return match.getGuestTeam();

		return match.getHostTeam();
	}

	/**
	 * checks if the game is a draw
	 * 
	 * @param match
	 * @return
	 */
	private static boolean isTied(Match match) {
		return getPointsByTeam(match.getHostTeam(), match) == 0 ? true : false;
	}

	/**
	 * duration of a match
	 * 
	 * @param match
	 * @return
	 * @throws NoMatchWhistleEvent
	 */
	public static MatchMinute getFinalWhistleTime(Match match)
			throws NoMatchWhistleEvent {

		List<MatchEndEvent> end = getAllMatchEndEvents(match);

		if (end.size() < 1)
			throw new NoMatchWhistleEvent();

		MatchEndEvent finalWhislte = end.get(end.size() - 1);
		return finalWhislte.getMinute();
	}

	/**
	 * gets the lineup of the host team
	 * 
	 * @param match
	 * @return
	 */
	public static List<Player> getHostLineup(Match match) {
		return getLineupForTeam(match.getHostTeam(), match);
	}

	/**
	 * gets the line up of the guest team
	 * 
	 * @param match
	 * @return
	 */
	public static List<Player> getGuestLineup(Match match) {
		return getLineupForTeam(match.getGuestTeam(), match);
	}

//	public static List<Player> getLineup(Match match) {
//		List<LineUpEvent> lineupEvents = new LinkedList<LineUpEvent>();
//		Collections.filterAndChangeType(match.getEvents(),
//				new FilterLineUpEvent(), lineupEvents);
//
//		List<Player> players = new LinkedList<Player>();
//		for (LineUpEvent event : lineupEvents) {
//			players.add(event.getInvolvedPlayer());
//		}
//		return players;
//	}

	/**
	 * lineup of given team
	 * 
	 */
	public static List<Player> getLineupForTeam(Team team, Match match) {
		List<Player> players = new LinkedList<Player>();
		for (LineUpEvent event : LineUpEventDao.findByMatchForTeam(match, team))
			players.add(event.getInvolvedPlayer());

		return players;}
	
	/**
	 * lineup by a given match
	 * 
	 */
	public static List<Player> getLineupByMatch(Match match) {
		List<Player> players = new LinkedList<Player>();
		for (LineUpEvent event : LineUpEventDao.findByMatch(match))
			players.add(event.getInvolvedPlayer());

		return players;
	}

	/**
	 * inserts players to a lineup 
	 * 
	 * @param players
	 * @param match
	 * @throws PlayerDoesNotPlayForTeam
	 * @throws TeamLineUpComplete
	 */
	public static void setLineup(List<Player> players, Match match)
			throws PlayerDoesNotPlayForTeam, TeamLineUpComplete {
		for (Player player : players) {
			insertPlayerToMatch(player, match);
		}
	}

	/**
	 * persist a card for a player in a match
	 * 
	 * @param minute
	 * @param affectedPlayer
	 * @param color
	 * @param match
	 */
	public static void addCard(int minute, Player affectedPlayer, String color,
			Match match) {

		CardEvent card = new CardEvent(match, minute, affectedPlayer, color);

		match.addEvent(card);
		MatchDao.save(match);
	}


	/**
	 * ends a match and persist it
	 * 
	 * @param i
	 * @param match
	 */
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

	/**
	 * gets all playing players for match at a given time
	 * 
	 * @param match
	 */
	public static List<Player> getPlayingPlayersForMatch(Match match, MatchMinute minute) {
		List<Player> players = getLineupByMatch(match);
		int i = -1;
		for (Substitution substitution : getAllSubstitutionsForMatch(match)) {
			i = players.indexOf(substitution.getPlayerOut());
			if (i >= 0 && substitution.getMinute().compareTo(minute) < 1) {
				players.remove(i);
				players.add(substitution.getPlayerIn());
			}
		}
		
		return players;

	}
	
	/**
	 * gieves all match end events of a match
	 * 
	 * @param match
	 * @return
	 */
	public static List<MatchEndEvent> getAllMatchEndEvents(Match match) {
		return MatchEndEventDao.findAllByMatch(match);
	}
	
	
	public static List<Substitution> getSubstituedPlayersByTeamForMatch(
			Team team, Match match) {
		List<Substitution> substitutions = getAllSubstitutionsForMatch(match);
		for (Substitution substitution : substitutions)
			if (!substitution.getPlayerOut().getTeams().contains(team))
				substitutions.remove(substitution);

		return substitutions;
	}

	/**
	 * gets all substitutions for a specified match
	 * 
	 * @param match
	 * @return
	 */
	public static List<Substitution> getAllSubstitutionsForMatch(
			Match match) {

		List<SubstitutionEvent> events = SubstitutionEventDao
				.findAllByMatch(match);
		List<Substitution> substitutions = new LinkedList<Substitution>();

		for (SubstitutionEvent event : events) {
			Substitution substitution = new Substitution(event.getMinute(), event.getInvolvedPlayer(), event.getNewPlayer());
			substitutions.add(substitution);
		}

		return substitutions;
	}

	public static List<SubstitutionEvent> getSubstitutionEventsForMatch(
			Match match) {
		
		return SubstitutionEventDao.findAllByMatch(match);
	}
	
}
