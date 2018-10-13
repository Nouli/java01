package java01.dao.groupeUtlisateur;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;
import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.GroupeUtilisateur;
import java01.entity.Role;
import java01.entity.Utilisateur;

public class TestgroupeDao {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			SQLException, UserNotFoundException {
		// TODO Auto-generated method stub
		ConnectDB connectDB = ConnectDB.getInstance();
		GroupeUtilisateurDao groupeDao = new GroupeUtilisateurDao();
		GroupeUtilisateur groupe = new GroupeUtilisateur(Role.user);
		UtilisateurDao userDao = new UtilisateurDao();
		/*
		 *
		 **/ try {
			Utilisateur user1 = new Utilisateur();
			user1 = userDao.select(14);
			System.out.println(user1.toString());
		} catch (Exception e) {
			System.out.println("User Not found");
		}
	}
}
