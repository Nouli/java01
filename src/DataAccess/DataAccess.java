package DataAccess;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Statement;
import Exception.AppDataAccessException;
import Exception.UserNotFoundException;
import SqlUtils.ConnectDB;
import java01.entity.Entity;

public class DataAccess {
	public DataAccess() {
		super();
	}

	// SqlUtils Update
	ConnectDB connectDB = ConnectDB.getInstance();

	public <T extends Entity> void update(T t, Long id) throws UserNotFoundException, AppDataAccessException {
		List<Field> privateFields = new ArrayList<>();
		Field[] allFields = t.getClass().getDeclaredFields();
		connectDB.setDatabaseName(t.getClass().getSimpleName());
		String databaseName = connectDB.getDatabaseName();
		try {
			select(t.getClass(), id);
			String query1 = "SELECT * FROM " + databaseName + "";
			Statement st = ConnectDB.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query1);
			String query = "UPDATE " + databaseName + " SET";
			if (rs != null) {
				ResultSetMetaData columns = rs.getMetaData();
				int i = 0;
				while (rs.next() && i == 0) {
					int j = 1;
					while (j < columns.getColumnCount()) {
						j++;
						for (Field field : allFields) {
							if (Modifier.isPrivate(field.getModifiers())) {
								privateFields.add(field);
								if (field.getName().toString().equals(columns.getColumnLabel(j))) {
									field.setAccessible(true);
									query += " " + "`" + columns.getColumnLabel(j) + "`=" + "'" + field.get(t) + "' ,";
								}
							}

						}
					}
					i++;
				}

			}
			query = query.substring(0, query.length() - 2);
			query += "where ( id = " + id + ")";
			PreparedStatement ps = connectDB.getConnection().prepareStatement(query);
			ps.executeUpdate();

		} catch (UserNotFoundException e) {
			throw new UserNotFoundException();
		} catch (AppDataAccessException | SQLException e) {
			throw new AppDataAccessException();
		} catch (IllegalAccessException e) {
			throw new AppDataAccessException();
		}

	}

	// SqlUtils Insert
	public <T extends Entity> void insert(T t) throws AppDataAccessException {
		ArrayList<String> results = new ArrayList<String>();
		List<Field> privateFields = new ArrayList<>();
		Field[] allFields = t.getClass().getDeclaredFields();

		try {
			connectDB.setDatabaseName(t.getClass().getSimpleName());
			String databaseName = connectDB.getDatabaseName();
			String query1 = "SELECT * FROM utilisateur";
			String query = "INSERT INTO " + databaseName + " (";
			Statement st = ConnectDB.getConnection().createStatement();
			ResultSet rs = (ResultSet) st.executeQuery(query1);
			if (rs != null) {
				ResultSetMetaData columns = rs.getMetaData();
				int i = 1;
				while (i < columns.getColumnCount()) {
					i++;
					query += columns.getColumnLabel(i) + ", ";
					results.add(columns.getColumnLabel(i));
				}
				query = query.substring(0, query.length() - 2);
				query += ") VALUES (";
				for (Field field : allFields) {
					if (Modifier.isPrivate(field.getModifiers())) {
						privateFields.add(field);

						if (results.contains(field.getName().toString())) {
							field.setAccessible(true);
							field.get(t);
							query += " '" + field.get(t) + "' ,";

						}
					}

				}

				query = query.substring(0, query.length() - 2);
				query += ")";
				PreparedStatement ps = ConnectDB.getConnection().prepareStatement(query);
				ps.executeUpdate();
			}
		} catch (SQLException | IllegalAccessException e) {
			throw new AppDataAccessException();
		}
	}

	// SqlUtils Delete :
	public <T extends Entity> void delete(Class<T> c, Long id) throws UserNotFoundException, AppDataAccessException {
		try {
			select(c, id);
			connectDB.setDatabaseName(c.getSimpleName());
			String databaseName = connectDB.getDatabaseName();
			String query = "DELETE FROM  " + databaseName + "  WHERE id = ?";
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement(query);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException();
		} catch (AppDataAccessException | SQLException e) {
			throw new AppDataAccessException();
		}
	}

	public <T extends Entity> T select(Class<T> type, Long id) throws AppDataAccessException, UserNotFoundException {
		T t;
	
		Object ob = null;
		try {
			t = type.newInstance();
			List<Field> privateFields = new ArrayList<>();
			Field[] allFields = type.getDeclaredFields();

			// Execute SQL query
			connectDB.setDatabaseName(type.getSimpleName());
			String databaseName = connectDB.getDatabaseName();
			String query = "SELECT * FROM " + databaseName + " Where id=";
			Statement st = ConnectDB.getConnection().createStatement();
			ResultSet rs = st.executeQuery(query + id);
			if (rs != null) {
				
				ResultSetMetaData columns = rs.getMetaData();
				int count = columns.getColumnCount();
				while (rs.next()) {
					HashMap<String, Object> cols = new HashMap<String, Object>(count);
					int j = 0;
					while (j < columns.getColumnCount()) {
						j++;
						ob = rs.getObject(j);
						if (rs.wasNull()) {
							ob = null;
						}
						cols.put(columns.getColumnLabel(j), ob);
						for (Field field : allFields) {
							if (Modifier.isPrivate(field.getModifiers())) {
								privateFields.add(field);
								if (field.getName().toString().equals(columns.getColumnLabel(j))) {

									field.setAccessible(true);
									if (field.getType().getName().equals("java.lang.Long")){
										Long a = (long) rs.getLong(j);
										field.set(t,a);
										}
									else if (field.getType().isEnum()) {
										String value = (String) rs.getObject(j);
										field.set(t, Enum.valueOf((Class<Enum>) field.getType(), value));
									} else {
										field.set(t, rs.getObject(j));

									}
								}

							}
						}

					}
				}
			}
		} catch (SQLException e) {
			throw new AppDataAccessException();
		} catch (InstantiationException | IllegalAccessException e1) {
			throw new AppDataAccessException();
		}

		if (ob == null) {
			throw new UserNotFoundException();
		}
		return t;

	}
	public <T extends Entity> ArrayList<T> findAll(Class<T> type) throws  AppDataAccessException, UserNotFoundException {
		ArrayList<T> entities = new ArrayList<T>();
		// Execute SQL query
		T t;
		Long ob = null;
		try {
		t = type.newInstance();
		String databaseName = connectDB.getDatabaseName();
		connectDB.setDatabaseName(t.getClass().getSimpleName());
		databaseName = connectDB.getDatabaseName();
		String query = "SELECT * FROM "+databaseName+" order by id";
		Statement st = ConnectDB.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				ob = rs.getLong("id");
				if (rs.wasNull()) {
					ob = null;
				}
				entities.add(select(type, ob));
			}
		}catch(SQLException e) {
			throw new AppDataAccessException();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new AppDataAccessException();
		} 
	if (ob == null) {
		throw new UserNotFoundException();
	}
	return entities;
	}
}