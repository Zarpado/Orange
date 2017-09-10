package me.evanog.orange.cmd.arguments;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.evanog.orange.cmd.FactionArgument;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.faction.FlatFileFactionManager;
import me.evanog.orange.faction.Role;
import me.evanog.orange.faction.types.PlayerFaction;
import me.evanog.orange.utils.ChatUtils;

public class RenameArgument extends FactionArgument {

	public RenameArgument() {
		super("rename", Arrays.asList());
	}

	@Override
	public void execute(Player p, List<String> args) {
		FlatFileFactionManager fm = FlatFileFactionManager.getInstance();
		if (args.size() == 1) {
			if (fm.hasFaction(p)) {
				if (Role.isAtLeast(Role.MODERATOR, p)) {
					{
						PlayerFaction faction = (PlayerFaction) fm.getFactionByPlayer(p);
						String newName = args.get(0);
						String oldName = faction.getName();
						if (!newName.equals(oldName)) {
							faction.rename(newName);
							faction.broadcast((String) ((String) LanguageFile.FACTION_RENAMED_FACTION_BROADCAST.get()).replace("%player%", p.getName()).replace("%name%", newName));
							Bukkit.broadcastMessage((String) ((String) LanguageFile.FACTION_RENAMED_SERVER_BROADCAST.get())
									.replace("%oldname%", oldName).replace("%newname%", newName));
						} else {
							p.sendMessage(ChatUtils.format("&cNew name must be different from the old name!"));
						}

					}
				} else {
					p.sendMessage(ChatUtils.format("&cYou must be at least MODERATOR in your faction to execute this command!"));
				}
			} else {
				p.sendMessage((String) LanguageFile.NO_FACTION.get());
			}
		} else {
			this.sendUsage(p, "/f rename <name>");
		}
	}

}
