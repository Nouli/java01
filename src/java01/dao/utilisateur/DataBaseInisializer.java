package java01.dao.utilisateur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.commons.lang3.EnumUtils;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.Gender;
import java01.entity.Utilisateur;
public class DataBaseInisializer {
	public static UtilisateurDao utilisateurDao;
	public DataBaseInisializer() {
		
	}
	
	public  void initDataBase() {
	UtilisateurDao utilisateurDao = new UtilisateurDao();
	}
	public static void main(String[] args) throws AppDataAccessException, UserNotFoundException  {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		utilisateurDao.select(12);
		}

}


