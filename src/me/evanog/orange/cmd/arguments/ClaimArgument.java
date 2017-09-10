package me.evanog.orange.cmd.arguments;

import java.util.List;

import org.bukkit.entity.Player;

import me.evanog.orange.claim.ClaimManager;
import me.evanog.orange.cmd.FactionArgument;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.faction.types.PlayerFaction;

public class ClaimArgument extends FactionArgument {

	public ClaimArgument() {
		super("claim");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Player p, List<String> args) {
		if (this.fm().hasFaction(p)) {
			PlayerFaction fac = (PlayerFaction) this.fm().getFactionByPlayer(p);
			if (ClaimManager.getInstance().isClaiming(p)) {
				ClaimManager.getInstance().disableClaimMode(p);
			}else {
				ClaimManager.getInstance().enableClaimMode(p, fac);
			}
		}else {
			p.sendMessage((String) LanguageFile.NO_FACTION.get());
		}
	}
	
	

}
