package dbs.project.service.event.filter;

import java.util.List;

import dbs.project.entity.EventGoal;
import dbs.project.entity.MatchEvent;
import dbs.project.entity.Team;
import dbs.project.util.Filter;

public class FilterOwnGoals implements Filter<MatchEvent> {

	public boolean apply(MatchEvent goal) {
		if(!new FilterGoals().apply(goal))
			return false;
		
		List<Team> teams = (List<Team>) goal.getInvolvedPlayer().getTeams().values();
		
		return teams.contains(((EventGoal) goal).getScorringTeam()) ? true : false;
	}

}
