package dbs.project.dao;

import java.util.List;
import dbs.project.entity.Tournament;

public class TournamentDao extends DaoBase {
	
	/**
	 * Creates or updates a tournament
	 * @param tournament
	 */
	public static void save(Tournament tournament) {
		session.beginTransaction();
		session.save(tournament);
		session.getTransaction().commit();
	}
	
	/**
	 * Deletes a tournament
	 * @param tournament
	 */
	public static void delete(Tournament tournament) {
		session.beginTransaction();
		session.delete(tournament);
		session.getTransaction().commit();
	}
	
	/**
	 * Tries to find a tournament
	 * @param name
	 * @return
	 */
	public static Tournament find(String name) {
		return (Tournament) session.load(Tournament.class, name);
	}
	
	/**
	 * Fetches all tournament entries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Tournament> fetchAll() {
		return (List<Tournament>) session.createQuery("From Tournament").list();
	}
	
}
