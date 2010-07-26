package dbs.project.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dbs.project.entity.EventSubstitution;
import dbs.project.entity.Player;

public class EventSubstitutionDao extends DaoBase{

	/**
	 * Save EventSubstitution
	 * @param es
	 */
	public static void save(EventSubstitution es){
		session.beginTransaction();
		session.save(es);
		session.getTransaction().commit();
	}
	
	/**
	 * Saves a list of EventSubstitution
	 * @param List<EventSubstitution>
	 */
	public static void saveAll(List<EventSubstitution> les){
		for(EventSubstitution es : les){
			save(es);
		}
	}
	
	/**
	 * Searches all EventSubstitutions by player 
	 * @param player
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public static List<EventSubstitution> findByPlayer(Player player){
		return (List<EventSubstitution>) session.createCriteria(EventSubstitution.class)
				.add(Restrictions.or(
					Restrictions.eq("newPlayer", player), Restrictions.eq("involvedPlayer", player)
					)
				).list();
	}
	
	
	
}
