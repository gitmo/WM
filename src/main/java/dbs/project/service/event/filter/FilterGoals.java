package dbs.project.service.event.filter;

import dbs.project.entity.EventGoal;
import dbs.project.entity.MatchEvent;
import dbs.project.util.*;

public class FilterGoals implements Filter<MatchEvent>{

	public boolean apply(MatchEvent type) {
		return (type.getClass() == EventGoal.class) ? true : false;
	}

}