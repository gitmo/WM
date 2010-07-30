package dbs.project.dao;

import java.util.List;

import dbs.project.entity.permission.Resource;

public class ResourceDao extends DaoBase {

	/**
	 * Creates or updates a resource
	 * 
	 * @param resource
	 */
	public static void save(Resource resource) {
		session.beginTransaction();
		session.saveOrUpdate(resource);
		session.getTransaction().commit();
	}

	/**
	 * Creates or updates a list of resources
	 * 
	 * @param resources
	 */
	public static void saveAll(List<Resource> resources) {
		for (Resource resource : resources)
			save(resource);
	}

	/**
	 * Deletes a resource
	 * 
	 * @param Resource
	 */
	public static void delete(Resource resource) {
		session.beginTransaction();
		session.delete(resource);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find a resource
	 * 
	 * @param name
	 * @return
	 */
	public static Resource find(String name) {
		return (Resource) session.load(Resource.class, name);
	}

	/**
	 * Fetches all resource entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Resource> fetchAll() {
		return (List<Resource>) session.createQuery("From Resource").list();
	}

}
