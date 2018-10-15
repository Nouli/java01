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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import java01.entity.Gender;
import java01.entity.Utilisateur;

public class ConnectDB<T> {
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

	public Connection getConnection() {
		return connection;
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public Statement getSt() {

		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
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

	// rs.getString()
	public String getString(String string) throws SQLException {
		return rs.getString(string);
	}

	// rs.getInt()
	public int getInt(String j) throws SQLException {
		return rs.getInt(j);
	}

	// rs.getLong()
	public long getLong(String j) throws SQLException {
		return rs.getLong(j);
	}

	// ps.setLong
	public void setLong(int i, long j) throws SQLException {
		ps.setLong(i, j);
	}

	public void setString(int i, String string) throws SQLException {
		ps.setString(i, string);
	}

	public void setInt(int i, int j) throws SQLException {
		ps.setInt(i, j);
	}
	//SqlUtils Update

	// SqlUtils Delete :
	public void delete	(Class c, int id) throws UserNotFoundException, AppDataAccessException {
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
	public Object select(Class c, int id) throws UserNotFoundException, AppDataAccessException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		ArrayList results = new ArrayList();
		Object t = c.newInstance();
		try {
		// object
		Class cls = c.getClass();
		Constructor ct = c.getConstructor();
		List<Field> privateFields = new ArrayList<>();
		Field[] allFields = c.getDeclaredFields();

		// Execute SQL query
		
		this.databaseName=c.getSimpleName();
		//String query = "SELECT * FROM "+databaseName+" Where id=";
		String query = "SELECT * FROM utilisateur Where id=";
		
		this.executeQuery(query + id);
		if (rs != null) {
			ResultSetMetaData columns = rs.getMetaData();
			int i = 0;
			int count = columns.getColumnCount();
			while (rs.next()) {
				HashMap cols = new HashMap(count);
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
