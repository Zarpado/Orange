package me.evanog.orange.faction;

import java.util.HashSet;
import java.util.Set;

import me.evanog.orange.claim.Claim;

public class Faction {

	/*
	 * Base Faction Class Contains info that every faction will have. id -
	 * special unique number that you can use to get info about a faction, Stays
	 * same throughout name changes. name - another identifier, but can be
	 * changed timeCreated - stores the time in which the faction was created.
	 * Can be converted to M-D-Y format.
	 */

	private int id;
	private String name;
	private long timeCreated;
	private String description;
	private Set<Claim> claims;

	public Faction(String name) {
		this.name = name;
		this.id = FlatFileFactionManager.getInstance().getFactions().size() + 1;
		this.timeCreated = System.currentTimeMillis();
		this.description = "Edit me using /f desc <desc>";
		this.claims = new HashSet<>();
	}

	public Faction(int id, String name, long timeCreated, String description, Set<Claim> claims) {
		this.id = id;
		this.name = name;
		this.timeCreated = timeCreated;
		this.description = description;
		this.claims = claims;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public long getTimeCreated() {
		return timeCreated;
	}

	public Set<Claim> getClaims() {
		return claims;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addClaim(Claim claim) {
		this.claims.add(claim);
	}

	public void setTimeCreated(long timeCreated) {
		this.timeCreated = timeCreated;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((claims == null) ? 0 : claims.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (timeCreated ^ (timeCreated >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faction other = (Faction) obj;
		if (other.getId() == this.getId()) {
			return true;
		}
		return false;
	}

}
