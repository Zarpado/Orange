package me.evanog.orange.claim;

import java.util.Arrays; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.evanog.orange.faction.Faction;
import me.evanog.orange.utils.ChatUtils;
import me.evanog.orange.utils.ItemBuilder;

public class ClaimManager {

	private ClaimManager() {
	}

	private static ClaimManager instance = new ClaimManager();
	private Map<Player, Faction> claiming = new HashMap<>();

	public static ClaimManager getInstance() {
		return instance;
	}

	public boolean isClaiming(Player p) {
		return claiming.containsKey(p);
	}

	public void enableClaimMode(Player p, Faction fac) {
		if (this.isClaiming(p)) {
			return;
		} else {
			p.getInventory().addItem(this.getClaimWand());
			claiming.put(p, fac);
			//p.sendMessage(((String) LanguageFile.CLAIMING_MODE_ENABLED.get()).replace("%faction%", fac.getName()));
		}
	}
	
	public void disableClaimMode(Player p) {
		if (!isClaiming(p)) {
			return;
		}
		claiming.remove(p);
		p.getInventory().remove(this.getClaimWand());
		//p.sendMessage((String) LanguageFile.CLAIM_MODE_DISABLED.get());
	}
	
	public ItemStack getClaimWand() {
		List<String> lore = Arrays.asList("&9Left Click&e block to set point 1", "&9Right Click &eblock to set point 2",
					"&9Shift-Right Click&e Air to attempt claim purchase",
					"&9Shift-Left Click&e to clear claim selection");
		return new ItemBuilder(Material.GOLD_HOE).setName(ChatUtils.format("&aClaiming Wand")).setLore(ChatUtils.formatList(lore)).toItemStack();
	}

}
