package dbs.project.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.entity.Team;

public class TeamDao extends DaoBase {

	/**
	 * Creates or updates a team
	 * 
	 * @param Team
	 */
	public static void save(Team team) {
		session.beginTransaction();
		session.saveOrUpdate(team);
		// session.flush();
		session.getTransaction().commit();
	}

	public static void saveAll(List<Team> teams) {
		for (Team team : teams)
			save(team);
	}

	/**
	 * Deletes a team
	 * 
	 * @param Team
	 */
	public static void delete(Team team) {
		session.beginTransaction();
		session.delete(team);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find a team by name
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Team> findByName(String name) {
		return (List<Team>) session.createCriteria(Team.class).add(
				Restrictions.eq("name", name)).list();
	}

	/**
	 * Fetches all team entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Team> fetchAll() {
		return (List<Team>) session.createQuery("From Team").list();
	}

}
