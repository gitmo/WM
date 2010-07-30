package dbs.project.dao;

import java.util.List;

import dbs.project.entity.permission.Permission;

public class PermissionDao extends DaoBase {

	/**
	 * Creates or updates a permission
	 * 
	 * @param permission
	 */
	public static void save(Permission permission) {
		session.beginTransaction();
		session.saveOrUpdate(permission);
		session.getTransaction().commit();
	}

	/**
	 * Creates or updates a list of permissions
	 * 
	 * @param permissions
	 */
	public static void saveAll(List<Permission> permissions) {
		for (Permission permission : permissions)
			save(permission);
	}

	/**
	 * Deletes a permission
	 * 
	 * @param Permission
	 */
	public static void delete(Permission permission) {
		session.beginTransaction();
		session.delete(permission);
		session.getTransaction().commit();
	}

	/**
	 * Tries to find a permission
	 * 
	 * @param name
	 * @return
	 */
	public static Permission find(String name) {
		return (Permission) session.load(Permission.class, name);
	}

	/**
	 * Fetches all permission entries
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Permission> fetchAll() {
		return (List<Permission>) session.createQuery("From Permission").list();
	}

}
