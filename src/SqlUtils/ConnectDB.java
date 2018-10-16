package SqlUtils;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Vector;

public class ConnectDB {
	private static Connection connection = null;

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

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
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

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		ConnectDB.connection = connection;
	}
}