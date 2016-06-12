package com.cpttr0ller.spigotplugins.politics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import com.cpttr0ller.spigotplugins.politics.datatypes.Territory;

public final class Events implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	public void enteringRegion(PlayerMoveEvent event) {
		if ((event.getFrom().getBlockX() != event.getTo().getBlockX()) && (event.getFrom().getBlockY() != event.getTo().getBlockY()) && (event.getFrom().getBlockZ() != event.getTo().getBlockZ())) {
			
		} else {
			return;
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void loadingChunks(ChunkLoadEvent event) {
		if (event.isNewChunk()) {
			Main.getInstance().getTerritoryStorage().registerTerritory(new Territory(event.getChunk()));
		} else {
			return;
		}
	}
}
