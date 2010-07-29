package dbs.project.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import dbs.project.entity.Tournament;
import dbs.project.entity.permission.Actor;
import dbs.project.entity.permission.Permission;
import dbs.project.entity.permission.PermissionAccessType;
import dbs.project.entity.permission.Resource;
import dbs.project.util.HibernateUtil;

public class PermissionService {

	@Test
	public static void main(String[] args) {
		
		final String CLASS_NAMES = "dbs.project.entity.Tournament";

		Actor a = new Actor();
		a.setEmail("mudda@fucker.org");
		a.setPassword_hash("kljsahdgiuy32qo87yfasdjhg532q87rwyu");

		Permission p = new Permission();
		Resource r = new Resource();
		r.setName(CLASS_NAMES);
		r.setKey(1990);
		p.setTypeOfAccess(PermissionAccessType.READ);
		p.setResource(r);

		LinkedList<Permission> permissions = new LinkedList<Permission>();
		permissions.add(p);
		a.setPermissions(permissions);

		Tournament t = new Tournament();
		t.setYear(1990);
		t.setName("Turkmenistan");

		Session sess = HibernateUtil.getSession();
		sess.beginTransaction();
		sess.save(r);
		sess.save(p);
		sess.save(a);
		sess.save(t);
		try {
			sess.getTransaction().commit();
		} finally {
			sess.close();
		}

		Permission reqPerm = new Permission();
		reqPerm.setResource(r);

		reqPerm.setTypeOfAccess(PermissionAccessType.READ);
		System.out.println(isAllowed(a, t, reqPerm));

		reqPerm.setTypeOfAccess(PermissionAccessType.UPDATE);
		System.out.println(isAllowed(a, t, reqPerm));
	}

	public static <T, S extends Serializable> boolean isAllowed(Actor actor,
			T reqEntity, Permission reqPerm) {

		System.out.printf("Comparing %s with %s", reqPerm, reqEntity);
		List<Permission> perms = actor.getPermissions();

		for (Permission perm : perms) {
			// TODO: UPDATE -> READ
			// Check for type of access (READ, UPDATE, ...) first
			if (reqPerm.getTypeOfAccess() != perm.getTypeOfAccess()) {
				System.out.println("Diff access type");
				continue;
			}

			Resource resEntity = perm.getResource();
			System.out.println("r.getName(): " + resEntity.getName());

			String entityName = reqEntity.getClass().getName();			
			if (entityName.endsWith(resEntity.getName())) {
				Serializable key = reqPerm.getResource().getKey();
				if (HibernateUtil.getSession().get(resEntity.getName(), key) != null)
					return true;
			}
		}

		return false;
	}
}
