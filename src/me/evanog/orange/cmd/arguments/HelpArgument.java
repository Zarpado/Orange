package me.evanog.orange.cmd.arguments;

import java.util.List;

import org.bukkit.entity.Player;

import me.evanog.orange.cmd.FactionArgument;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.utils.ChatUtils;

public class HelpArgument extends FactionArgument {

	public HelpArgument() {
		super("help");
	}

	@Override
	public void execute(Player p, List<String> args) {
		if (args.size() == 1) {
			if (ChatUtils.isInt(args.get(0))) {
				this.sendHelp(Integer.valueOf(args.get(0)), p);
			}
		}
		
	}

	private void sendHelp(int i, Player p) {
		if (i == 1) {
			for (String s : ChatUtils.formatList((Iterable<String>) LanguageFile.FACTION_HELP_PAGE_1.get())) {
				p.sendMessage(s);
			}
		}else if (i == 2) {
			for (String s : ChatUtils.formatList((Iterable<String>) LanguageFile.FACTION_HELP_PAGE_2.get())) {
				p.sendMessage(s);
			}
		}else if (i == 3) {
			for (String s : ChatUtils.formatList((Iterable<String>) LanguageFile.FACTION_HELP_PAGE_3.get())) {
				p.sendMessage(s);
			}
		}else {
			p.sendMessage(ChatUtils.format("/f help <1-3>"));
		}
	}

	
	
	
	
}
