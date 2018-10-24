package DataAccess;


import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import HibernateUtils.HibernateUtil;
import java01.entity.Entity;


public class DataAccessHibernate {
	 private static Logger logger = Logger.getLogger("DataAccess");
	 private HibernateUtil hibernateUtil;
	 SessionFactory sf = HibernateUtil.getSessionFactory();
	 Session session = sf.openSession();
	 interface Function<T, R> {
		    R apply(T t);
		}

		<R> R execute(final Function<Session, R> func) {
		    R result = null;

		    session = sf.openSession();
		    try {
		    	if(session.isOpen()) {
		        session.beginTransaction();
		    	}
		        result = func.apply(session);

		        session.getTransaction().commit();
		    } catch (Exception e) {
		        e.printStackTrace();
		        session.getTransaction().rollback();
		    } finally {
		        session.close();
		    }
			return result;
		}
		
	public DataAccessHibernate() {

	}	
	// Hibernate Utils
	public <T extends Entity> void update(T t) {
		execute(s -> s.merge(t));
    }
	//save
	public <T extends Entity> void save(T t) {
		execute(s->s.save(t));
	}
	// findALL
	@SuppressWarnings("unchecked")
	public <T extends Entity> ArrayList<T> findAll(Class<T> type) {

		return (ArrayList<T>) execute(s->s.createQuery("from "+type.getSimpleName()).getResultList());
	}
	// find/get
	public <T extends Entity> T select(Class<T> type, Long id) {
		return execute(s->s.get(type, id));
	}
	//Delete
	public <T extends Entity> void delete(Class<T> type, Long id) throws UserNotFoundException, AppDataAccessException {
		 
	        try {
	        		        	
	            Object object = select(type, id);
	            if (!session.isOpen()) {
	                session = session.getSessionFactory().openSession();
	                session.beginTransaction();
	            }
	            if (object == null) {
	                throw new UserNotFoundException(type.getSimpleName()+" NotFound");
	            }
	            session.delete(object);
	            session.getTransaction().commit();
	        } catch (Exception ex) {
	            //throw new AppDataAccessException(type.getSimpleName()+" cannot be deleted");
	        	ex.printStackTrace();
	        } finally {
	            session.close();
	        }
		
		
	    }



}
