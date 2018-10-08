package java01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDB {
 public Connection connection;
 static String databaseName = "";
 static String url= "jdbc:mysql://localhost:3306/" +databaseName;
 
 static String username = "root";
 static String password = "";
 
public ConnectDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
 
	 Class.forName("com.mysql.jdbc.Driver").newInstance();
	 connection = DriverManager.getConnection(url, username, password);
}
 }
