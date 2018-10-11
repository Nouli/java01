package java01.dao.groupeUtlisateur;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;
import java01.entity.Gender;
import java01.entity.GroupeUtilisateur;
import java01.entity.Role;
import java01.entity.Utilisateur;

public class GroupeUtilisateurDao {
	ConnectDB connectDB = ConnectDB.getInstance();
	
	public GroupeUtilisateurDao() {
		super();
	}

	public void add(GroupeUtilisateur groupe) throws AppDataAccessException {
	try {
		 String query = "insert into  projet.groupe_utilisateur (name) values ('"+groupe.getName()+"')";
		 connectDB.prepare(query);
		 connectDB.executeUpdate();
		 System.out.println(":: SERVER :: Record was Insered");
		}catch (Exception e ) {	
		throw new AppDataAccessException();
		}
	}
	public  GroupeUtilisateur select(long id) throws AppDataAccessException ,UserNotFoundException{
		GroupeUtilisateur groupe = null;
		try {
				String query = "SELECT id ,name  FROM projet.groupe_utilisateur Where id=";
	
		connectDB.executeQuery(query + id );
		while ( connectDB.next() ) {
	
			Role name = Enum.valueOf(Role.class, connectDB.getRs().getString("name"));
			groupe= new GroupeUtilisateur (name);
						}
		}catch(Exception e) {
			throw new AppDataAccessException();
		}
		if(groupe == null) {
			
				throw new UserNotFoundException();
		}
		else {
			return groupe;
		}
		
	}

}
