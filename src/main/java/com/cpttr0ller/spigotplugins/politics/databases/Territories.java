package com.cpttr0ller.spigotplugins.politics.databases;

import com.cpttr0ller.spigotplugins.politics.Main;
import com.cpttr0ller.spigotplugins.politics.datatypes.Territory;
import com.cpttr0ller.spigotplugins.utilities.Database;

/**
 * Database handler for territories.
 * 
 * @author	CptTr0ller <cpttr0ller@national.shitposting.agency>
 * @version	ALPHA
 * @since	ALPHA
 */
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
