package dbs.project.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.entity.Match;
import dbs.project.entity.Team;
import dbs.project.entity.Tournament;

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

	/**
	 * saves all matches in the list
	 * 
	 * @param matches
	 */
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
		return session.createCriteria(Match.class)
				.add(Restrictions.eq("name", name)).list();
	}

	/**
	 * searches all matches where team1 and team2 meet
	 * 
	 * @param team1
	 * @param team2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Match> findByTeams(Team team1, Team team2) {
		return session
				.createCriteria(Match.class)
				.add(Restrictions.or(Restrictions.and(
						Restrictions.eq("hostTeam", team1),
						Restrictions.eq("guestTeam", team2)), Restrictions.and(
						Restrictions.eq("hostTeam", team2),
						Restrictions.eq("guestTeam", team1)))).list();
	}

	/**
	 * Fetches all match entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Match> fetchAll() {
		return session.createQuery("From Match").list();
	}

	@SuppressWarnings("unchecked")
	public static List<Match> findAllByTournament(Tournament tournament) {
		return session.createCriteria(Match.class)
				.add(Restrictions.eq("tournament", tournament)).list();
	}

}
