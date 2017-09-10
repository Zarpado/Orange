package me.evanog.orange.cmd.arguments;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import me.evanog.orange.cmd.FactionArgument;

public class MapArgument extends FactionArgument {

	private static Set<Player> viewing = new HashSet<Player>();
	
	public MapArgument() {
		super("map");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player p, List<String> args) {
		
	}

	
	
}
