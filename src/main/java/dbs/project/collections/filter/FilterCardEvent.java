package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.CardEvent;
import dbs.project.util.collections.Filter;

public class FilterCardEvent implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof CardEvent) ? true : false;
	}

}
