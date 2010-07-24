package dbs.project.dao;

import org.hibernate.Session;

import dbs.project.util.HibernateUtil;

public abstract class DaoBase {
	protected static Session session;
	static {
		session = HibernateUtil.getSession();
	}
}
