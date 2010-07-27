package dbs.project.service.event.filter;

import dbs.project.entity.EventCard;
import dbs.project.entity.MatchEvent;
import dbs.project.util.Filter;

public class FilterCards implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof EventCard) ? true : false;
	}

}
