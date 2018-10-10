package java01.dao.groupeUtlisateur;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.entity.GroupeUtilisateur;
import java01.entity.Role;

public class TestgroupeDao {

	public static void main(String[] args) throws AppDataAccessException, UserNotFoundException {
		// TODO Auto-generated method stub
		GroupeUtilisateurDao groupeDao = new GroupeUtilisateurDao();
		GroupeUtilisateur groupe = new GroupeUtilisateur(Role.user);
		try {
		groupeDao.select(1);
		}catch(UserNotFoundException e){
			System.out.println("Groupe Not found");
		}
	}

}
