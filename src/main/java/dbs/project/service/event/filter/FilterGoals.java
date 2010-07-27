package dbs.project.service.event.filter;

import dbs.project.entity.EventGoal;
import dbs.project.entity.MatchEvent;
import dbs.project.util.Filter;

public class FilterGoals implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof EventGoal) ? true : false;
	}

}
