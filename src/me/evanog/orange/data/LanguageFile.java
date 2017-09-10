package me.evanog.orange.data;

import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;

import me.evanog.orange.Orange;
import me.evanog.orange.utils.ChatUtils;

public enum LanguageFile {

	FACTION_HELP_PAGE_1("Messages.FACTION_HELP_1", Arrays.asList("&8&m--*--------------------------*--",
			"&eOrange &7HCF &8(&a1/3&8)", "&8&m--*--------------------------*--", "&6/f help <1-3> &7- shows commands",
			"&6/f version &7- shows version of Orange installed", "&6/f create <name> &7- create a faction",
			"&6/f disband <name> &7- disband your faction", "&6/f rename <name> &7- rename your faction",
			"&6/f show <faction> &7- show info on a faction",
			"&6/f invite <player> &7- invite a player to your faction", "&8&m--*--------------------------*--")),

	FACTION_HELP_PAGE_2("Messages.FACTION_HELP_2", Arrays.asList("&8&m--*--------------------------*--",
			"&eOrange &7HCF &8(&a2/3&8)", "&8&m--*--------------------------*--",
			"&6/f claim &7- claim land for your faction", "&6/f unclaim &7- unclaim the claim you are standing in",
			"&6/f deposit <amount> &7- deposit money into f bank",
			"&6/f deposit all &7- deposit all your balance into f bank",
			"&6/f withdraw <amount> &7- take money out of your f bank", "&6/f home &7- teleport to your faction home",
			"&6&6/f sethome &7- set your factions home", "&8&m--*--------------------------*--")),

	FACTION_HELP_PAGE_3("Messages.FACTION_HELP_3", Arrays.asList("&8&m--*--------------------------*--",
			"&eOrange &7HCF &8(&a3/3&8)", "&8&m--*--------------------------*--",
			"&6/f stuck &7- teleport out of enemy land", "&6/f ally <faction> &7- send ally request",
			"&6/f enemy <faction> &7- enemy an ally faction", "&6/f promote <player> &7- promote a player to moderator",
			"&6/f leader <player> &7- promote a player to leader", "&6/f chat &7- change chat channel of yourself",
			"&6&6/f kick <player> &7- kick player from your faction", "&8&m--*--------------------------*--")),

	FACTION_ALREADY_EXISTS("Messages.FACTION_ALREADY_EXISTS", "&CThere is already a faction with that name!"),

	ALREADY_HAS_FACTION("Messages.ALREADY_HAS_FACTION", "&CYou are already apart of a faction!"),

	FACTION_CREATE_BROADCAST("Messages.FACTION_CREATE_BROADCAST",
			"&eFaction &a%faction% has been created by &9%player%."),

	FACTION_CREATE_MESSAGE("Messages.FACTION_CREATE_MESSAGE",
			"&eYou have created a faction called &b%faction%&r&e.  Do /f show to view details on your faction."),

	FACTION_WHO_ONLINE_COLOR("Settings.FACTION_WHO_ONLINE_COLOR", "GREEN"),

	NO_FACTION("Messages.NO_FACTION", "&cYou do not have a faction!"),

	FACTION_WHO_OFFLINE_COLOR("Messages.FACITON_WHO_OFFLINE_COLOR", "RED"),

	FACTION_WHO_LEADER_PREFIX("Messages.FACTION_WHO_LEADER_PREFIX", "**"),

	FACTION_WHO_MOD_PREFIX("Messages.FACTION_WHO_MOD_PREFIX", "*"),

	FACTION_WHO_KILLS_PREFIX("Messages.FACTION_WHO_KILLS_PREFIX", "&7[&c%kills%&7]"),

	FACTION_WHO("Messages.FACTION_WHO",
			Arrays.asList("&6&m-------------------------------", "&9%faction% &7[%online%/%total%]  &eHome: %home%",
					"&eLeader » &7%leader%", "&eBalance » &9$%balance%", "&eMembers » %members%", "&eDTR » &6%dtr%",
					"&6&m------------------------------")),

	SYSTEM_WHO("Messages.SYSTEM_WHO", new String[] { "&6&m-------------------------------", "%systemcolor%%faction%",
			"&eType » &7System Faction", "&eLocation » %home%", "&6&m------------------------------" }),

	FACTION_NOT_FOUND("Messages.FACTION_NOT_FOUND", "&CFaction not found!"),

	FACTION_RENAMED("Messages.FACTION_RENAMED", "&EYou have renamed your faction to &a%name%&e!"),

	FACTION_RENAMED_FACTION_BROADCAST("Messages.FACTION_RENAMED_FACTION_BROADCAST",
			"&E%player% has renamed your faction to %name%!"),

	FACTION_RENAMED_SERVER_BROADCAST("Messages.FACTION_RENAMED_SERVER_BROADCAST",
			"&eFaction &7%oldname% &ahas been renamed to &9%newname%&a."),

	CLAIMING_MODE_ENABLED("Messages.CLAIMING_MODE_ENABLED",
			"&eYou have enabled claiming mode.  You are now claiming land for &c%faction%.  Use your claim wand to select claim points.  To disable claim mode, do /f claim again."),

	CLAIM_MODE_DISABLED("Messages.CLAIM_MODE_DISABLED", "&cClaim mode has been disabled!"),
	
	OVERWORLD_ONLY("Messages.OVERWORLD_ONLY", "&cYou can only claim land in the overworld."),
	
	POINT_ONE_SET("Messages.POINT_ONE_SET", "&AClaim point 1 set at &8[&e%cords%&8]&a."),
	POINT_TWO_SET("Messages.POINT_TWO_SET", "&AClaim point 2 set at &8[&e%cords%&8]&r&a."),
	
	CLAIM_PRICE_MSG("Messages.CLAIM_PRICE_MSG", "&eThis claim will cost &a&n%price%&r&e. "),
	
	SET_POINTS("Messages.SET_POINTS", "&cPlease set both points before attempting to claim!");

	private String path;
	private Object defaultValue;

	private LanguageFile(String path, Object defaultValue) {
		this.path = path;
		this.defaultValue = defaultValue;
	}

	public static void setup() {
		DataFile language = Orange.getInstance().getLanguage();
		language.set("test", "hey");
		language.save();

		for (LanguageFile factory : LanguageFile.values()) {
			if (language.get(factory.getPath()) == null) {
				language.set(factory.getPath(), factory.getDefaultValue());
			}
		}
		language.save();
	}

	public Object get() {
		DataFile language = Orange.getInstance().getLanguage();
		if (language.getConfig().get(this.path) instanceof String) {
			return ChatUtils.format(language.getConfig().getString(this.path));
		} else {
			return language.getConfig().get(this.path);
		}
	}

	public String getPath() {
		return path;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}
}
