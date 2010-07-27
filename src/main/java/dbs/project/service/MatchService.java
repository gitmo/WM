package dbs.project.service;

import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;

import dbs.project.dao.MatchDao;
import dbs.project.entity.EventGoal;
import dbs.project.entity.Match;
import dbs.project.entity.MatchEvent;
import dbs.project.entity.Player;
import dbs.project.entity.Team;
import dbs.project.exception.PlayerDoesNotPlay;
import dbs.project.service.event.filter.*;
import dbs.project.util.Collections;
import dbs.project.util.Tuple;


public class MatchService {
	
	public static void insertPlayerToMatch(Player player, Match match) {
		throw new NotYetImplementedException("insertGoal()");
	}
	
	public static String getResult(Match match) {
		throw new NotYetImplementedException("getResult()");
	}
	
	public static void insertGoal(EventGoal goal, Player player, Match match) throws PlayerDoesNotPlay {
		if(!PlayerService.playerHasPlayed(player, match))
			throw new PlayerDoesNotPlay();
		
		goal.setInvolvedPlayer(player);
		match.addEvent(goal);
		MatchDao.save(match);
	}

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
}
