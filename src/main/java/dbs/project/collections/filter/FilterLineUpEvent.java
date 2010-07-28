package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.LineUpEvent;
import dbs.project.util.collections.Filter;

public class FilterLineUpEvent implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof LineUpEvent) ? true : false;
	}
}
