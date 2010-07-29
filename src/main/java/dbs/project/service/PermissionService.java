package dbs.project.service;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.security.acl.Group;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import dbs.project.entity.Tournament;
import dbs.project.entity.permission.Actor;
import dbs.project.entity.permission.Permission;
import dbs.project.entity.permission.Resource;
import dbs.project.util.HibernateUtil;

public class PermissionService {

	@Test
	// TODO: Make separate JUnit-Test
	public static void main(String[] args) {

		final String plaintextPW = "plaintext";

		Actor a = new Actor();
		a.setEmail("mudda@fucker.org");

		// Encrypt a password and check if the same plain text creates same hash
		ActorService.setPasswordEncrypted(a, plaintextPW);
		String rehash = null;
		try {
			rehash = PasswordService.getInstance().encrypt(plaintextPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(a.getPassword_hash(), rehash);

		Tournament t = new Tournament();
		t.setYear(1990);
		t.setName("Turkmenistan");

		Permission p = new Permission();
		Resource r = new Resource();
		r.setName(Tournament.class.getName());
		r.setKey(1990);
		p.setTypeOfAccess(Permission.AccessType.READ);
		p.setResource(r);

		LinkedList<Permission> permissions = new LinkedList<Permission>();
		permissions.add(p);
		a.setPermissions(permissions);

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

		reqPerm.setTypeOfAccess(Permission.AccessType.READ);
		assertTrue(isAllowed(a, t, reqPerm));

		reqPerm.setTypeOfAccess(Permission.AccessType.UPDATE);
		assertFalse(isAllowed(a, t, reqPerm));

		reqPerm.setTypeOfAccess(Permission.AccessType.READ);
		reqPerm.getResource().setKey(1991);
		assertFalse(isAllowed(a, t, reqPerm));

		reqPerm.getResource().setKey(1990);
		assertTrue(isAllowed(a, t, reqPerm));

		r.setName(Group.class.getName());
		assertFalse(isAllowed(a, t, reqPerm));
	}

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
