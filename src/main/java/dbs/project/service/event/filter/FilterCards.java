package dbs.project.service.event.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.CardEvent;
import dbs.project.util.Filter;

public class FilterCards implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof CardEvent) ? true : false;
	}

}
