package me.evanog.orange.claim;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Pillar {

	private Player displayed;
	private Location location;
	private Material material;

	public Pillar(Player displayed, Location location, Material material) {
		this.displayed = displayed;
		this.location = location;
		this.material = material;
	}

	public void display() {
		final ArrayList<Integer> ints = new ArrayList<>(); 		
		final int x = this.location.getBlockX();
	        final int z = this.location.getBlockZ();
	        for (int i = 0; i <= this.getLocation().getWorld().getMaxHeight(); i++) {
	            final Location location = new Location(this.getLocation().getWorld(), (double)x, (double)i, (double)z);
	            if (location.getBlock().getType() == Material.AIR && displayed != null) {
	                if (ints.contains(location.getBlockY())) {
	                   displayed.sendBlockChange(location, material, (byte)0 );
	                   displayed.sendBlockChange(location.add(0.0, 2.0, 0.0), Material.GLASS, (byte)0);
	                }
	                else {
	                   	displayed.sendBlockChange(location, Material.GLASS, (byte)0);
	                    ints.add(location.getBlockY() + 2);
	                }
	            }
	        }
	}

	public void remove() {
		Location loc = null;
		for (int i = 0; i < location.getWorld().getMaxHeight(); i++) {
			loc = new Location(location.getWorld(), location.getBlockX(), i, location.getBlockZ());
			if (loc.getBlock().getType() == Material.AIR) {
				displayed.sendBlockChange(loc, material.AIR, (byte) 0);
			}
		}
	}

	public Player getDisplayed() {
		return displayed;
	}

	public Location getLocation() {
		return location;
	}

	public Material getMaterial() {
		return material;
	}
	
}
