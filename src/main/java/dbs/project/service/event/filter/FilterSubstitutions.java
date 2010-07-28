package dbs.project.service.event.filter;

import dbs.project.entity.MatchEvent;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.util.Filter;

public class FilterSubstitutions implements Filter<MatchEvent> {

	public boolean apply(MatchEvent event) {
		return (event instanceof SubstitutionEvent) ? true : false;
	}
}
