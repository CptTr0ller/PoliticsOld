package com.cpttr0ller.spigotplugins.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.cpttr0ller.spigotplugins.PluginUtilities;

public class Localization { 
	private String localeFileName;
	private InputStream is;
	private static Properties prop;
	
	public Localization(String locale) {
		localeFileName = "translations_" + locale + ".properties";
		is = null;
		prop = new Properties();
		load();
	}
	
	public void load() {
		try {
			File customFile = new File(PluginUtilities.getPlugin().getDataFolder(), localeFileName);
			if (customFile.exists()) {
				is = new FileInputStream(customFile);
			} else {
				is = getClass().getClassLoader().getResourceAsStream(localeFileName);
			}
			if (is == null) {
				return;
			}
			prop.load(is);
		} catch(Exception e) {
			close();
			return;
		}
	}
	
	public void close() {
		try {
			is.close();
			prop = null;
		} catch (Exception e) {
			
		}
	}
	
	public void reload() {
		try {
			is.close();
		} catch (Exception e) {
			
		}
		load();
	}
	
	public void changeLocale(String locale) {
		localeFileName = "translations_" + locale;
	}
	
	public static String localize(String message, String[] args) {
		return prop.getProperty(message);
	}
	
	private String formatMessage(String message, String[] value) {
		String result = prop.getProperty(message);
		for (int i=0; i<value.length; i++) {
			result = result.replaceAll("[" + i + "]", value);
		}
	}
}
