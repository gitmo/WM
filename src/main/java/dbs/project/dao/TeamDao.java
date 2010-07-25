package dbs.project.dao;

import java.util.List;

import dbs.project.entity.Team;

public class TeamDao extends DaoBase {
	
	/**
	 * Creates or updates a team
	 * @param Team
	 */
	public static void save(Team team) {
		session.save(team);
		session.flush();
	}
	
	public static void saveAll(List<Team> teams) {
		for(Team team : teams)
			save(team);
	}
	
	/**
	 * Deletes a team
	 * @param Team
	 */
	public static void delete(Team team) {
		session.beginTransaction();
		session.delete(team);
		session.getTransaction().commit();
	}
	
	/**
	 * Tries to find a team
	 * @param name
	 * @return
	 */
	public static Team find(String name) {
		return (Team) session.load(Team.class, name);
	}
	
	/**
	 * Fetches all team entries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Team> fetchAll() {
		return (List<Team>) session.createQuery("From Team").list();
	}
	
}
