package dbs.project.dao.event;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.dao.DaoBase;
import dbs.project.entity.Match;
import dbs.project.entity.event.MatchEndEvent;

public class MatchEndEventDao extends DaoBase {

	@SuppressWarnings("unchecked")
	public static List<MatchEndEvent> findAllByMatch(Match match) {
		return (List<MatchEndEvent>) session.createCriteria(MatchEndEvent.class).add(Restrictions.eq("match", match)).list();
	}

}
