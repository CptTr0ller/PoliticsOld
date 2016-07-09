package com.cpttr0ller.spigotplugins.utilities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a utility class to make and manage database, it can be inherited by the sub-database class that handles the interaction between plugin and database.
 * 
 * @contributor(s)	[CptTr0ller]
 * @version			ALPHA
 * @since			ALPHA
 */
public class Database {
	private Connection con;
	private PreparedStatement createTable, createColumn, createRow, deleteRow, fetchRow, fetchMultiRow;
	private int dbStatus;
	
	/**
	 * Launches database 
	 * @param dbType of the database(1 for SQLite and 2 for MySQL)
	 * @param host address of the database
	 * @param port of the database
	 * @param username of the user
	 * @param password of the user
	 * @param database name of the MySQL database
	 * @param dbFile of the SQLite database
	 */
	public Database(int dbType, String host, String port, String username, String password, String database, File dbFile) {
		try {
			if (dbType == 1) {
				if (dbFile.exists() && (dbFile != null)) {
					Class.forName("org.sqlite.JDBC");
					con = DriverManager.getConnection("jdbc:sqlite:" + dbFile.toString());
				} else {
					dbStatus = 3;
					return;
				}
			} else if (dbType == 2) {
				if ((host != null) && (port != null) && (username != null) && (password != null) && (database != null)) {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
				} else {
					dbStatus = 3;
					return;
				}
			} else {
				dbStatus = 2;
				return;
			}
			createTable = con.prepareStatement("CREATE TABLE ? (id ? PRIMARY KEY NOT NULL)");
			createColumn = con.prepareStatement("ALTER TABLE ? ADD COLUMN ? ?");
			createRow = con.prepareStatement("INSERT INTO ?(?) VALUES(?)");
			deleteRow = con.prepareStatement("DELETE FROM ? WHERE id=?");
			fetchRow = con.prepareStatement("SELECT ? FROM ? WHERE id='?'");
			fetchMultiRow = con.prepareStatement("SELECT ? FROM ?");
			dbStatus = 0;
		} catch (Exception e) {
			e.printStackTrace();
			dbStatus = 1;
			return;
		}
	}
	
	/**
	 * Closes the database
	 * @throws SQLException 
	 */
	public void close() throws SQLException {
		createTable.close();
		createColumn.close();
		createRow.close();
		fetchRow.close();
		fetchMultiRow.close();
		con.close();
	}
	
	/**
	 * Checks for last operation status
	 * @return 0 if database launched, 1 if error happened while launching database, 2 if the argument is wrong and 3 if database type not supported.
	 */
	public int getDbStatus() {
		return dbStatus;
	}
	
	/**
	 * Creates a table
	 * @param name of the table to be created
	 * @param type of id column to be created
	 * @throws SQLException 
	 */
	public void createTable(String name, String type) throws SQLException {
		createTable.setString(1, name);
		createTable.setString(2, type);
		createTable.executeUpdate();
	}
	
	/**
	 * Creates a column to table
	 * @param table to be inserted with row
	 * @param name of column to be created
	 * @param type of column to be created
	 * @throws SQLException 
	 */
	public void createColumn(String table, String name, String type) throws SQLException {
		createColumn.setString(1, table);
		createColumn.setString(2, name);
		createColumn.setString(2, type);
		createColumn.executeUpdate();
		dbStatus = 0;
	}
	
	/**
	 * Insert a row into a table with values
	 * @param table to be inserted with row
	 * @param columns to be inserted with values, separated by comma
	 * @param values to be inserted to column, separated by comma, number of values must match number of columns provided
	 * @throws SQLException 
	 */
	public void createRow(String table, String columns, String values) throws SQLException {
		if (columns.split(",").length != values.split(",").length) {
			dbStatus = 2;
			return;
		}
		createRow.setString(1, table);
		createRow.setString(2, columns);
		createRow.setString(3, values);
		createRow.executeUpdate();
		dbStatus = 0;
	}
	
	/**
	 * Insert a row into a table with values
	 * @param table with the target row
	 * @param id of the target row
	 * @throws SQLException 
	 */
	public void deleteRow(String table, String id) throws SQLException {
		deleteRow.setString(1, table);
		deleteRow.setString(2, id);
		deleteRow.executeQuery();
		dbStatus = 0;
	}
	
	/**
	 * Fetches single row, it will only get the first row if it is multiple so use fetchMultiRow if you need multiple rows, can be also used to check if row exists
	 * @param table target
	 * @param id of the row to be fetched
	 * @param columns to be fetched
	 * @return String[column]
	 * @throws SQLException 
	 */
	public String[] fetchRow(String table, String id, String... columns) throws SQLException {
		if (columns.length == 0) {
			fetchRow.setString(1, "*");
		} else {
			fetchRow.setString(1, String.join(",", columns));
		}
		fetchRow.setString(2, table);
		fetchRow.setString(3, id);
		ResultSet rs = fetchRow.executeQuery();
		if (rs.first()) {
			String[] result = new String[columns.length];
			for (int i = 0; i < columns.length; i++) {
				result[i] = rs.getString(i + 1);
			}
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * Fetches single row, it will only get the first row if it is multiple so use fetchMultiRow if you need multiple rows, can be also used to check if row exists
	 * @param table target
	 * @param whereValue to be used, example: "boo='1' AND (foo='2' OR bar='3')"
	 * @param columns to be fetched
	 * @return String[row][column]
	 * @throws SQLException 
	 */
	public String[][] fetchMultiRow(String table, String whereValue, String... columns) throws SQLException {
		if (columns.length == 0) {
			fetchRow.setString(1, "*");
		} else {
			fetchRow.setString(1, String.join(",", columns));
		}
		if (whereValue == null) {
			fetchMultiRow.setString(2, table);
		} else {
			fetchMultiRow.setString(2, table + " WHERE " + whereValue);
		}
		ResultSet rs = fetchMultiRow.executeQuery();
		int rsRows;
		if (rs.last()) {
			rsRows = rs.getRow();
			rs.beforeFirst();
		} else {
			return null;
		}
		String[][] result = new String[rsRows][columns.length];
		for (int row = 0; rs.next(); row++) {
			for (int column = 0; column < columns.length; column++) {
				result[row][column] = rs.getString(column + 1);
			}
		}
		return result;
	}
}
