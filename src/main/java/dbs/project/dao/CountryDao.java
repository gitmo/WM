package dbs.project.dao;

import java.util.List;

import dbs.project.entity.Country;
import dbs.project.entity.Player;

public class CountryDao extends DaoBase {
	
	/**
	 * Creates or updates a country
	 * @param country
	 */
	public static void save(Country country) {
		session.saveOrUpdate(country);
		session.flush();
	}
	
	public static void saveAll(List<Country> countries) {
		for(Country country : countries)
			save(country);
	}
	
	/**
	 * Deletes a country
	 * @param country
	 */
	public static void delete(Country country) {
		session.beginTransaction();
		session.delete(country);
		session.getTransaction().commit();
	}
	
	/**
	 * Tries to find a country
	 * @param name
	 * @return
	 */
	public static Country find(String name) {
		return (Country) session.load(Country.class, name);
	}
	
	/**
	 * Fetches all country entries
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Country> fetchAll() {
		return (List<Country>) session.createQuery("From Country").list();
	}
	
}
