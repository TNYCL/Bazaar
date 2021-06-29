package cr.shop.module;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import cr.shop.enums.AutoSell;

public class PlayerSettings {
	
	public static ConcurrentHashMap<String, PlayerSettings> data = new ConcurrentHashMap<>();
	
	public static PlayerSettings getPlayerSettings(String playerName) {
		return data.getOrDefault(playerName, null);
	}
	
	public static void updatePlayerSettings(String playerName, PlayerSettings settings) {
		data.replace(playerName, settings);
	}
	
	public static void addPlayerSettings(String playerName, PlayerSettings settings) {
		data.put(playerName, settings);
	}
	
	public static void removePlayerSettings(String playerName) {
		data.remove(playerName);
	}
	
	private String playerName;
	private List<Settings> settings;
	
	public PlayerSettings(String playerName, List<Settings> settings) {
		this.playerName = playerName;
		this.settings = settings;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public List<Settings> getListSettings() {
		return this.settings;
	}
	
	public Settings getSettings(AutoSell settings) {
		return this.settings.stream().filter(data -> data.getSelected() == settings).findFirst().orElse(null);
	}
	
	public void addSettings(Settings settings) {
		this.settings.add(settings);
	}

}
