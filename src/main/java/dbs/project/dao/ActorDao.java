package dbs.project.dao;

import java.util.List;

import dbs.project.entity.permission.Actor;

public class ActorDao extends DaoBase {

	/**
	 * Creates or updates an actor
	 * 
	 * @param actor
	 */
	public static void save(Actor actor) {
		session.beginTransaction();
		session.saveOrUpdate(actor);
		session.getTransaction().commit();
	}

	/**
	 * Creates or updates a list of actors
	 * 
	 * @param actors
	 */
	public static void saveAll(List<Actor> actors) {
		for (Actor actor : actors)
			save(actor);
	}

	/**
	 * Deletes an actor
	 * 
	 * @param Actor
	 */
	public static void delete(Actor actor) {
		session.beginTransaction();
		session.delete(actor);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find an actor
	 * 
	 * @param name
	 * @return
	 */
	public static Actor find(String name) {
		return (Actor) session.load(Actor.class, name);
	}

	/**
	 * Fetches all actor entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Actor> fetchAll() {
		return (List<Actor>) session.createQuery("From Actor").list();
	}

}
