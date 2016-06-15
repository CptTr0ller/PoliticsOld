package com.cpttr0ller.spigotplugins.utilities;

import com.cpttr0ller.spigotplugins.PluginUtilities;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

/**
 * Manages economy, chat and permissions
 * 
 * @contributor(s)	[CptTr0ller]
 * @version			ALPHA
 * @since			ALPHA
 */
public final class Vault {
	private static Chat chat = null;
	private static Economy econ = null;
	private static Permission perm = null;
	
	public Vault() {
		chat = PluginUtilities.getPlugin().getServer().getServicesManager().getRegistration(Chat.class).getProvider();
		econ = PluginUtilities.getPlugin().getServer().getServicesManager().getRegistration(Economy.class).getProvider();
		perm = PluginUtilities.getPlugin().getServer().getServicesManager().getRegistration(Permission.class).getProvider();
	}
	
	public void close() {
		chat = null;
		econ = null;
		perm = null;
	}
	
	public  short getStatus() {
		if (PluginUtilities.getPlugin().getServer().getPluginManager().getPlugin("Vault") == null) {
			return 2;
		} else if (chat == null || econ == null || perm == null) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public Chat getChat() {
		return chat;
	}
	
	public Economy getEcon() {
		return econ;
	}
	
	public Permission getPerm() {
		return perm;
	}
}
