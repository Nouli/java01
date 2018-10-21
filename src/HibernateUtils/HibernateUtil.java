package HibernateUtils;

import javax.imageio.spi.ServiceRegistry;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;

/*public class HibernateUtil {
    public static final SessionFactory sessionFactory;

    static {
        try {
            // Création de la SessionFactory à partir de hibernate.cfg.xml
            sessionFactory = new Configuration().configure("Hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

   // public static final ThreadLocal session = new ThreadLocal();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}*/
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private static Logger logger = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration();
        	configuration.configure("hibernate.cfg.xml");
        	logger.info("Hibernate Configuration loaded");
        	
        	SessionFactory sessionFactory = configuration.buildSessionFactory();
        	
            return sessionFactory;
        }
        catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}