package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.util.collections.Filter;

public class FilterGoal implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof GoalEvent) ? true : false;
	}

}
