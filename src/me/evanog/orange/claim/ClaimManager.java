package me.evanog.orange.claim;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.data.SettingsFile;
import me.evanog.orange.faction.Faction;
import me.evanog.orange.faction.FlatFileFactionManager;
import me.evanog.orange.faction.types.PlayerFaction;
import me.evanog.orange.faction.types.SystemFaction;
import me.evanog.orange.utils.ChatUtils;
import me.evanog.orange.utils.ItemBuilder;

public class ClaimManager {

	private ClaimManager() {
	}

	private static ClaimManager instance = new ClaimManager();
	private Map<Player, Faction> claiming = new HashMap<>();
	protected Set<ClaimProfile> profiles = new HashSet<>();

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
			p.sendMessage(((String) LanguageFile.CLAIMING_MODE_ENABLED.get()).replace("%faction%", fac.getName()));
		}
	}

	public void disableClaimMode(Player p) {
		if (!isClaiming(p)) {
			return;
		}
		claiming.remove(p);
		p.getInventory().remove(this.getClaimWand());
		p.sendMessage((String) LanguageFile.CLAIM_MODE_DISABLED.get());
	}

	public void attemptClaim(ClaimProfile profile, Faction faction) {
		Player p = profile.getP();
		if (profile.getLocationOne() != null && profile.getLocationTwo() != null) {
			double distance = profile.getLocationOne().distance(profile.getLocationTwo());
			if (distance < (int) SettingsFile.CLAIM_MIN_SIZE.get()) {
				// profile.getP().sendMessage((String)
				// ConfigFactory.CLAIM_MIN_SIZE_MSG.get());
				return;
			} else {
				Claim claim = new Claim(faction, profile.getLocationOne(), profile.getLocationTwo());

				if (this.containsClaim(claim, profile) == true) {
					p.sendMessage("claim inside another error");
					return;
				}

				if (this.obeysBuffer(claim, profile) == false && faction instanceof PlayerFaction) {
					p.sendMessage("does not obey buffer");
					return;
				}

				if (this.conjoins(profile, faction) == false && faction instanceof PlayerFaction
						&& faction.getClaims().isEmpty() == false) {
					p.sendMessage("claim must conjoin!!");
					return;
				}

				/*
				if (faction instanceof PlayerFaction) {
					PlayerFaction pf = (PlayerFaction) faction;
					int price = this.calculateClaimPrice(profile, faction, false);
					if (price > pf.getBalance()) {
						p.sendMessage((String) LanguageFile.LACKING_FACTION_MONEY.get());
						return;
					}

				}
				*/
				if (faction instanceof PlayerFaction) {
					PlayerFaction pf = (PlayerFaction) faction;
					pf.broadcast("claim purchased!");
					//pf.removeFunds(this.calculateClaimPrice(profile, faction, false));
				}
				p.sendMessage("claim purchased" +
						this.calculateClaimPrice(profile, faction, false) + "");
				profile.getPillarOne().remove();
				profile.getPillarTwo().remove();
				faction.addClaim(claim);
				;

				profiles.remove(profile);

			}

		} else {
			profile.getP().sendMessage((String) LanguageFile.SET_POINTS.get());
		}
	}

	private boolean obeysBuffer(Claim claim, ClaimProfile profile) {
		int minimumX = claim.getX1();
		int maximumX = claim.getX2();
		int minimumZ = claim.getZ1();
		int maximumZ = claim.getZ2();
		int buffer = (int) SettingsFile.CLAIM_BUFFER.get();
		for (int xxx = minimumX - buffer; xxx < maximumX + buffer; xxx++) {
			for (int zzz = minimumZ - buffer; zzz < maximumZ + buffer; zzz++) {
				Location loc = new Location(profile.getP().getWorld(), xxx, 97, zzz);
				Faction facAt = FlatFileFactionManager.getInstance().getFactionAt(loc);
				if (facAt instanceof PlayerFaction && facAt.equals(claim.getFaction()) == false) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean conjoins(ClaimProfile profile, Faction faction) {
		if (faction instanceof SystemFaction) {
			return true;
		}
		Location toCheck = profile.getLocationOne();
		for (Claim claim : faction.getClaims()) {
			if (claim.contains(toCheck.getBlock().getRelative(BlockFace.NORTH).getLocation())) {
				return true;
			}
			if (claim.contains(toCheck.getBlock().getRelative(BlockFace.SOUTH).getLocation())) {
				return true;
			}
			if (claim.contains(toCheck.getBlock().getRelative(BlockFace.EAST).getLocation())) {
				return true;
			}
			if (claim.contains(toCheck.getBlock().getRelative(BlockFace.WEST).getLocation())) {
				return true;
			}
		}
		return false;

	}

	private boolean containsClaim(Claim claim, ClaimProfile profile) {
		int minimumX = claim.getX1();
		int maximumX = claim.getX2();
		int minimumZ = claim.getZ1();
		int maximumZ = claim.getZ2();

		for (int xx = minimumX; xx < maximumX; xx++) {
			for (int zz = minimumZ; zz < maximumZ; zz++) {
				if (FlatFileFactionManager.getInstance()
						.getFactionAt(new Location(profile.getP().getWorld(), xx, 98, zz)) != FlatFileFactionManager.getInstance().getWilderness()) {
					return true;
				}
			}
		}
		return false;

	}

	public ClaimProfile getProfile(Player p) {
		for (ClaimProfile profile : profiles) {
			if (profile.getP().getName().equals(p.getName())) {
				return profile;
			}
		}
		return null;
	}

	public Set<ClaimProfile> getProfiles() {
		return profiles;
	}

	public int calculateClaimPrice(ClaimProfile profile, Faction fac, boolean sell) {
		if (profile.getLocationOne() == null || profile.getLocationTwo() == null) {
			return 0;
		}
		if (fac instanceof me.evanog.orange.faction.types.SystemFaction) {
			return 0;

		}
		int price = (int) Math.round(profile.getLocationOne().distance(profile.getLocationTwo())
				* (int) SettingsFile.CLAIM_PRICE_MULTIPLIER.get());
		if (sell) {
			return (int) Math.round(price * 0.8);
		}
		return price;

	}

	public ItemStack getClaimWand() {
		List<String> lore = ChatUtils.formatList((Iterable<String>) SettingsFile.CLAIM_WAND_LORE.get());
		return new ItemBuilder(Material.valueOf((String) SettingsFile.CLAIM_WAND_MATERIAL.get()))
				.setName(ChatUtils.format((String) SettingsFile.CLAIM_WAND_NAME.get())).setLore(lore).toItemStack();
	}

}
