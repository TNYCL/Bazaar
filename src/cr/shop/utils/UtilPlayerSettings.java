package cr.shop.utils;

import java.util.ArrayList;
import java.util.List;

import cr.shop.enums.AutoSell;
import cr.shop.module.PlayerSettings;
import cr.shop.module.Settings;

public class UtilPlayerSettings {
	
	public static PlayerSettings setupSettings(String playerName) {
		List<Settings> settingsList = new ArrayList<>();
		for(AutoSell items : AutoSell.values()) {
			settingsList.add(new Settings(false, items));
		}
		return new PlayerSettings(playerName, settingsList);
	}

}
