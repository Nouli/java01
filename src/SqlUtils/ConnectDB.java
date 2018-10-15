package SqlUtils;

import java.sql.Connection;
import java.lang.reflect.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Vector;


import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.entity.Entity;



public class ConnectDB {
	private static Connection connection = null;
	private PreparedStatement ps = null;
	private Statement st = null;
	private ResultSet rs = null;
	private String databaseName = "projet";
	private String url = "jdbc:mysql://localhost:3306/" + databaseName;
	private String username = "root";
	private String password = "";
	private static ConnectDB connectDB = null;
	Vector<String> columnNames = new Vector<String>();

	public static ConnectDB getInstance() {
		if (connectDB == null) {
			connectDB = new ConnectDB();
		}
		return connectDB;
	}

	private ConnectDB() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(url, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ResultSet.executeQuery()
	public void executeQuery(String query) throws SQLException {
		st = connection.createStatement();
		rs = st.executeQuery(query);
	}

	// PS.executeUpdate()
	public void executeUpdate() throws SQLException {
		ps.executeUpdate();
	}

	// PreparedStatement.prepareStatement()
	public void prepare(String query) throws SQLException {
		ps = connection.prepareStatement(query);
	}

	// Resultset rs.next()
	public boolean next() throws SQLException {
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}
	public void setString(int i, String string) throws SQLException {
		ps.setString(i, string);
	}

	public void setInt(int i, int j) throws SQLException {
		ps.setInt(i, j);
	}
	//SqlUtils Update

	public <T extends Entity> void update (T t,int id) throws UserNotFoundException, AppDataAccessException {
			List<Field> privateFields = new ArrayList<>();
			Field[] allFields = t.getClass().getDeclaredFields();
			//UPDATE `projet`.`utilisateur` SET `firstName` = 'amma', `lastName` = 'moo', `gender` = 'female', `age` = '222' WHERE (`id` = '12');
			this.databaseName=t.getClass().getSimpleName();

			try {

				String query1 = "SELECT * FROM "+databaseName+"";
				connectDB.executeQuery(query1);
				String query = "UPDATE "+databaseName+" SET";
				if (rs != null) {
					ResultSetMetaData columns = rs.getMetaData();
					int i = 0;
					while (rs.next() && i==0) {
						int j = 1;
						while (j < columns.getColumnCount()) {
							j++;
							for (Field field : allFields) {
								if (Modifier.isPrivate(field.getModifiers())) {
									privateFields.add(field);
									if (field.getName().toString().equals(columns.getColumnLabel(j))) {
										field.setAccessible(true);
										query +=" "+"`"+columns.getColumnLabel(j)+"`="+"'"+field.get(t)+"' ,";
										}
									}

								}
							}
						i++;
						}
					
					}
				query = query.substring(0, query.length()-2);
				query +="where ( id = "+id+")";
				System.out.println(query);	
				connectDB.prepare(query);
				connectDB.executeUpdate();
					
				}	catch(Exception e){
				e.printStackTrace();
			}
		
	}
	//SqlUtils Insert
	public <T extends Entity> void insert (T t) throws UserNotFoundException, AppDataAccessException {
		ArrayList<String> results = new ArrayList<String>();
		List<Field> privateFields = new ArrayList<>();
		Field[] allFields = t.getClass().getDeclaredFields();
		
		try {

			//INSERT INTO `projet`.`utilisateur` (firstName, lastName, gender, age) VALUES ('atat', 'yaya', 'male', '155');

			this.databaseName=t.getClass().getSimpleName();
			String query1 = "SELECT * FROM utilisateur";
			String query = "INSERT INTO "+databaseName+" (";
			connectDB.executeQuery(query1);
			if (rs != null) {
				ResultSetMetaData columns = rs.getMetaData();
				int i = 1;
				while (i < columns.getColumnCount()) {
					i++;
					query += columns.getColumnLabel(i)+", ";
					results.add(columns.getColumnLabel(i));	
				}	
				query = query.substring(0, query.length()-2);
				query += ") VALUES (";
					System.out.println(query);
					for (Field field : allFields) {
						if (Modifier.isPrivate(field.getModifiers())) {
							privateFields.add(field);
							
							if (results.contains(field.getName().toString())) {
								field.setAccessible(true);
								field.get(t);
								query+=" '"+field.get(t)+"' ,";
								
								}
							}

						}
				
					query = query.substring(0, query.length()-2);
					query +=")";
	
					connectDB.prepare(query);
					connectDB.executeUpdate();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	// SqlUtils Delete :
	public <T extends Entity> void delete	(Class<T> c, int id) throws UserNotFoundException, AppDataAccessException {
		try {
			select(c, id);
			String query = "DELETE FROM  "+databaseName+"  WHERE id = ?";
			connectDB.prepare(query);
			connectDB.setInt(1, id);
			connectDB.executeUpdate();
		}catch(UserNotFoundException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public <T extends Entity> T select(Class<T> c, int id) throws UserNotFoundException, AppDataAccessException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
		T t = c.newInstance();
		try {
		List<Field> privateFields = new ArrayList<>();
		Field[] allFields = c.getDeclaredFields();

		// Execute SQL query
		
		this.databaseName=c.getSimpleName();
		String query = "SELECT * FROM "+databaseName+" Where id=";
		connectDB.executeQuery(query+id);
		if (rs != null) {
			ResultSetMetaData columns = rs.getMetaData();
			int count = columns.getColumnCount();
			while (rs.next()) {
				HashMap<String, Object> cols = new HashMap<String, Object>(count);
				int j = 0;
				while (j < columns.getColumnCount()) {
					j++;
					Object ob = rs.getObject(j);
					if (rs.wasNull()) {
						ob = null;
					}
					cols.put(columns.getColumnLabel(j), ob);
					for (Field field : allFields) {
						if (Modifier.isPrivate(field.getModifiers())) {
							privateFields.add(field);
							if (field.getName().toString().equals(columns.getColumnLabel(j))) {

								field.setAccessible(true);
								if (field.getType().isEnum()) {
									String value = (String) rs.getObject(j);
									field.setAccessible(true);
									field.set(t, Enum.valueOf((Class<Enum>) field.getType(), value));
								} else {
									field.set(t, rs.getObject(j));;
			
								}
							}

						}
					}

				}
				results.add(cols);
			}		
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return  t;

	}
}
