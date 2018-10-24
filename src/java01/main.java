package java01;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import DataAccess.DataAccessHibernate;
import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import HibernateUtils.HibernateUtil;
import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.Gender;
import java01.entity.GroupeUtilisateur;
import java01.entity.Role;
import java01.entity.Utilisateur;

public class main {
	private static Logger logger = Logger.getLogger("main");
	public static void main(String[] args) throws AppDataAccessException, UserNotFoundException {
		// TODO Auto-generated method stub
		DataAccessHibernate da = new DataAccessHibernate();
		UtilisateurDao userDao = new UtilisateurDao();


	}

}
