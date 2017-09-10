package me.evanog.orange.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

	public static String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static List<String> formatList(Iterable<String> list) {
		List<String> toReturn = new ArrayList<>();
		for (String s : list) {
			toReturn.add(format(s));
		}
		return toReturn;
	}
	
	public static void sendMessage(Player p, String s) {
		p.sendMessage(format(s));
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
}
