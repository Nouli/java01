package java01;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import DataAccess.DataAccessHibernate;
import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.entity.Gender;
import java01.entity.Utilisateur;

public class main {
	private static Logger logger = Logger.getLogger("main");
	public static void main(String[] args) throws AppDataAccessException, UserNotFoundException {
		// TODO Auto-generated method stub
		DataAccessHibernate da = new DataAccessHibernate();
	Utilisateur user1 = new Utilisateur("moUpdate", "tata", Gender.female, 11);
	//user1.setAge(200);
	//ArrayList<Utilisateur> users = da.findAll(Utilisateur.class);
		//da.update(user1,(long) 41);
	da.save(user1);
		//da.delete(Utilisateur.class, (long) 40);
		
	System.out.println(user1.toString());
	}

}
