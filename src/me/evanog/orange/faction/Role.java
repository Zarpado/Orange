package me.evanog.orange.faction;

import org.bukkit.entity.Player;

import me.evanog.orange.faction.types.PlayerFaction;

public enum Role {

	LEADER(1), MODERATOR(2), MEMBER(3);

	private int id;

	private Role(int id) {
		this.id = id;
	}

	public static Role getRole(Player p, PlayerFaction fac) {
		if (fac.getOfficers().contains(p.getName())) {
			return Role.MODERATOR;
		} else if (fac.getLeader().equals(p.getUniqueId())) {
			return Role.LEADER;
		} else {
			return Role.MEMBER;
		}
	}

	public int getId() {
		return id;
	}

	public static boolean isAtLeast(Role must, Player p) {
		if (FlatFileFactionManager.getInstance().hasFaction(p)) {
			if (getRole(p, (PlayerFaction) FlatFileFactionManager.getInstance().getFactionByPlayer(p)).getId() <= must
					.getId()) {
				return true;
			}
		}
		return false;
	}

}
