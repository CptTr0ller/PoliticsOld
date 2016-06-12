package com.cpttr0ller.spigotplugins.politics;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * Loads the settings so the plug-in will not fetch from files and cause some delays.
 * 
 * @author	CptTr0ller <cpttr0ller@national.shitposting.agency>
 * @version	ALPHA
 * @since	ALPHA
 */
public class Settings {
	private Main plugin;
	private FileConfiguration config;
	private int dbType;
	private String genLocale, dbFileName, dbHost, dbPort, dbName, dbUsername, dbPassword;
	
	public Settings(Main plugin, FileConfiguration config) {
		this.plugin = plugin;
		this.config = config;
		this.plugin.saveDefaultConfig();
		reload();
	}
	
	public void reload() {
		plugin.reloadConfig();
		genLocale = config.getString("general.localization");
		dbType = config.getInt("database.dbtype");
		dbFileName = config.getString("database.filename");
		dbHost = config.getString("database.host");
		dbPort = config.getString("database.port");
		dbName = config.getString("database.name");
		dbUsername = config.getString("database.username");
		dbPassword = config.getString("database.password");
	}
	
	public String getGenLocale() {
		return genLocale;
	}
	
	public int getDbType() {
		return dbType;
	}
	
	public String getDbFileName() {
		return dbFileName;
	}
	
	public String getDbHost() {
		return dbHost;
	}
	
	public String getDbPort() {
		return dbPort;
	}
	
	public String getDbName() {
		return dbName;
	}
	
	public String getDbUsername() {
		return dbUsername;
	}
	
	public String getDbPassword() {
		return dbPassword;
	}
}
