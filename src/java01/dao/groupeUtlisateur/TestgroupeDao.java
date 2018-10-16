package java01.dao.groupeUtlisateur;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


import Exception.AppDataAccessException;
import Exception.UserNotFoundException;

import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.Gender;

import java01.entity.Utilisateur;

public class TestgroupeDao {

	public static void main(String[] args) throws UserNotFoundException, AppDataAccessException {
		// TODO Auto-generated method stub
		UtilisateurDao userDao = new UtilisateurDao(Utilisateur.class);
		try {
		System.out.println(userDao.select(12).toString());
		}catch(UserNotFoundException e){
			System.out.println(e.getMessage());
		}
		}
}
