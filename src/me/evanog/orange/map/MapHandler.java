package me.evanog.orange.map;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

public class MapHandler {

	private static Set<Player> viewing = new HashSet<>();
	
	public static static void addPlayer(Player p) {
		if (isViewing(p) == false) {
			viewing.add(p);
			
			
		}
	}
	
	public static boolean isViewing(Player p) {
		return viewing.contains(p);
	}
	
	
	
}
