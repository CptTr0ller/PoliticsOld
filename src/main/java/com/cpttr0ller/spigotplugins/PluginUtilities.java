package com.cpttr0ller.spigotplugins;

import com.cpttr0ller.spigotplugins.politics.Main;

/**
 * This class is used to get the dependancy of utility classes.
 * 
 * @author	CptTr0ller <cpttr0ller@national.shitposting.agency>
 * @version	ALPHA
 * @since	ALPHA
 */
public class PluginUtilities {
	public static Main getPlugin() {
		return Main.getInstance();
	}
}
