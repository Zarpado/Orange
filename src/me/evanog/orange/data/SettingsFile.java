package me.evanog.orange.data;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import me.evanog.orange.Orange;
import me.evanog.orange.utils.ChatUtils;

public enum SettingsFile {

	CLAIM_WAND_MATERIAL("Claim_Wand_Material", "GOLD_HOE"),

	CLAIM_WAND_NAME("Claim_Wand_Name", "&aClaiming Wand"),

	CLAIM_WAND_LORE("Claim_Wand_Lore", Arrays.asList("&9Left Click&e block to set point 1", "&9Right Click &eblock to set point 2",
					"&9Shift-Right Click&e Air to attempt claim purchase",
					"&9Shift-Left Click&e to clear claim selection"));

	private String path;
	private Object defaultValue;

	private SettingsFile(String path, Object defaultValue) {
		this.path = path;
		this.defaultValue = defaultValue;
	}

	public Object get() {
		DataFile language = Orange.getInstance().getSettings();
		if (language.getConfig().get(this.path) instanceof String) {
			return ChatUtils.format(language.getConfig().getString(this.path));
		} else {
			return language.getConfig().get(this.path);
		}
	}

	public String getPath() {
		return path;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

}
