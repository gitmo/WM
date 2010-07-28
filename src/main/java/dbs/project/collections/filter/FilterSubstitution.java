package dbs.project.collections.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.util.collections.Filter;

public class FilterSubstitution implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof SubstitutionEvent) ? true : false;
	}
}
