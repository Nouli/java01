package java01.dao.groupeUtlisateur;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.dao.utilisateur.UtilisateurDao;
import java01.entity.GroupeUtilisateur;
import java01.entity.Role;
import java01.entity.Utilisateur;

public class TestgroupeDao {

	public static void main(String[] args) throws AppDataAccessException, UserNotFoundException {
		// TODO Auto-generated method stub
		GroupeUtilisateurDao groupeDao = new GroupeUtilisateurDao();
		GroupeUtilisateur groupe = new GroupeUtilisateur(Role.user);
		UtilisateurDao userDao = new UtilisateurDao();
		try {
		groupeDao.select(1);
		}catch(UserNotFoundException e){
			System.out.println("Groupe Not found");
		}
		Utilisateur user1 = userDao.select(25);
		Utilisateur user2 = userDao.select(10);
		groupe.addUser(user1);
		groupe.addUser(user2);
		System.out.println(groupe.toString());
		groupe.deleteUser(user2);
		System.out.println(groupe.toString());
	}

}
