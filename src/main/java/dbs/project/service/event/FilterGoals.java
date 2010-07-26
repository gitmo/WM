package dbs.project.service.event;

import dbs.project.entity.EventGoal;
import dbs.project.entity.MatchEvent;

public class FilterGoals implements Filter<MatchEvent>{

	public boolean apply(MatchEvent type) {
		return (type.getClass() == EventGoal.class) ? true : false;
	}

}
