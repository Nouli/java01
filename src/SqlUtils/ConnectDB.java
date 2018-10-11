package SqlUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
 private static ConnectDB instance = new ConnectDB();
 private Connection connection=null;
 private PreparedStatement ps=null;
 private Statement st=null;
 private ResultSet rs=null;
 private String databaseName = "";
 private String url= "jdbc:mysql://localhost:3306/" +databaseName;
 private String username = "root";
 private String password = "";
 
 	public static ConnectDB getInstance() {
 		return instance;
 	}
 	private ConnectDB() {
 		if (connection == null){
 			try {
 				Class.forName("com.mysql.jdbc.Driver").newInstance();
 				connection = DriverManager.getConnection(url, username, password);
 			}catch(Exception e) {
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
	//ResultSet.executeQuery()
	public void executeQuery(String query) throws SQLException {
		st=connection.createStatement();
		rs=st.executeQuery(query);
	}
	//PS.executeUpdate()
		public void executeUpdate() throws SQLException {
			ps.executeUpdate();
		}
	//PreparedStatement.prepareStatement()
	public void prepare(String query) throws SQLException {
		ps=connection.prepareStatement(query);
	}
	//Resultset rs.next()
	public boolean next() throws SQLException  {
		if(rs.next()) {
			return true;
		}else {
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
	//ps.setLong
	public void setLong(int i,long j) throws SQLException {
		ps.setLong(i,j);
		}
	public void setString(int i,String string) throws SQLException {
		ps.setString(i,string);
	}
	public void setInt(int i,int j) throws SQLException {
		ps.setInt(i,j);
		}
 }
