package cr.shop;

import org.bukkit.plugin.java.JavaPlugin;

import cr.essentials.CREssentials;
import cr.essentials.economy.CREconomy.EconomyType;
import cr.shop.commands.Tüccar;
import cr.shop.listener.MenuPacketListener;
import cr.shop.listener.PlayerLoginListener;

public class CRShop extends JavaPlugin {
	
	public static CRShop instance; 
	
	public void onEnable() {
		instance = this;
		registerCommand();
		registerListener();
		CREssentials.setupEconomy(EconomyType.ZRANGE);
		CREssentials.getEconomy().setMaxPayMoney(20000000);
	}
	
	public void registerCommand() {
		getCommand("tüccar").setExecutor(new Tüccar());
	}
	
	public void registerListener() {
		getServer().getPluginManager().registerEvents(new MenuPacketListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
	}
	
	public static CRShop getInstance() {
		return instance;
	}

}
