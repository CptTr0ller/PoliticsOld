package com.cpttr0ller.spigotplugins.politics;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.cpttr0ller.spigotplugins.politics.databases.Territories;
import com.cpttr0ller.spigotplugins.utilities.Database;
import com.cpttr0ller.spigotplugins.utilities.Localization;

/**
 * This is main plugin class extending JavaPlugin, it calls out other class for function of the plugin.
 * 
 * @author	CptTr0ller <cpttr0ller@national.shitposting.agency>
 * @version	ALPHA
 * @since	ALPHA
 */
public final class Main extends JavaPlugin {
	private static Main instance;
	private Settings st;
	private Localization lc;
	private Database db;
	private Territories territoryStorage;
	
	@Override
	public void onEnable() {
		instance = this;
		st = new Settings(this, this.getConfig());
		File localeFolder = new File(this.getDataFolder() + File.pathSeparator + "localization");
		if (!localeFolder.exists()) {
			localeFolder.mkdir();
		}
		lc = new Localization("en");
		db = new Database(getSt().getDbType(), getSt().getDbHost(), getSt().getDbPort(), getSt().getDbName(), getSt().getDbUsername(), getSt().getDbPassword(), new File(getDataFolder(), getSt().getDbFileName()));
		if (db.getDbStatus() != 0) {
			getLogger().severe("Something wrong happened while launching database, error code " + db.getDbStatus());
		}
		territoryStorage = new Territories(this, getDb());
		getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public Settings getSt() {
		return st;
	}
	
	public Database getDb() {
		return db;
	}
	
	public Territories getTerritoryStorage() {
		return territoryStorage;
	}
}
