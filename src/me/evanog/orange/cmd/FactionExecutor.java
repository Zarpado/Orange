package me.evanog.orange.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.evanog.orange.cmd.arguments.ClaimArgument;
import me.evanog.orange.cmd.arguments.CreateArgument;
import me.evanog.orange.cmd.arguments.HelpArgument;
import me.evanog.orange.cmd.arguments.RenameArgument;
import me.evanog.orange.cmd.arguments.ShowArgument;
import me.evanog.orange.cmd.arguments.VersionArgument;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.utils.ChatUtils;

public class FactionExecutor implements CommandExecutor {

	private static List<FactionArgument> args = new ArrayList<>();

	public FactionExecutor() {
		args.add(new HelpArgument());
		args.add(new VersionArgument());
		args.add(new CreateArgument());
		args.add(new ShowArgument());
		args.add(new RenameArgument());
		args.add(new ClaimArgument());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("factions")) {
				if (args.length == 0) {
					for (String s : ChatUtils.formatList((Iterable<String>) LanguageFile.FACTION_HELP_PAGE_1.get())) {
						p.sendMessage(s);
					}
					return false;
				}

				FactionArgument arg = this.getArgument(args[0]);
				if (arg == null) {
					return false;
				}
				List<String> newList = new ArrayList<>(Arrays.asList(args));
				newList.remove(0);
	
				arg.execute(p, newList);

				
				
			}
		}
		return false;
	}

	private FactionArgument getArgument(String s) {
		for (FactionArgument arg : args) {
			if (arg.getArgument().equalsIgnoreCase(s)) {
				return arg;
			}
		}
		for (FactionArgument arg : args) {
			if (arg.getAliases().contains(s)) {
				return arg;
			}
		}
		return null;
	}
	
}
