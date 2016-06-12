package com.cpttr0ller.spigotplugins.politics.datatypes;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * Class for managing territories.(chunks owned by a state)
 * 
 * @author	CptTr0ller <cpttr0ller@national.shitposting.agency>
 * @version	ALPHA
 * @since	ALPHA
 */
public class Territory {
	private World world;
	private Chunk chunk;
	private int x;
	private int z;
	private Vector minPoint;
	private Vector maxPoint;
	
	public Territory(Chunk chunk) {
		this.chunk = chunk;
		world = chunk.getWorld();
		x = chunk.getX();
		z = chunk.getZ();
		minPoint = new Vector(x*16, 0, z*16);
		maxPoint = new Vector(x*16+15, 255, z*16+15);
	}
	
	public Chunk getChunk() {
		return chunk;
	}
	
	public World getWorld() {
		return world;
	}
	
	public int getX() {
		return x;
	}
	
	public int getZ() {
		return z;
	}
	
	public Location getMinPoint() {
		return minPoint.toLocation(getWorld());
	}
	
	public Location getMaxPoint() {
		return maxPoint.toLocation(world);
	}
}
