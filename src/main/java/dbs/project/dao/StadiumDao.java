package dbs.project.dao;

import java.util.List;

import dbs.project.entity.Stadium;

public class StadiumDao extends DaoBase {

	/**
	 * Creates or updates a stadium
	 * 
	 * @param stadium
	 */
	public static void save(Stadium stadium) {
		session.beginTransaction();
		session.saveOrUpdate(stadium);
		session.getTransaction().commit();
	}

	public static void saveAll(List<Stadium> stadiums) {
		for (Stadium stadium : stadiums)
			save(stadium);
	}

	/**
	 * Deletes a stadium
	 * 
	 * @param stadium
	 */
	public static void delete(Stadium stadium) {
		session.beginTransaction();
		session.delete(stadium);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find a stadium
	 * 
	 * @param name
	 * @return
	 */
	public static Stadium find(String name) {
		return (Stadium) session.load(Stadium.class, name);
	}

	/**
	 * Fetches all stadium entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Stadium> fetchAll() {
		return session.createQuery("From Stadium").list();
	}

}
