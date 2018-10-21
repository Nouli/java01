package java01.dao.entity;

import java.util.ArrayList;

import DataAccess.DataAccessHibernate;
import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.DataAccess;
import java01.entity.Entity;


public class EntityDao<T extends Entity> {
	DataAccess dataAccess = new DataAccess();
	DataAccessHibernate dataAccess2 = new DataAccessHibernate();
	protected Class<T> entityClass;
	public EntityDao(Class<T> entityClass)
	{
		this.entityClass = entityClass;
	}
	public EntityDao()
	{
		this.entityClass = entityClass;
	}
	// insert
	public <T extends Entity> void save(T t) {
		try {
			dataAccess2.save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  <T extends Entity> T select(Long id) throws AppDataAccessException ,UserNotFoundException{
		return  (T) dataAccess2.select(entityClass,id);
		
	}
	public <T extends Entity> void delete(Long id) throws AppDataAccessException,UserNotFoundException {
		dataAccess2.delete(entityClass,id);
		
	}
	public <T extends Entity> void update(T t,Long id){

		try {
			dataAccess.update(t, id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (AppDataAccessException e) {
			e.printStackTrace();
		}

	}
	public <T> ArrayList<T>  findAll() throws AppDataAccessException ,UserNotFoundException{
		return  (ArrayList<T>) dataAccess2.findAll(entityClass);	
	}

}