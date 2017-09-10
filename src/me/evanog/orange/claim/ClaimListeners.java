package me.evanog.orange.claim;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import me.evanog.orange.Orange;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.faction.FlatFileFactionManager;

public class ClaimListeners implements Listener {

	@EventHandler
	public void onClickBlock(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action action = e.getAction();
		if (ClaimManager.getInstance().getProfile(p) == null) {
			ClaimManager.getInstance().profiles.add(new ClaimProfile(p));
		}
		final ClaimProfile profile = ClaimManager.getInstance().getProfile(p);
		if (p.getItemInHand().equals(ClaimManager.getInstance().getClaimWand())) {
			if (FlatFileFactionManager.getInstance().getFactionByPlayer(p) == null) {
				p.sendMessage((String) LanguageFile.NO_FACTION.get());
				return;
			}
			if (p.getWorld().getEnvironment() != World.Environment.NORMAL) {
				p.sendMessage((String) LanguageFile.OVERWORLD_ONLY.get());
				return;
			}
			if (action == Action.LEFT_CLICK_BLOCK) {
				e.setCancelled(true);
				if (profile.getLocationOne() != null) {
					profile.getPillarOne().remove();
					profile.setPillarOne(
							new Pillar(p, e.getClickedBlock().getLocation().subtract(0, 1, 0), Material.DIAMOND_BLOCK));
					profile.getPillarOne().display();
					profile.setLocationOne(e.getClickedBlock().getLocation());
					p.sendMessage(((String) LanguageFile.POINT_ONE_SET.get()).replace("%cords%",
							e.getClickedBlock().getLocation().getBlockX() + ","
									+ e.getClickedBlock().getLocation().getBlockZ()));
					return;
				} else {
					profile.setLocationOne(e.getClickedBlock().getLocation());
					profile.setPillarOne(
							new Pillar(p, e.getClickedBlock().getLocation().subtract(0, 1, 0), Material.DIAMOND_BLOCK));
					profile.getPillarOne().display();
					profile.setLocationOne(e.getClickedBlock().getLocation());
					p.sendMessage(((String) LanguageFile.POINT_ONE_SET.get()).replace("%cords%",
							e.getClickedBlock().getLocation().getBlockX() + ","
									+ e.getClickedBlock().getLocation().getBlockZ()));
				}

			} else if (action == Action.RIGHT_CLICK_BLOCK) {
				e.setCancelled(true);
				if (profile.getLocationTwo() != null) {
					profile.getPillarTwo().remove();
					profile.setPillarTwo(
							new Pillar(p, e.getClickedBlock().getLocation().subtract(0, 0, 0), Material.DIAMOND_BLOCK));
					new BukkitRunnable() {
						public void run() {
							profile.getPillarTwo().display();
						}
					}.runTaskLater(Orange.getInstance(), 1L);
					profile.setLocationTwo(e.getClickedBlock().getLocation());
					p.sendMessage(((String) LanguageFile.POINT_TWO_SET.get()).replace("%cords%",
							e.getClickedBlock().getLocation().getBlockX() + ","
									+ e.getClickedBlock().getLocation().getBlockZ()));
					String m = ((String) LanguageFile.CLAIM_PRICE_MSG.get())
							.replace("%price%",
									ClaimManager.getInstance().calculateClaimPrice(profile,
											FlatFileFactionManager.getInstance().getFactionByPlayer(p), false)
											+ "");
					p.sendMessage(m);
				} else {
					profile.setLocationTwo(e.getClickedBlock().getLocation());
					profile.setPillarTwo(
							new Pillar(p, e.getClickedBlock().getLocation().subtract(0, 0, 0), Material.DIAMOND_BLOCK));

					new BukkitRunnable() {
						public void run() {
							profile.getPillarTwo().display();
						}
					}.runTaskLater(Orange.getInstance(), 1L);
					profile.setLocationTwo(e.getClickedBlock().getLocation());
					p.sendMessage(((String) LanguageFile.POINT_TWO_SET.get()).replace("%cords%",
							e.getClickedBlock().getLocation().getBlockX() + ","
									+ e.getClickedBlock().getLocation().getBlockZ()));
					String m = ((String) LanguageFile.CLAIM_PRICE_MSG.get())
							.replace("%price%",
									ClaimManager.getInstance().calculateClaimPrice(profile,
											FlatFileFactionManager.getInstance().getFactionByPlayer(p), false)
											+ "");
					p.sendMessage(m);
				}
			} else if (action == Action.RIGHT_CLICK_AIR && p.isSneaking()) {
				ClaimManager.getInstance().attemptClaim(profile,
				FlatFileFactionManager.getInstance().getFactionByPlayer(p));
			}

		}

	}

}
