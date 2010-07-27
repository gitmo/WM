package dbs.project.service.event.filter;

import java.util.List;

import dbs.project.entity.EventGoal;
import dbs.project.entity.MatchEvent;
import dbs.project.entity.Team;
import dbs.project.util.*;

public class FilterOwnGoals implements Filter<MatchEvent> {

	public boolean apply(MatchEvent goal) {
		if(!new FilterGoals().apply(goal))
			return false;
		
		List<Team> teams = goal.getInvolvedPlayer().getTeams();
		
		return teams.contains(((EventGoal) goal).getScorringTeam()) ? true : false;
	}

}
