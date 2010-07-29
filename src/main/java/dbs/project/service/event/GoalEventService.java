package dbs.project.service.event;

import java.util.List;

import dbs.project.dao.event.GoalEventDao;
import dbs.project.entity.Match;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.util.Tuple;

public class GoalEventService {

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
