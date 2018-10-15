package java01.dao.utilisateur;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;
import SqlUtils.DataAccess;
import java01.entity.Gender;
import java01.entity.Utilisateur;

public class UtilisateurDao {
	 
	ConnectDB connectDB = ConnectDB.getInstance();

	public void add(Utilisateur user) throws AppDataAccessException {
	try {
		//connectDB.insert(user);
		 String query = "insert into  projet.utilisateur (firstName,lastName,gender,age) values ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getGender()+"',"+user.getAge()+")";
		 connectDB.prepare(query);
		 connectDB.executeUpdate();
		 System.out.println(":: SERVER :: Record was Insered");
		}catch (Exception e ) {	
		throw new AppDataAccessException();
	}
	
	}
	public  Utilisateur select(int id) throws AppDataAccessException ,UserNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{

		return  (Utilisateur) connectDB.select(Utilisateur.class,id);
		
	}
	public void delete(int id) throws AppDataAccessException,UserNotFoundException {
	
		
		//connectDB.delete(user,id);
		
	}

	public void update(int id,Utilisateur user) throws AppDataAccessException ,UserNotFoundException{

	try {
		UtilisateurDao utilisateurDao = new UtilisateurDao();
		utilisateurDao.select(id);
		String query = "UPDATE projet.utilisateur SET firstName = ? ,lastName = ? ,gender = ? ,age = ? WHERE id = ?";
		connectDB.setPs(connectDB.getConnection().prepareStatement(query));
		
		connectDB.setString(1, user.getFirstName());
		connectDB.setString(2, user.getLastName());
		connectDB.setString(3, user.getGender());
		connectDB.setInt(4, user.getAge());
		connectDB.setInt(5, id);
		connectDB.executeUpdate();
		System.out.println(":: SERVER :: Record was Updated");
	} catch (Exception e ) {	

		throw new AppDataAccessException();
	}
	}

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

}
	
