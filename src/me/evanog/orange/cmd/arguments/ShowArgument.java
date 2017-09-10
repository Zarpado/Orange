package me.evanog.orange.cmd.arguments;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.evanog.orange.cmd.FactionArgument;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.faction.Faction;
import me.evanog.orange.faction.FlatFileFactionManager;
import me.evanog.orange.faction.types.PlayerFaction;
import me.evanog.orange.faction.types.SystemFaction;
import me.evanog.orange.utils.ChatUtils;

public class ShowArgument extends FactionArgument {

	public ShowArgument() {
		super("show");
	}

	@Override
	public void execute(Player p, List<String> args) {
		FlatFileFactionManager fm = FlatFileFactionManager.getInstance();
		if (args.size() == 0) {
			if (fm.hasFaction(p)) {
				this.showFaction(fm.getFactionByPlayer(p), p);
			} else {
				p.sendMessage((String) LanguageFile.NO_FACTION.get());
			}
		} else if (args.size() == 1) {
			String name = args.get(0);
			if (fm.getFactionByName(name) == null) {
				p.sendMessage((String) LanguageFile.FACTION_NOT_FOUND.get());
			}else {
				this.showFaction(fm.getFactionByName(name), p);
			}
		} else if (args.size() > 1) {
			p.sendMessage(ChatUtils.format("&c/f show <name>"));
		}
	}
	
	private void showFaction(Faction faction, Player player) {
		if (faction instanceof PlayerFaction) {
			PlayerFaction pfaction = (PlayerFaction) faction;
			List<String> pf = (List<String>) LanguageFile.FACTION_WHO.get();
			for (String s : pf) {
				s = s.replace("%faction%", faction.getName());
				s = s.replace("%leader%", Bukkit.getOfflinePlayer(pfaction.getLeader()).getName());
				s = s.replace("%balance%", pfaction.getBalance() + "");
				s = s.replace("%members%", this.memberString(pfaction));
				if (pfaction.getHome() == null) {
					s = s.replace("%home%", "None");
				} else {
					s.replace("%home%", pfaction.getHome().getBlockX() + "," + pfaction.getHome().getBlockZ());
				}
				s = s.replace("%online%", pfaction.getOnlinePlayers().size() + "").replace("%total%",
						pfaction.getMembers().size() + "");
				s = s.replace("%dtr%", pfaction.getDtr() + "");
				s = ChatUtils.format(s);
				player.sendMessage(s);
			}

		} else if (faction instanceof SystemFaction) {
			/*
			List<String> pf = (List<String>) LanguageFile.SYSTEM_WHO.get();
			SystemFaction sfaction = (SystemFaction) faction;
			for (String s : pf) {
				s = s.replaceAll("%systemcolor%", sfaction.getColor().toString());
				s = s.replace("%faction%", faction.getName());
				if (sfaction.getLocation() == null) {
					s = s.replace("%home%", "None");
				} else {
					s.replace("%home%", sfaction.getLocation().getBlockX() + "," + sfaction.getLocation().getBlockZ());
				}
				s = ChatUtils.format(s);
				player.sendMessage(s);
			}
			*/
		}
		
	}

	private ChatColor getOffOrOn(String s) {
		ChatColor off = ChatColor.valueOf((String) LanguageFile.FACTION_WHO_OFFLINE_COLOR.get());
		ChatColor on = ChatColor.valueOf((String) LanguageFile.FACTION_WHO_ONLINE_COLOR.get());
		if (Bukkit.getPlayer(s) != null) {
			return on;
		} else {
			return off;
		}
	}

	private String getPrefix(String s, PlayerFaction faction) {
		String mod = (String) LanguageFile.FACTION_WHO_MOD_PREFIX.get();
		String leader = (String) LanguageFile.FACTION_WHO_LEADER_PREFIX.get();
		if (Bukkit.getOfflinePlayer(faction.getLeader()).getName().equalsIgnoreCase(s)) {
			return leader;
		} else if (faction.getOfficers().contains(s)) {
			return mod;
		}
		return "";
	}

	private String memberString(PlayerFaction faction) {
		StringBuilder builder = new StringBuilder();
		for (String s : faction.getMembers()) {
			s = this.getOffOrOn(s) + this.getPrefix(s, faction) + s + LanguageFile.FACTION_WHO_KILLS_PREFIX.get();
			builder.append(s);
			continue;
		}
		return builder.toString();
	}
	
	

}
