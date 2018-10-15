package java01.dao.groupeUtlisateur;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;
import java01.entity.GroupeUtilisateur;


public class GroupeUtilisateurDao{
	ConnectDB connectDB = ConnectDB.getInstance();
	
	public GroupeUtilisateurDao() {
		super();
	}


	public  GroupeUtilisateur select(int id) throws AppDataAccessException ,UserNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		
			return (GroupeUtilisateur) connectDB.select(GroupeUtilisateur.class, id);
		}
	public  void delete(int id) throws AppDataAccessException ,UserNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
		
		connectDB.delete(GroupeUtilisateur.class, id);
	}
	public void update(GroupeUtilisateur groupe,int id) throws AppDataAccessException ,UserNotFoundException{

		connectDB.update(groupe, id);

	}
	public void insert(GroupeUtilisateur groupe) throws UserNotFoundException, AppDataAccessException  {

		connectDB.insert(groupe);
	
	}

}
	