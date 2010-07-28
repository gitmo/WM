package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.LineUpEvent;
import dbs.project.util.Filter;

public class FilterLineUp implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof LineUpEvent) ? true : false;
	}
}
