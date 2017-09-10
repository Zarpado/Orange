package me.evanog.orange.cmd.arguments;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.evanog.orange.Orange;
import me.evanog.orange.claim.Claim;
import me.evanog.orange.cmd.FactionArgument;
import me.evanog.orange.faction.FlatFileFactionManager;
import me.evanog.orange.utils.ChatUtils;

public class VersionArgument extends FactionArgument {

	public VersionArgument() {
		super("version");
	}

	private List<String> list = Arrays.asList(
			"&8&m--*------------------------*--",
			"&eThis server is running &eOrange&7HCF&E!",
			"&aDeveloper: &7EvanOG",
			"&aRunning Version: &7" + Orange.version,
			"",
			"&7&oVisit my website: evantheog.github.io",
			"&8&m--*------------------------*--"
			);

	@Override
	public void execute(Player p, List<String> args) {
		for (String s : list) {
			p.sendMessage(ChatUtils.format(s));
		}
	}
	
	
}
