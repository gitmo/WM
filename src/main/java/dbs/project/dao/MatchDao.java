package dbs.project.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.entity.Match;
import dbs.project.entity.Team;

public class MatchDao extends DaoBase {

	/**
	 * Creates or updates a match
	 * 
	 * @param match
	 */
	public static void save(Match match) {
		session.beginTransaction();
		session.saveOrUpdate(match);
		// session.flush();
		session.getTransaction().commit();
	}

	public static void saveAll(List<Match> matches) {
		for (Match match : matches)
			save(match);
	}

	/**
	 * Deletes a match
	 * 
	 * @param match
	 */
	public static void delete(Match match) {
		session.beginTransaction();
		session.delete(match);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find a match by name
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Match> findByName(String name) {
		return (List<Match>) session.createCriteria(Match.class).add(
				Restrictions.eq("name", name)).list();
	}

	@SuppressWarnings("unchecked")
	public static List<Match> findByTeams(Team team1, Team team2) {
		return (List<Match>) session.createCriteria(Match.class).add(
				Restrictions.or(Restrictions.and(Restrictions.eq("hostTeam",
						team1), Restrictions.eq("guestTeam", team2)),
						Restrictions.and(Restrictions.eq("hostTeam", team2),
								Restrictions.eq("guestTeam", team1)))).list();
	}

	/**
	 * Fetches all match entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Match> fetchAll() {
		return (List<Match>) session.createQuery("From Match").list();
	}

}
