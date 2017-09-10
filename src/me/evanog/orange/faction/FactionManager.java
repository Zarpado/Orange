package me.evanog.orange.faction;

import java.util.Set;

import org.bukkit.entity.Player;

import me.evanog.orange.faction.types.PlayerFaction;
import me.evanog.orange.faction.types.SystemFaction;

public interface FactionManager {
	
	/*
	 * Method to setup the factions folder, along with playerfactions and systemfactions folders.
	 */
	public void setupFactions();
	
	/*
	 * Create a PlayerFaction with the specified name.  Sets the leader to @param leader.
	 */
	public void createPlayerFaction(String name, Player leader);
	
	/*
	 * Create a PlayerFaction with the specified name.  Sets the leader to @param leader.
	 */
	public void createSystemFaction(String name);
	
	/*
	 * Returns if a @param p has a faction or not.
	 */
	public boolean hasFaction(Player p);
		
	/*
	 * Returns a faction with @param name.
	 */
	public Faction getFactionByName(String name);
	
	/*
	 * Returns a faction thats members contain @param p's name.
	 */
	public Faction getFactionByPlayer(Player p);
	
	/*
	 * Loads in all factions.
	 */
	public void loadFactions();
	
	/*
	 * Saves all factions.
	 */
	public void saveFactions();
	
	/*
	 * Returns all the factions in the system.
	 */
	public Set<Faction> getFactions();
	
	/*
	 * Returns all the PlayerFactions in getFactions();
	 */
	public Set<PlayerFaction> getPlayerFactions();
	
	/*
	 * Returns all the SystemFactions in getFactions();
	 */
	public Set<SystemFaction> getSystemFactions();
}
