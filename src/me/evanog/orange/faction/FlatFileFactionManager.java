package me.evanog.orange.faction;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

import me.evanog.orange.Orange;
import me.evanog.orange.faction.types.PlayerFaction;
import me.evanog.orange.faction.types.SystemFaction;

public class FlatFileFactionManager implements FactionManager {

	private static Set<Faction> factions = new HashSet<>();
	
	private static FlatFileFactionManager instance = new FlatFileFactionManager();

	private FlatFileFactionManager() {
		//prevent instantiation
	}
	
	@Override
	public void setupFactions() {
		File database = new File(Orange.getInstance().getDataFolder(), "factions");
		database.mkdirs();
		new File(database, "player-factions").mkdirs();
		new File(database, "system-factions").mkdirs();
	}
	
	@Override
	public void createPlayerFaction(String name, Player leader) {
		System.out.print(factions.size());
		PlayerFaction pf = new PlayerFaction(name,leader);
		factions.add(pf);			
	}

	@Override
	public void createSystemFaction(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasFaction(Player p) {
		for (PlayerFaction pf : this.getPlayerFactions()) {
			if (pf.getMembers().contains(p.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Faction getFactionByName(String name) {
		for (Faction fac : factions) {
			if (fac.getName().equalsIgnoreCase(name)) {
				return fac;
			}
		}
		
		return null;
	}

	@Override
	public Faction getFactionByPlayer(Player p) {
		for (Faction fac : factions) {
			if (fac instanceof PlayerFaction) {
				PlayerFaction pf = (PlayerFaction) fac;
				if (pf.getMembers().contains(p.getName())) {
					return pf;
				}
			}
		}
		
		return null;
	}

	@Override
	public void loadFactions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveFactions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Faction> getFactions() {
		return factions;
	}

	@Override
	public Set<PlayerFaction> getPlayerFactions() {
		Set<PlayerFaction> toReturn = new HashSet<>();
		for (Faction fac : factions) {
			if (fac instanceof PlayerFaction) {
				toReturn.add((PlayerFaction) fac);
			}
		}
		return toReturn;
	}

	@Override
	public Set<SystemFaction> getSystemFactions() {
		Set<SystemFaction> toReturn = new HashSet<>();
		for (Faction fac : factions) {
			if (fac instanceof SystemFaction) {
				toReturn.add((SystemFaction) fac);
			}
		}
		return toReturn;
	}
	
	public File getPlayerFactionsFolder() {
		File database = new File(Orange.getInstance().getDataFolder(), "factions");
		return new File(database, "player-factions");
	}
	
	public File getSystemFactionsFolder() {
		File database = new File(Orange.getInstance().getDataFolder(), "factions");
		return new File(database, "system-factions");
	}

	public static FlatFileFactionManager getInstance() {
		return instance; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
