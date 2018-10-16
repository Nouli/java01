package java01.dao.entity;

import java.util.ArrayList;

import DataAccess.DataAccess;
import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.entity.Entity;


public class EntityDao<T extends Entity> {
	DataAccess dataAccess = new DataAccess();
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
			dataAccess.insert(t);
		} catch (Exception e) {
			System.out.println("cannot access data");
		}
	}
	
	public  <T extends Entity> T select(int id) throws AppDataAccessException ,UserNotFoundException{
		return  (T) dataAccess.select(entityClass,id);
		
	}
	public <T extends Entity> void delete(int id) throws AppDataAccessException,UserNotFoundException {
		Class< ? extends T > c=null;
		dataAccess.delete(c,id);
		
	}
	public <T extends Entity> void update(T t,int id){

		try {
			dataAccess.update(t, id);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (AppDataAccessException e) {
			e.printStackTrace();
		}

	}

}