package java01.dao.utilisateur;
import java.sql.SQLException;

import java01.Gender;
import java01.Utilisateur;
import java01.dao.utilisateur.UtilisateurDao;
public class DataBaseInisializer {
	public UtilisateurDao utilisateurDao;
	static Utilisateur user1 = new Utilisateur("newName","koki",Gender.male,57);
	public DataBaseInisializer() {
		
	}
	public  void initDataBase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	UtilisateurDao utilisateurDao = new UtilisateurDao();
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		DataBaseInisializer	initDataBase = new DataBaseInisializer() ;
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		
		//user1.setAge(29);
		//utilisateurDao.add(user1);
		//utilisateurDao.delete(2);
		utilisateurDao.update(9, user1);
	}

}


