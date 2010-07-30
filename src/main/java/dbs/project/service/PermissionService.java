package dbs.project.service;

import java.io.Serializable;
import java.util.List;

import dbs.project.entity.permission.Actor;
import dbs.project.entity.permission.Permission;
import dbs.project.util.HibernateUtil;

public class PermissionService {

	public static <T, S extends Serializable> boolean isAllowed(Actor actor,
			T reqEntity, Permission reqPerm) {

		System.out.printf("Comparing %s with %s\n", reqPerm, reqEntity);
		List<Permission> perms = actor.getPermissions();

		for (Permission perm : perms) {
			// TODO: UPDATE -> READ
			// Check for type of access (READ, UPDATE, ...) first
			if (reqPerm.getTypeOfAccess() != perm.getTypeOfAccess())
				// Different access type -> check next available permission
				continue;

			String entityName = perm.getResource().getName();
			Class<? extends Object> clazz = reqEntity.getClass();
			if (entityName.endsWith(clazz.getName())) {
				Serializable key = reqPerm.getResource().getKey();
				if (HibernateUtil.getSession().get(clazz, key) != null)
					return true;
			}
		}

		return false;
	}
}
