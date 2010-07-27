package dbs.project.service;

import java.util.List;

import dbs.project.dao.MatchDao;
import dbs.project.entity.EventGoal;
import dbs.project.entity.EventSubstitution;
import dbs.project.entity.Match;
import dbs.project.entity.MatchEvent;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.exception.NewPlayerHasPlayedBefore;
import dbs.project.exception.NotInSameTeam;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.exception.PlayersTeamNotInMatch;
import dbs.project.exception.TeamLineUpComplete;
import dbs.project.exception.TiedMatch;
import dbs.project.service.event.filter.FilterGoals;
import dbs.project.service.event.filter.FilterOwnGoals;
import dbs.project.util.Collections;
import dbs.project.util.Tuple;


public class MatchService {
	
	/**
	 * Substitutes player1 with player2
	 * @param out 
	 * @param in 
	 * @param minute
	 * @throws NewPlayerHasPlayedBefore 
	 * @throws PlayerDoesNotPlay 
	 * @throws NotInSameTeam 
	 */
	public static void substitutePlayers(Player out, Player in, Integer minute, Match match) throws NewPlayerHasPlayedBefore, PlayerDoesNotPlay, NotInSameTeam{
		
		if(PlayerService.playerHasPlayed(in, match))
			throw new NewPlayerHasPlayedBefore();
		
		if(!PlayerService.playerHasPlayed(out, match))
			throw new PlayerDoesNotPlay();
		
		boolean sameTeam = false;
		for(Team t : out.getTeams()){
			if(in.getTeams().contains(t)){
				sameTeam = true;
			}
			
		}
		if(sameTeam)
			throw new NotInSameTeam();
		
		EventSubstitution substitution = new EventSubstitution(out, in, minute);
		match.addEvent(substitution);
		
		MatchDao.save(match);
	}
	
	
	
	
	/**
	 * inserts Player to Match(the LineUp)
	 * @param player
	 * @param match
	 * @throws PlayersTeamNotInMatch
	 * @throws TeamLineUpComplete
	 */
	public static void insertPlayerToMatch(Player player, Match match) throws PlayersTeamNotInMatch, TeamLineUpComplete {
		if(player.getTeams().contains(match.getGuestTeam())){
			if(match.getGuestLineup().size() < 11)
				match.getGuestLineup().add(player);
			else throw new TeamLineUpComplete();
		}
		else if (player.getTeams().contains(match.getHostTeam())){
			if(match.getHostLineup().size() < 11)
				match.getHostLineup().add(player);
			else throw new TeamLineUpComplete();
		}
		else
			throw new PlayersTeamNotInMatch();
				
	}
	
	/**
	 * returns a String with the names of the Teams and the result
	 * style: Host - Guest 0 : 0
	 * @param match
	 * @return
	 */
	public static String getResult(Match match) {
		Tuple<Integer> goals = getGoalsByTeam(match.getHostTeam(), match);
		return match.getHostTeam().getName() + " - "+ match.getGuestTeam().getName() + " " + goals.getFirst() + " : " + goals.getSecond() + " " ;
	}
	
	/**
	 * Insert Goal into match
	 * @param goal
	 * @param player
	 * @param match
	 * @throws PlayerDoesNotPlay
	 */
	public static void insertGoal(EventGoal goal, Player player, Match match) throws PlayerDoesNotPlay {
		if(!PlayerService.playerHasPlayed(player, match))
			throw new PlayerDoesNotPlay();
		
		goal.setInvolvedPlayer(player);
		match.addEvent(goal);
		MatchDao.save(match);
	}

	/**
	 * Get the points a team receives in a match
	 * @param team
	 * @param match
	 * @return
	 */
	public static int getPointsByTeam(Team team, Match match) {
		if(!match.isPlayed())
			return 0;
		
		Tuple<Integer> goals = getGoalsByTeam(team, match);
		if(goals.getFirst() > goals.getSecond())
			return 3;
		else if(goals.getFirst() == goals.getSecond())
			return 1;
		else
			return 0;
	}

	
	/**
	 * get the goals for a team in a tuple
	 * (ownGoals,enemyGoals)
	 * @param team
	 * @param match
	 * @return
	 */
	public static Tuple<Integer> getGoalsByTeam(Team team, Match match) {
		Tuple<Integer> goals = new Tuple<Integer>();
		int goalsScored, goalsAgainst;
		List<MatchEvent> goalEvents = Collections.filter(match.getEvents(), new FilterGoals());
		if(match.getHostTeam() == team) {
			List<MatchEvent> realGoals = Collections.filter(goalEvents, new FilterOwnGoals());
			goalsScored = realGoals.size();
			goalsAgainst = goalEvents.size();
		} else {
			goalsScored = goalEvents.size();
			goalsAgainst = goalEvents.size();
		}
		
		goals.setFirst(goalsScored);
		goals.setSecond(goalsAgainst);
		
		return goals;
	}


	public static Team getWinner(Match match) throws TiedMatch {
		if(MatchService.isTied(match))
			throw new TiedMatch();
		
		if(getPointsByTeam(match.getHostTeam(), match) == 3)
			return match.getHostTeam();
		
		return match.getGuestTeam();
	}
	
	public static Team getLooser(Match match) throws TiedMatch {
		if(MatchService.isTied(match))
			throw new TiedMatch();
		
		if(getPointsByTeam(match.getHostTeam(), match) == 3)
			return match.getGuestTeam();
		
		return match.getHostTeam();
	}


	private static boolean isTied(Match match) {
		return getPointsByTeam(match.getHostTeam(), match) == 0 ? true : false;
	}
}
