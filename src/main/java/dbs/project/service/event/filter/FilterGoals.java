package dbs.project.service.event.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.GoalEvent;
import dbs.project.util.Filter;

public class FilterGoals implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof GoalEvent) ? true : false;
	}

}
