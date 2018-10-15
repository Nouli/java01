package java01.dao.groupeUtlisateur;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


import Exception.AppDataAccessException;
import Exception.UserNotFoundException;

import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.Gender;

import java01.entity.Utilisateur;

public class TestgroupeDao {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			SQLException, UserNotFoundException, AppDataAccessException {
		// TODO Auto-generated method stub
		Utilisateur user = new Utilisateur("hassanmodified", "mimonmodified", Gender.male, 15);
		UtilisateurDao userDao = new UtilisateurDao();
		/*
		 *
		 **/ try {			
			userDao.update(user,12);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
