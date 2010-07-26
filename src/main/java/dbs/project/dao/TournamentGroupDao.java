package dbs.project.dao;

import java.util.List;

import dbs.project.entity.TournamentGroup;

public class TournamentGroupDao extends DaoBase {
	
	/**
	 * Creates or updates a tournament group
	 * @param tournament
	 */
	public static void save(TournamentGroup tournament) {
		session.beginTransaction();
		session.saveOrUpdate(tournament);
		session.getTransaction().commit();
	}
	
	/**
	 * Deletes a tournament group
	 * @param tournamentGroup
	 */
	public static void delete(TournamentGroup tournamentGroup) {
		session.beginTransaction();
		session.delete(tournamentGroup);
		session.getTransaction().commit();
	}
	
	/**
	 * Tries to find a tournament group
	 * @param name
	 * @return
	 */
	public static TournamentGroup find(String name) {
		return (TournamentGroup) session.load(TournamentGroup.class, name);
	}
	
	/**
	 * Fetches all tournament group entries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<TournamentGroup> fetchAll() {
		return (List<TournamentGroup>) session.createQuery("From TournamentGroup").list();
	}
	
}
