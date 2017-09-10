package me.evanog.orange.faction.types;

import java.util.Set;

import org.bukkit.ChatColor;

import me.evanog.orange.claim.Claim;
import me.evanog.orange.faction.Faction;

public class SystemFaction extends Faction {

	private ChatColor color;
	private boolean deathban = true;
	
	public SystemFaction(String name, ChatColor color,Boolean deathban) {
		super(name);
		this.color = color;
		this.deathban = deathban;
	}
	
	public SystemFaction(int id, String name, long timeCreated, String description, Set<Claim> claims) {
		super(id, name, timeCreated, description, claims);
		this.deathban = false;
		
	}
	
	

}
