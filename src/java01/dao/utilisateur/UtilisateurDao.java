package java01.dao.utilisateur;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;
import java01.entity.Gender;
import java01.entity.Utilisateur;

public class UtilisateurDao {
	 
	ConnectDB connectDB = ConnectDB.getInstance();

	public void add(Utilisateur user) throws AppDataAccessException {
	try {
		 String query = "insert into  projet.utilisateur (firstName,lastName,gender,age) values ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getGender()+"',"+user.getAge()+")";
		 connectDB.setPs(connectDB.getConnection().prepareStatement(query));
		 
		 connectDB.getPs().executeUpdate();
		 System.out.println(":: SERVER :: Record was Insered");
		}catch (Exception e ) {	
		throw new AppDataAccessException();
	}
	
	}
	public  Utilisateur select(long id) throws AppDataAccessException ,UserNotFoundException{
		Utilisateur user = null;
		try {
		
		
		String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur Where id=";
		connectDB.setSt(connectDB.getConnection().createStatement());
	
		connectDB.setRs(connectDB.getSt().executeQuery(query + id ));
		while ( connectDB.getRs().next() ) {
	
			Gender gender = Enum.valueOf(Gender.class, connectDB.getRs().getString("gender"));
			user= new Utilisateur (connectDB.getRs().getLong("id"),connectDB.getRs().getString("firstName"),connectDB.getRs().getString("lastName"),gender,connectDB.getRs().getInt("age"));
						}
		}catch(Exception e) {
			throw new AppDataAccessException();
		}
		if(user == null) {
			
				throw new UserNotFoundException();
		}
		else {
			return user;
		}
		
		
	}
	public void delete(int id) throws AppDataAccessException,UserNotFoundException {
		 	UtilisateurDao utilisateurDao = new UtilisateurDao();
		 	Utilisateur user = null;
		 	try {
				
				user = utilisateurDao.select(id);
				String query = "DELETE FROM  projet.utilisateur WHERE id = ?";
				connectDB.setPs(connectDB.getConnection().prepareStatement(query));
				connectDB.getPs().setLong(1, id);
				connectDB.getPs().executeUpdate();
			
				System.out.println(":: SERVER :: Record was Deleted");
			}catch (UserNotFoundException e) {
				throw new UserNotFoundException();
		}
		 	catch (Exception e) {
					throw new AppDataAccessException();
			}
		
		
	}

	public void update(int id,Utilisateur user) throws AppDataAccessException ,UserNotFoundException{

	try {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		utilisateurDao.select(id);
		String query = "UPDATE projet.utilisateur SET firstName = ? ,lastName = ? ,gender = ? ,age = ? WHERE id = ?";
		connectDB.setPs(connectDB.getConnection().prepareStatement(query));
		
		connectDB.getPs().setString(1, user.getFirstName());
		connectDB.getPs().setString(2, user.getLastName());
		connectDB.getPs().setString(3, user.getGender());
		connectDB.getPs().setInt(4, user.getAge());
		connectDB.getPs().setInt(5, id);
		connectDB.getPs().executeUpdate();
		System.out.println(":: SERVER :: Record was Updated");
	} catch (Exception e ) {	

		throw new AppDataAccessException();
	}
	}

	public ArrayList<Utilisateur> findAll() throws  AppDataAccessException, UserNotFoundException {
		ArrayList utilisateurs = new ArrayList();
	try{
			String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur order by id";
			connectDB.setSt(connectDB.getConnection().createStatement());
			
			connectDB.setRs(connectDB.getSt().executeQuery(query));
			while ( connectDB.getRs().next() ) {
				
				Gender gender = Enum.valueOf(Gender.class, (connectDB.getRs()).getString("gender"));
				Utilisateur user= new Utilisateur (connectDB.getRs().getLong("id"),connectDB.getRs().getString("firstName"),connectDB.getRs().getString("lastName"),gender,connectDB.getRs().getInt("age"));
				utilisateurs.add(user);
							}
	}catch(Exception e){
	throw new AppDataAccessException(); 
		}
		return utilisateurs;
	}

}
	
