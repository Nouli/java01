package DataAccess;


import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import HibernateUtils.HibernateUtil;
import java01.entity.Entity;


public class DataAccessHibernate {
	private static Logger logger = Logger.getLogger("DataAccess");

	public DataAccessHibernate() {

	}
	// Hibernate Utils
	public <T extends Entity> void update(T t, Long id) throws AppDataAccessException, UserNotFoundException
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		t=(T) select(t.getClass(),id);
		session.getTransaction().commit();
		
	}
	
	// Delete public <T extends Entity> void delete(Class<T> c, Long id)
	public <T extends Entity> void delete(Class<T> type, Long id) throws AppDataAccessException, UserNotFoundException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.delete(select(type,id));
		session.getTransaction().commit();
		
	}
	public <T extends Entity> void save(T t) {
		try {

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			session.close();
			logger.info(t.getClass().getSimpleName() + " : " + t.toString() + " added ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.getSessionFactory().close();
		}

	}

	// find
	public <T extends Entity> T select(Class<T> type, Long id) throws AppDataAccessException, UserNotFoundException {
		Object ob = null;
		T t;
		try {
			t = type.newInstance();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			t = session.get(type, id);
			session.close();
			logger.info(type.getSimpleName() + " : select on id =" + id);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new AppDataAccessException();
		}
		try {
			t.toString();
		}catch(NullPointerException e) {
			throw new UserNotFoundException();
		}
			return t;
	}
	// findALL
	public <T extends Entity> ArrayList<T> findAll(Class<T> type) throws  AppDataAccessException, UserNotFoundException {
		ArrayList<T> entities = new ArrayList<T>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String query = "from "+type.getSimpleName();
		entities=(ArrayList<T>) session.createQuery(query).getResultList();
		session.close();
		return entities;
	}
}
