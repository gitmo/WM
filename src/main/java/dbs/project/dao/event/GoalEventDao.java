package dbs.project.dao.event;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.dao.DaoBase;
import dbs.project.entity.Match;
import dbs.project.entity.event.player.GoalEvent;

public class GoalEventDao extends DaoBase {

	@SuppressWarnings("unchecked")
	public static List<GoalEvent> findAllByMatch(Match match) {
		return session.createCriteria(GoalEvent.class)
				.add(Restrictions.eq("match", match)).list();
	}

}
