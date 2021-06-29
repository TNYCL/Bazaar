package cr.shop.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import cr.api.util.UtilTask;
import cr.shop.module.PlayerSettings;
import cr.shop.utils.UtilMongo;
import cr.shop.utils.UtilPlayerSettings;

public class PlayerLoginListener implements Listener {

	@EventHandler
	public void onJoin(AsyncPlayerPreLoginEvent e) {
		String playerName = e.getPlayerProfile().getName();
		
		UtilTask.makeAsync(() -> {
			PlayerSettings data = UtilMongo.getSettingsData(Query.query(Criteria.where("playerName").is(playerName)));
			if(data == null) {
				data = UtilPlayerSettings.setupSettings(playerName);
				PlayerSettings.addPlayerSettings(playerName, data);
				return;
			}
			PlayerSettings.addPlayerSettings(playerName, data);
		});
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		String playerName = e.getPlayer().getName();
		
		UtilTask.makeAsync(() -> {
			PlayerSettings data = UtilMongo.getSettingsData(Query.query(Criteria.where("playerName").is(playerName)));
			if(data == null) {
				data = PlayerSettings.getPlayerSettings(playerName);
				UtilMongo.insertSettingsData(data);
				return;
			}
			UtilMongo.updateSettingsData(Query.query(Criteria.where("playerName").is(playerName)), Update.update("settings", PlayerSettings.getPlayerSettings(playerName).getListSettings()));
		});
	}
	
}
