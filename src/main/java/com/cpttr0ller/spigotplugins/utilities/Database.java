package com.cpttr0ller.spigotplugins.utilities;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is a utility class to make and manage database, it can be inherited by the sub-database class that handles the interaction between plugin and database.
 * 
 * @author	CptTr0ller <cpttr0ller@national.shitposting.agency>
 * @version	ALPHA
 * @since	ALPHA
 */
public class Database {
	private Connection con;
	private PreparedStatement createTable, createColumn, fetchSingleRow;
	private int dbStatus;
	
	/**
	 * Launches database 
	 * @param dbType states database type
	 * @param host address of the database
	 * @param port port of the database
	 * @param username 
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
			createTable = con.prepareStatement("CREATE TABLE ? (? ? PRIMARY KEY NOT NULL)");
			createColumn = con.prepareStatement("ALTER TABLE ? ADD COLUMN ? ?");
			fetchSingleRow = con.prepareStatement("SELECT ? WHERE ?=?");
			dbStatus = 0;
		} catch (Exception e) {
			e.printStackTrace();
			dbStatus = 1;
			return;
		}
	}
	
	/**
	 * Closes the database
	 */
	public void close() {
		try {
			con.close();
			createTable.close();
			createColumn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	/**
	 * Checks for database status
	 * @return 0 if database launched, 1 if error happened while launching database, 2 if the argument is wrong and 3 if database type not supported.
	 */
	public int getDbStatus() {
		return dbStatus;
	}
	
	public void createTable(String tableName, String idName, String idDataType) {
		try {
			createTable.setString(1, tableName);
			createTable.setString(2, idName);
			createTable.setString(3, idDataType);
			createTable.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void createColumn(String tableName, String columnName, String columnType) {
		try {
			createColumn.setString(1, tableName);
			createColumn.setString(2, columnName);
			createColumn.setString(2, columnType);
			createColumn.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	
}
