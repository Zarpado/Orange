package me.evanog.orange.data;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.evanog.orange.Orange;

public class DataFile {

	private String name;
	private final String EXT = ".yml";
	private File folder;
	private File file;
	private FileConfiguration config;
	private final static Orange plugin;

	public static Set<DataFile> dataFiles = new HashSet<>();
	
	static {
		plugin = Orange.getInstance();
		if (plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
	}

	public DataFile(String name) {
		this.name = name;
		this.folder = Orange.getInstance().getDataFolder();
		this.file = new File(folder, name + EXT);
	}
	
	public DataFile(String name, File folder) {
		this.name = name;
		this.folder = folder;
		this.file = new File(folder,name + EXT);
	}
	
	public void setup() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(file);
		this.save();
		this.onCreate();
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reload() {
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void addDefault(String path, Object obj) {
		this.config.addDefault(path, obj);
		if (!config.options().copyDefaults()) {
			config.options().copyDefaults(true);
		}
		this.save();
	}
	
	public Object get(String path) {
		return config.get(path);
	}
	
	public void set(String path, Object value) {
		config.set(path, value);
		this.save();
	}
	
	public void onCreate() {
		
	}
	
	public File getFile() {
		return file;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
	public String getName() {
		return name;
	}
	
	public File getFolder() {
		return folder;
	}
	
	public static DataFile getDataFileByName(String name,File folder) {
		for (DataFile file : dataFiles) {
			if (file.getName().equals(name) && file.getFolder().equals(folder)) {
				return file;
			}
		}
		return null;
	}
	
}
