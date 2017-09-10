package me.evanog.orange.claim;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ClaimProfile {

	private Player p;
	private Location locationOne;
	private Location locationTwo;
	
	private Pillar pillarOne;
	private Pillar pillarTwo;

	public ClaimProfile(Player p) {
		this.p = p;
		this.locationOne = null;
		this.locationTwo = null;
	}

	public Location getLocationOne() {
		return locationOne;
	}

	public void setLocationOne(Location locationOne) {
		this.locationOne = locationOne;
	}

	public Location getLocationTwo() {
		return locationTwo;
	}

	public void setLocationTwo(Location locationTwo) {
		this.locationTwo = locationTwo;
	}

	public Player getP() {
		return p;
	}
	
	public Pillar getPillarOne() {
		return pillarOne;
	}
	
	public Pillar getPillarTwo() {
		return pillarTwo;
	}

	public void setPillarOne(Pillar pillarOne) {
		this.pillarOne = pillarOne;
	}

	public void setPillarTwo(Pillar pillarTwo) {
		this.pillarTwo = pillarTwo;
	}	
	
}
