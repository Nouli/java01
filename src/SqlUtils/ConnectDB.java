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
	

 }
