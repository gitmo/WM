package dbs.project.dao;

import java.util.List;

import dbs.project.entity.MatchEvent;

public class MatchEventDao extends DaoBase {
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<MatchEvent> fetchAll(){
		return (List<MatchEvent>) session.createQuery("From MatchEvent").list();
	}
	
}
