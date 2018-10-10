package SqlUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDB {
 private static ConnectDB instance = new ConnectDB();
 private Connection connection;
 private PreparedStatement ps;
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
	public PreparedStatement prepareStatement(String query) throws SQLException {
		return connection.prepareStatement(query);
	}


 }
