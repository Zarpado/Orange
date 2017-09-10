package me.evanog.orange.cmd.arguments;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.evanog.orange.cmd.FactionArgument;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.faction.FlatFileFactionManager;

public class CreateArgument extends FactionArgument {

	public CreateArgument() {
		super("create");
	}

	@Override
	public void execute(Player p, List<String> args) {
		FlatFileFactionManager fmanager = FlatFileFactionManager.getInstance();
		if (args.size() == 1) {
			String name = args.get(0);
			if (fmanager.getFactionByName(name) == null) {
				if (!fmanager.hasFaction(p)) {
					fmanager.createPlayerFaction(name, p);
					p.sendMessage((String) ((String) LanguageFile.FACTION_CREATE_MESSAGE.get()).replace("%faction%",name));
					Bukkit.broadcastMessage((String) ((String) LanguageFile.FACTION_CREATE_BROADCAST.get()).replace("%faction%",name).replace("%player%",p.getName()));
				}else {
					p.sendMessage((String) LanguageFile.ALREADY_HAS_FACTION.get());
				}
			}else {
				p.sendMessage((String) LanguageFile.FACTION_ALREADY_EXISTS.get());
			}
		}else {
			this.sendUsage(p, "/f create <factionName>");
		}
	}
	
	

	
	
}
