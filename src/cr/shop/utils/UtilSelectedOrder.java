package cr.shop.utils;

import java.util.concurrent.ConcurrentHashMap;

import cr.shop.module.Confirm;

public class UtilSelectedOrder {
	
public static ConcurrentHashMap<String, Confirm> selectedOrder = new ConcurrentHashMap<String, Confirm>();
	
	public static Confirm getConfirmMenu(String playerName) {
		return selectedOrder.getOrDefault(playerName, null);
	}
	
	public static void addConfirmMenu(String playerName, Confirm data) {
		selectedOrder.put(playerName, data);
	}
	
	public static void removeConfirmMenu(String playerName) {
		if(getConfirmMenu(playerName) == null) return;
		selectedOrder.remove(playerName);
	}

}
