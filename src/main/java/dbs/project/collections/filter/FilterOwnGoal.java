package dbs.project.collections.filter;

import java.util.List;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.util.Filter;

public class FilterOwnGoal implements Filter<MatchEvent> {

	public boolean apply(MatchEvent goal) {
		if (!new FilterGoal().apply(goal))
			return false;

		List<Team> teams = ((GoalEvent) goal).getInvolvedPlayer().getTeams();

		return teams.contains(((GoalEvent) goal).getScorringTeam()) ? true
				: false;
	}

}
