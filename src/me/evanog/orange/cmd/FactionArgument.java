package me.evanog.orange.cmd;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.evanog.orange.faction.FlatFileFactionManager;

public abstract class FactionArgument {

	private String arg;
	private List<String> aliases;
	
	public FactionArgument(String arg) {
		this.arg = arg;
		this.aliases = Arrays.asList();
		
	}
	
	public FactionArgument(String arg, List<String> aliases) {
		this.arg = arg;
		this.aliases = aliases;
		
	}
	
	
	public abstract void execute(Player p,List<String> args);
	
	public FlatFileFactionManager fm() {
		return FlatFileFactionManager.getInstance();
	}
	
	public String getArgument() {
		return arg;
	}
	
	public List<String> getAliases() {
		return aliases;
	}
	
	public void sendUsage(Player p, String usage) {
		p.sendMessage(ChatColor.RED + usage);
	}
}
