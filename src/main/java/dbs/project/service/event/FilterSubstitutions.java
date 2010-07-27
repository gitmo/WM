package dbs.project.service.event;

import dbs.project.entity.EventSubstitution;
import dbs.project.entity.MatchEvent;

public class FilterSubstitutions implements Filter<MatchEvent>{

	public boolean apply(MatchEvent type) {
		return (type.getClass() == EventSubstitution.class) ? true : false;
	}
}
