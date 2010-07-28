package dbs.project.dao;

import java.util.List;

import dbs.project.entity.Country;
import dbs.project.entity.KnockoutMatch;

public class KnockoutMatchDao extends DaoBase {

	/**
	 * Creates or updates a KnockoutMatch
	 * 
	 * @param country
	 */
	public static void save(KnockoutMatch country) {
		session.beginTransaction();
		session.saveOrUpdate(country);
		session.getTransaction().commit();
	}

	public static void saveAll(List<KnockoutMatch> knockoutMatches) {
		for (KnockoutMatch match : knockoutMatches)
			save(match);
	}

	/**
	 * Deletes a KnockoutMatch
	 * 
	 * @param match
	 */
	public static void delete(KnockoutMatch match) {
		session.beginTransaction();
		session.delete(match);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find a KnockoutMatch
	 * 
	 * @param name
	 * @return
	 */
	public static KnockoutMatch find(String name) {
		return (KnockoutMatch) session.load(KnockoutMatch.class, name);
	}

	/**
	 * Fetches all KnockoutMatch entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<KnockoutMatch> fetchAll() {
		return (List<KnockoutMatch>) session.createQuery("From KnockoutMatch").list();
	}

}
