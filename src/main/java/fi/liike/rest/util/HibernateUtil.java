package fi.liike.rest.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


	
public class HibernateUtil {
	
	private final static SessionFactory sessionFactory = buildSessionFactory();
	private final static String testConfigurationPath = "testhibernate.cfg.xml";

	public static boolean isTestingSession() {
		return System.getProperty("db", "").equals("h2");
	}
	
	private static SessionFactory buildSessionFactory() {
		try {
			if (isTestingSession()) {
				return new Configuration().configure(testConfigurationPath).buildSessionFactory();
			}
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null){
			return buildSessionFactory();
		}
		return sessionFactory;
	}
 
	public static void shutdown() {
		// Close caches and connection pools
		if (sessionFactory != null){
			sessionFactory.close();
		}
	}
}
