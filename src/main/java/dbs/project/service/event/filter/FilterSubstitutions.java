package dbs.project.service.event.filter;

import dbs.project.entity.EventSubstitution;
import dbs.project.entity.MatchEvent;
import dbs.project.util.Filter;

public class FilterSubstitutions implements Filter<MatchEvent>{

	public boolean apply(MatchEvent type) {
		return (type.getClass() == EventSubstitution.class) ? true : false;
	}
}
