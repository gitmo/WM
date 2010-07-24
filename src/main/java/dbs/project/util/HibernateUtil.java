package dbs.project.util;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	//Session-Factory wird beim ersten Laden der Klasse instanziert
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
			.configure().buildSessionFactory();
		} catch (Throwable ex) {
		// Log exception!
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}
