package com.cpttr0ller.spigotplugins.politics.datatypes;

import org.bukkit.entity.Player;

public final class Denizen extends Entity {
	public Denizen(Player player) {
		this.active = player.isOnline();
	}
}
