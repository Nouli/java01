package java01.dao.utilisateur;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java01.dao.ConnectDB;
import java01.entity.Gender;
import java01.entity.Utilisateur;

public class UtilisateurDao {
	 
	 
	public void add(Utilisateur user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		 ConnectDB connectDB = new ConnectDB();
	try {
		 PreparedStatement ps = connectDB.connection.prepareStatement("insert into  projet.utilisateur (firstName,lastName,gender,age) values ('"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getGender()+"',"+user.getAge()+")");
		 System.out.println(":: SERVER :: Record was Insered");
		 ps.executeUpdate();
	} catch (SQLException e ) {	
		 System.out.println(":: SERVER :: Insert not successful");
	}
	
	}
	
	void delete(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ConnectDB connectDB = new ConnectDB();
		try {
		PreparedStatement ps = connectDB.connection.prepareStatement("DELETE FROM  projet.utilisateur WHERE id = " + id + ";");
		ps.executeUpdate();
		System.out.println(":: SERVER :: Record was Deleted");
		} catch (SQLException e ) {	

			System.out.println(":: SERVER :: Delete not successful");
		}
		
		
	}
	
	void update(int id,Utilisateur user) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ConnectDB connectDB = new ConnectDB();
	try {
		String query = "UPDATE projet.utilisateur SET firstName = ? ,lastName = ? ,gender = ? ,age = ? WHERE id = ?";
		PreparedStatement ps = connectDB.connection.prepareStatement(query);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getGender());
		ps.setInt(4, user.getAge());
		ps.setInt(5, id);
		ps.executeUpdate();
		System.out.println(":: SERVER :: Record was Updated");
	} catch (SQLException e ) {	

		System.out.println(":: SERVER :: Update not successful");
	}
	}

	ArrayList<Utilisateur> findAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ConnectDB connectDB = new ConnectDB();
		Statement st = null;
		ArrayList utilisateurs = new ArrayList();
		try {
			String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur order by id";
			st = connectDB.connection.createStatement();
			ResultSet rs;
			rs = st.executeQuery(query);
			while ( rs.next() ) {
				
				Gender gender = Enum.valueOf(Gender.class, rs.getString("gender"));
				Utilisateur user= new Utilisateur (rs.getLong("id"),rs.getString("firstName"),rs.getString("lastName"),gender,rs.getInt("age"));
				utilisateurs.add(user);
							}
			
		}catch(SQLException e ){
			System.out.println(":: SERVER :: Update not successful");	
		}finally {
			if(st != null) {st.close();}
		}
		return utilisateurs;
	}
	Utilisateur find(long id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		ConnectDB connectDB = new ConnectDB();
		Utilisateur user = new Utilisateur();
		Statement st = null;
		try {
		String query = "SELECT id ,firstName ,lastname ,gender ,age FROM projet.utilisateur Where id=";
		st = connectDB.connection.createStatement();
		ResultSet rs;
		rs = st.executeQuery(query + id );
		while ( rs.next() ) {
			
			Gender gender = Enum.valueOf(Gender.class, rs.getString("gender"));
			user= new Utilisateur (rs.getLong("id"),rs.getString("firstName"),rs.getString("lastName"),gender,rs.getInt("age"));
						}
		
	}catch(SQLException e ){
		System.out.println(":: SERVER :: Update not successful");	
	}finally {
		if(st != null) {st.close();}
	}
		return user;
	}
}
	
