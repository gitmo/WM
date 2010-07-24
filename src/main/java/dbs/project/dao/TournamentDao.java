package dbs.project.dao;

import java.util.List;
import dbs.project.entity.Tournament;

public class TournamentDao extends DaoBase {
	
	/**
	 * Creates or updates a tournament
	 * @param tournament
	 */
	public void save(Tournament tournament) {
		session.save(tournament);
	}
	
	/**
	 * Deletes a tournament
	 * @param tournament
	 */
	public void delete(Tournament tournament) {
		session.delete(tournament);
	}
	
	/**
	 * Tries to find a tournament
	 * @param name
	 * @return
	 */
	public Tournament find(String name) {
		return (Tournament) session.load(Tournament.class, name);
	}
	
	/**
	 * Fetches all tournament entries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tournament> fetchAll() {
		return (List<Tournament>) session.createQuery("From Tournament").list();
	}
	
}
