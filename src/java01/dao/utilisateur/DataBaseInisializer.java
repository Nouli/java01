package java01.dao.utilisateur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.lang3.EnumUtils;

import Exception.UserNotFoundException;
import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.Gender;
import java01.entity.Utilisateur;
public class DataBaseInisializer {
	public static UtilisateurDao utilisateurDao;
	public DataBaseInisializer() {
		
	}
	
	public  void initDataBase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	UtilisateurDao utilisateurDao = new UtilisateurDao();
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserNotFoundException {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		try {
		utilisateurDao.select(1228);
		}catch (UserNotFoundException e) {
			System.out.println("User not found");
			}
		}

}


