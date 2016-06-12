package com.cpttr0ller.spigotplugins.politics.databases;

import com.cpttr0ller.spigotplugins.politics.Main;
import com.cpttr0ller.spigotplugins.politics.datatypes.Territory;
import com.cpttr0ller.spigotplugins.utilities.Database;

public final class Territories {
	private Main plugin;
	private Database db;
	
	public Territories(Main plugin, Database db) {
		this.plugin = plugin;
		this.db = db;
		db.createTable("territories", "", "");
	}
	
	public Main getPlugin() {
		return plugin;
	}
	
	public Database getDb() {
		return db;
	}
	
	public void registerTerritory(Territory territory) {
		
	}
}
