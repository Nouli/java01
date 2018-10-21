package Jbdc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibConnectDB {
	private static HibConnectDB hibConnectDB = null;
	private static Session session = null;

	public static HibConnectDB getInstance() {
		if (hibConnectDB == null) {
			hibConnectDB = new HibConnectDB();
		}
		return hibConnectDB;
	}
	private HibConnectDB() {
		if (session == null) {
			try {
				SessionFactory factory = Configuration.class.newInstance().configure().buildSessionFactory();
				session = factory.getCurrentSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
