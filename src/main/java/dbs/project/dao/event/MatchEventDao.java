package dbs.project.dao.event;

import java.util.List;

import dbs.project.dao.DaoBase;
import dbs.project.entity.MatchEvent;

public class MatchEventDao extends DaoBase {

	/**
	 * returns all MatchEvents
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<MatchEvent> fetchAll() {
		return (List<MatchEvent>) session.createQuery("From MatchEvent").list();
	}

}
