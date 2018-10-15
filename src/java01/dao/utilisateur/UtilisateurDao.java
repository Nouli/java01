package java01.dao.utilisateur;



import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;

import java01.entity.Utilisateur;

public class UtilisateurDao {
	 
	ConnectDB connectDB = ConnectDB.getInstance();

	public void insert(Utilisateur user) throws AppDataAccessException {
	try {
		connectDB.insert(user);
		}catch (Exception e ) {	
		throw new AppDataAccessException();
	}
	
	}
	public  Utilisateur select(int id) throws AppDataAccessException ,UserNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{

		return  (Utilisateur) connectDB.select(Utilisateur.class,id);
		
	}
	public void delete(int id) throws AppDataAccessException,UserNotFoundException {
	
		connectDB.delete(Utilisateur.class,id);
		
	}

	public void update(Utilisateur user,int id) throws AppDataAccessException ,UserNotFoundException{

		connectDB.update(user, id);

	}
/*
	public ArrayList<Utilisateur> findAll() throws  AppDataAccessException, UserNotFoundException {
		ArrayList utilisateurs = new ArrayList();
	try{
			String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur order by id";
			connectDB.executeQuery(query);
			while ( connectDB.next() ) {
				
				Gender gender = Enum.valueOf(Gender.class, connectDB.getString("gender"));
				Utilisateur user= new Utilisateur (connectDB.getRs().getLong("id"),connectDB.getRs().getString("firstName"),connectDB.getRs().getString("lastName"),gender,connectDB.getRs().getInt("age"));
				utilisateurs.add(user);
							}
	}catch(Exception e){
	throw new AppDataAccessException(); 
		}
		return utilisateurs;
	}
*/
}
	
