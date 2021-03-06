package dbs.project.dao.event;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.collections.filter.FilterSubstitutionEvent;
import dbs.project.dao.DaoBase;
import dbs.project.entity.Match;
import dbs.project.entity.Player;
import dbs.project.entity.event.player.SubstitutionEvent;
import dbs.project.util.Collections;

public class SubstitutionEventDao extends DaoBase {

	/**
	 * Save EventSubstitution
	 * 
	 * @param es
	 */
	public static void save(SubstitutionEvent es) {
		session.beginTransaction();
		session.save(es);
		session.getTransaction().commit();
	}

	/**
	 * Saves a list of EventSubstitution
	 * 
	 * @param List
	 *            <EventSubstitution>
	 */
	public static void saveAll(List<SubstitutionEvent> les) {
		for (SubstitutionEvent es : les) {
			save(es);
		}
	}

	/**
	 * Searches all EventSubstitutions by player
	 * 
	 * @param player
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SubstitutionEvent> findByPlayer(Player player) {
		return session
				.createCriteria(SubstitutionEvent.class)
				.add(Restrictions.or(Restrictions.eq("newPlayer", player),
						Restrictions.eq("involvedPlayer", player))).list();
	}

	/**
	 * Searches all EventSubstitution for a Match
	 * 
	 * @param match
	 * @return
	 */
	public static List<SubstitutionEvent> findByMatch(Match match) {
		List<SubstitutionEvent> res = new ArrayList<SubstitutionEvent>();
		Collections.filterAndChangeType(match.getEvents(),
				new FilterSubstitutionEvent(), res);
		return res;
	}

	/**
	 * Searches all EventSubstitution for a player in a match
	 * 
	 * @param player
	 * @param match
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SubstitutionEvent> findByPlayerAndMatch(Player player,
			Match match) {
		List<SubstitutionEvent> res = session.createCriteria(SubstitutionEvent.class).add(Restrictions.eq("involvedPlayer",player)).add(Restrictions.eq("match",match)).list();
		return res;

	}

	@SuppressWarnings("unchecked")
	public static List<SubstitutionEvent> findAllByMatch(Match match) {
		return session.createCriteria(SubstitutionEvent.class)
				.add(Restrictions.eq("match", match)).list();
	}

}
