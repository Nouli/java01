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
	  SessionFactory sf = HibernateUtil.getSessionFactory();
	  Session session = sf.openSession();
	public DataAccessHibernate() {

	}
	// Hibernate Utils
	public <T extends Entity> void update(T t, Long id) throws AppDataAccessException, UserNotFoundException
	{
		T t1=(T) select(t.getClass(),id);
		session.update(t1);
		session.getTransaction().begin();
		session.getTransaction().commit();
		//session.close();
	}
	

	public <T extends Entity> void delete(Class<T> type, Long id) throws AppDataAccessException, UserNotFoundException {
		if(!session.isOpen()) {
			session.beginTransaction();
			}
		
		session.delete(select(type,id));
		session.getTransaction().begin();
		session.getTransaction().commit();

	}
	public <T extends Entity> void save(T t) {
		try {
			if(!session.isOpen()) {
			session.beginTransaction();
			}
			session.save(t);
			logger.info(t.getClass().getSimpleName() + " : " + t.toString() + " added ");
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	// find
	public <T extends Entity> T select(Class<T> type, Long id) throws AppDataAccessException, UserNotFoundException {
		Object ob = null;
		T t;
		try {
			t = type.newInstance();
			if(!session.isOpen()) {
				session.beginTransaction();
				}
			t = session.get(type, id);
			//session.close();
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
		if(!session.isOpen()) {
			session.beginTransaction();
			}
		String query = "from "+type.getSimpleName();
		entities=(ArrayList<T>) session.createQuery(query).getResultList();
		//session.close();
		return entities;
	}
}
