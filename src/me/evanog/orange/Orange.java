package me.evanog.orange;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.evanog.orange.cmd.FactionExecutor;
import me.evanog.orange.data.DataFile;
import me.evanog.orange.data.LanguageFile;
import me.evanog.orange.faction.FlatFileFactionManager;
import me.evanog.orange.utils.ChatUtils;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public final class Orange extends JavaPlugin {

	private static Orange instance;
	private Economy economy;
	private Chat chat;
	public static final String version = "1.0 ALPHA";
	private DataFile language;
	private DataFile settings;

	@Override
	public void onEnable() {
		instance = this;
		log("&8&l&m---------------------------", false);
		log("&bVersion: " + version + " &epreparing to load....", true);
		this.attemptEconomy();
		this.attemptChat();
		this.registerCommand();
		this.registerListeners();
		this.registerFiles();
		LanguageFile.setup();
		FlatFileFactionManager.getInstance().setupFactions();
		log("&eRegistered Commands/Listeners.  Now Preparing to load in Factions...", false);
		log("&asuccessfully loaded.", true);
		log("&8&l&m---------------------------", true);

	}

	@Override
	public void onDisable() {
		instance = null;
	}

	private void registerCommand() {
		this.getCommand("factions").setExecutor(new FactionExecutor());
	}

	private void registerListeners() {

	}

	private void registerFiles() {
		this.language = new DataFile("language");
		language.setup();
		this.settings = new DataFile("settings");
		settings.setup();
	}

	private void attemptEconomy() {
		if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
			log("&cVault was not found, and it is required for HCFactions to work.  Disabling HCFactions...", true);
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			log("&cVault was not found, and it is required for HCFactions to work.  Disabling HCFactions...", true);
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		economy = rsp.getProvider();
		if (economy != null) {
			log("&aSuccessfully hooked with Vault and " + rsp.getProvider().getName() + "!", true);
			return;
		} else {
			log("&cVault was not found, and it is required for HCFactions to work.  Disabling HCFactions...", true);
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
	}

	private void attemptChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();

		if (chat == null) {
			log("&cA chat plugin was not found.  It is recommended to install one for prefixes and names.", true);
			return;
		} else {
			log("&eA chat plugin was found" + " and hooked!", true);
		}
	}

	private final static String prefixLog = me.evanog.orange.utils.ChatUtils.format("&8[&6Orange&8] ");

	public static void log(String s, boolean prefix) {
		if (prefix) {
			Bukkit.getConsoleSender().sendMessage(prefixLog + ChatUtils.format(s));
		}
		Bukkit.getConsoleSender().sendMessage(ChatUtils.format(s));
	}

	public static Orange getInstance() {
		return instance;
	}

	public DataFile getLanguage() {
		return language;
	}

	public DataFile getSettings() {
		return settings;
	}

}
