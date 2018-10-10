package java01.dao.utilisateur;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;
import java01.entity.Gender;
import java01.entity.Utilisateur;

public class UtilisateurDao {
	 
	ConnectDB connectDB = ConnectDB.getInstance();
	UtilisateurDao utilisateurDao = new UtilisateurDao();
	public void add(Utilisateur user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	try {
		 PreparedStatement ps = connectDB.getConnection().prepareStatement("insert into  projet.utilisateur (firstName,lastName,gender,age) values ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getGender()+"',"+user.getAge()+")");
		 System.out.println(":: SERVER :: Record was Insered");
		 ps.executeUpdate();
	} catch (SQLException e ) {	
		 System.out.println(":: SERVER :: Insert not successful");
	}
	
	}
	/*
	public void delete(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException ,UserNotFoundException {
		try {
		String query = "DELETE FROM  projet.utilisateur WHERE id = " + id + ";";	
		connectDB.prepareStatement(query).executeQuery();
		System.out.println(":: SERVER :: Record was Deleted");
		} catch (SQLException e ) {	

			System.out.println(":: SERVER :: Delete not successful");
		}
		
		
	}
	
	public void update(int id,Utilisateur user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

	try {
		utilisateurDao.select(id);
		String query = "UPDATE projet.utilisateur SET firstName = ? ,lastName = ? ,gender = ? ,age = ? WHERE id = ?";
		PreparedStatement ps = connectDB.prepareStatement(query);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getGender());
		ps.setInt(4, user.getAge());
		ps.setInt(5, id);
		ps.executeUpdate();
		System.out.println(":: SERVER :: Record was Updated");
	} catch (UserNotFoundException e ) {	

		System.out.println(":: SERVER :: Update not successful");
	}
	}

	public ArrayList<Utilisateur> findAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserNotFoundException {
		Statement st = null;
		ArrayList utilisateurs = new ArrayList();

			String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur order by id";
			st = connectDB.getConnection().createStatement();
			ResultSet rs;
			rs = st.executeQuery(query);
			while ( rs.next() ) {
				
				Gender gender = Enum.valueOf(Gender.class, rs.getString("gender"));
				Utilisateur user= new Utilisateur (rs.getLong("id"),rs.getString("firstName"),rs.getString("lastName"),gender,rs.getInt("age"));
				utilisateurs.add(user);
							}
			
		
			if(st != null) {st.close();
			}
		return utilisateurs;
	}
	public  Utilisateur select(long id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, UserNotFoundException {
		Utilisateur user = null;
		Statement st = null;

		String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur Where id=";
		st = connectDB.getConnection().createStatement();
		ResultSet rs;
		rs = st.executeQuery(query + id );
		while ( rs.next() ) {
			
			Gender gender = Enum.valueOf(Gender.class, rs.getString("gender"));
			user= new Utilisateur (rs.getLong("id"),rs.getString("firstName"),rs.getString("lastName"),gender,rs.getInt("age"));
						}
		if(user == null) {
			throw new UserNotFoundException();
		}
		else {
			st.close();
			return user;
		}
		
		
	}*/
}
	
