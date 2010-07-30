package dbs.project.service;

import java.util.LinkedList;
import java.util.List;

import dbs.project.dao.PlayerDao;
import dbs.project.dao.event.GoalEventDao;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.util.MatchMinute;
import dbs.project.util.Substitution;
import dbs.project.util.Tuple;

public class TeamService {

	/**
	 * gets all playing players for a team in given match in a specified minute
	 * 
	 * @param match
	 * @param team
	 * @return
	 */
	public static List<Player> getPlayingPlayersInAMatchForTeam(Match match, Team team, MatchMinute minute) {
		List<Player> players = new LinkedList<Player>();
		for(Player player : MatchService.getPlayingPlayersForMatch(match, minute))
			if(player.getTeams().contains(team))
				players.add(player);
		
		return players;
	}

	/**
	 * gets all players which are on the bench
	 * 
	 * @param match
	 * @param team
	 * @return
	 */
	public static List<Player> getPlayersOnTheBench(Match match, Team team, MatchMinute minute) {
		List<Player> allPlayers = team.getPlayers();
		List<Player> playersWaiting = new LinkedList<Player>();
		
		for(Player player : getPlayingPlayersInAMatchForTeam(match, team , minute)) {
			if(!allPlayers.contains(player))
				playersWaiting.add(player);
		}
		
		return allPlayers;
	}
	
	
	/**
	 * gets all goals of a team in specified match
	 * 
	 * @param team
	 * @param match
	 * @return
	 */
	public static Tuple<Integer, Integer> getGoalsForMatchByTeam(Team team,
			Match match) {

		Tuple<Integer, Integer> goals = new Tuple<Integer, Integer>();
		goals.setFirst(0);
		goals.setSecond(0);

		List<GoalEvent> goalEvents = GoalEventDao.findAllByMatch(match);
		for (GoalEvent event : goalEvents) {
			if (event.getScorringTeam().equals(team))
				goals.setFirst(1 + goals.getFirst());
			else
				goals.setSecond(1 + goals.getSecond());
		}

		return goals;
	}

}
