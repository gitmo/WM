package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.Team;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.util.collections.Filter;

public class FilterEventsByOwnGoal implements Filter<MatchEvent> {
	protected Team team;

	public FilterEventsByOwnGoal(Team team) {
		this.team = team;
	}

	public boolean apply(MatchEvent goal) {
		if (!new FilterGoalEvent().apply(goal))
			return false;

		return team == ((GoalEvent) goal).getScorringTeam() ? true : false;
	}

}
