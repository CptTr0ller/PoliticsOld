package com.cpttr0ller.spigotplugins.politics.datatypes;

/**
 * Superclass for all entities
 * 
 * @author	CptTr0ller <cpttr0ller@national.shitposting.agency>
 * @version	ALPHA
 * @since	ALPHA
 */
public abstract class Entity {
	protected String name;
	protected boolean active;
	
	public String getName() {
		return name;
	}
	
	public boolean isActive() {
		return active;
	}
}
