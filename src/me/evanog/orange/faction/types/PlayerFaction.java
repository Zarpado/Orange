package me.evanog.orange.faction.types;

import java.util.ArrayList; 
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.evanog.orange.claim.Claim;
import me.evanog.orange.data.DataFile;
import me.evanog.orange.data.SettingsFile;
import me.evanog.orange.faction.Faction;
import me.evanog.orange.faction.FlatFileFactionManager;
import me.evanog.orange.utils.ChatUtils;

public class PlayerFaction extends Faction {

	private UUID leader;
	private List<String> invitedPlayers;
	private List<String> officers;
	private List<String> members;
	private List<String> allies; 
	private Location home;
	private boolean open;
	private double dtr;
	private int balance;
	private long frozenTime;
	private List<String> requestedAllies;
	private DataFile file;
	
	
	public PlayerFaction(String name,Player leader) {
		super(name);
		this.leader = leader.getUniqueId();
		this.invitedPlayers = new ArrayList<>();
		this.officers = new ArrayList<>();
		this.members = new ArrayList<>();
		members.add(leader.getName());
		this.allies = new ArrayList<>();
		this.requestedAllies = new ArrayList<>();
		this.home = null;
		this.open = false;
		this.dtr = 1.1;
		this.balance = 0;
		this.frozenTime = 0;
		this.file = new DataFile(name, FlatFileFactionManager.getInstance().getPlayerFactionsFolder());
		if (!file.getFile().exists()) {
			file.setup();
		}
	}


	public PlayerFaction(int id,String name, long timeCreated, String description, Set<Claim> claims, UUID leader, List<String> invitedPlayers, List<String> officers,
			List<String> members, List<String> allies, Location home, boolean open, double dtr, int balance,
			long frozenTime, List<String> requestedAllies, DataFile file) {
		super(id,name,timeCreated,description,claims);
		this.setName(name);
		this.setId(id);
		this.setTimeCreated(timeCreated);
		this.setDescription(description);
		this.setClaims(claims);
		this.leader = leader;
		this.invitedPlayers = invitedPlayers;
		this.officers = officers;
		this.members = members;
		this.allies = allies;
		this.home = home;
		this.open = open;
		this.dtr = dtr;
		this.balance = balance;
		this.frozenTime = frozenTime;
		this.requestedAllies = requestedAllies;
		this.file = file;
	}
	
	public void broadcast(String msg) {
		for (String s : this.getMembers()) {
			if (Bukkit.getPlayer(s) != null) {
				Bukkit.getPlayer(s).sendMessage(ChatUtils.format(msg));
			}
		}
	}
	
	public List<Player> getOnlinePlayers() {
		List<Player> players = new ArrayList<>();
		for (String s : this.getMembers()) {
			if (Bukkit.getPlayer(s) != null) {
				players.add(Bukkit.getPlayer(s));
			}
		}
		return players;
	}
	
	public void serialize() {
		
	}
	
	public void rename(String name) {
		this.setName(name);
	}


	public UUID getLeader() {
		return leader;
	}


	public void setLeader(UUID leader) {
		this.leader = leader;
	}


	public List<String> getInvitedPlayers() {
		return invitedPlayers;
	}


	public void setInvitedPlayers(List<String> invitedPlayers) {
		this.invitedPlayers = invitedPlayers;
	}


	public List<String> getOfficers() {
		return officers;
	}


	public void setOfficers(List<String> officers) {
		this.officers = officers;
	}


	public List<String> getMembers() {
		return members;
	}


	public void setMembers(List<String> members) {
		this.members = members;
	}


	public List<String> getAllies() {
		return allies;
	}


	public void setAllies(List<String> allies) {
		this.allies = allies;
	}


	public Location getHome() {
		return home;
	}


	public void setHome(Location home) {
		this.home = home;
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}


	public double getDtr() {
		return dtr;
	}


	public void setDtr(double dtr) {
		this.dtr = dtr;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	public long getFrozenTime() {
		return frozenTime;
	}


	public void setFrozenTime(long frozenTime) {
		this.frozenTime = frozenTime;
	}


	public List<String> getRequestedAllies() {
		return requestedAllies;
	}


	public void setRequestedAllies(List<String> requestedAllies) {
		this.requestedAllies = requestedAllies;
	}


	public DataFile getFile() {
		return file;
	}


	public void setFile(DataFile file) {
		this.file = file;
	}
	
	
	
	

	
	
	
}
