package dbs.project.dao;

import java.util.List;

import dbs.project.entity.Match;

public class MatchDao extends DaoBase {
	
	/**
	 * Creates or updates a match
	 * @param match
	 */
	public static void save(Match team) {
		session.beginTransaction();
		session.saveOrUpdate(team);
//		session.flush();
		session.getTransaction().commit();
	}
	
	public static void saveAll(List<Match> matches) {
		for(Match match : matches)
			save(match);
	}
	
	/**
	 * Deletes a match
	 * @param match
	 */
	public static void delete(Match match) {
		session.beginTransaction();
		session.delete(match);
		session.getTransaction().commit();
	}
	
	/**
	 * Tries to find a match
	 * @param name
	 * @return
	 */
	public static Match find(String name) {
		return (Match) session.load(Match.class, name);
	}
	
	/**
	 * Fetches all match entries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Match> fetchAll() {
		return (List<Match>) session.createQuery("From Match").list();
	}
	
}
