package me.evanog.orange.claim;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.evanog.orange.faction.Faction;

public final class Claim {

	private Faction faction;
	private World world;
	private int x1;
	private int x2;
	private int z1;
	private int z2;
	private final int y1 = 0;
	private final int y2 = 256;

	public Claim(Faction faction, Location one, Location two) {
		this.faction = faction;
		this.world = one.getWorld();
		this.x1 = Math.min(one.getBlockX(), two.getBlockX());
		this.z1 = Math.min(one.getBlockZ(), two.getBlockZ());
		this.x2 = Math.max(one.getBlockX(), two.getBlockX());
		this.z2 = Math.max(one.getBlockZ(), two.getBlockZ());
	}

	public Location getMinimumPoint() {
		return new Location(world, x1, y1, z1);
	}

	public Location getMaximumPoint() {
		return new Location(world, x2, y2, z2);
	}

	public List<Location> getCorners() {
		List<Location> toReturn = new ArrayList<>();
		Location one = new Location(world, x1, 98, z1);
		Location two = new Location(world, x2, 98, z1);
		Location three = new Location(world, x2, 98, z2);
		Location four = new Location(world, x1, 98, z2);
		toReturn.add(one);
		toReturn.add(two);
		toReturn.add(three);
		toReturn.add(four);

		return toReturn;

	}

	public boolean contains(int x, int y, int z) {
		if (x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2) {
			return true;
		}
		return false;
	}

	public boolean contains(Location loc) {
		return this.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
	}

	public List<Player> getPlayersInsideClaim() {
		List<Player> players = new ArrayList<>();
		for (Player p : Bukkit.getOnlinePlayers()) {
			int x = p.getLocation().getBlockX();
			int y = p.getLocation().getBlockX();
			int z = p.getLocation().getBlockX();
			if (contains(x, y, z)) {
				players.add(p);
			}
		}
		return players;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getZ1() {
		return z1;
	}

	public void setZ1(int z1) {
		this.z1 = z1;
	}

	public int getZ2() {
		return z2;
	}

	public void setZ2(int z2) {
		this.z2 = z2;
	}

	public Faction getFaction() {
		return faction;
	}

	public World getWorld() {
		return world;
	}

	public int getY1() {
		return y1;
	}

}
