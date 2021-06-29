package cr.shop.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import cr.api.CRAPI;
import cr.api.util.UtilPlayer;
import cr.api.util.UtilTime;
import cr.clientapi.events.ClientPacketEvent;
import cr.clientapi.packets.PacketPlayInGuiInput;
import cr.shop.enums.ConfirmType;
import cr.shop.module.Order;
import cr.shop.utils.UtilOrder;
import cr.shop.utils.UtilSelectedOrder;

public class MenuPacketListener implements Listener {

	@EventHandler
	public void onEnterAmount(ClientPacketEvent e) {
		Player p = e.getPlayer();
		String packetName = e.getPacketName();

		if (packetName.equalsIgnoreCase("PacketPlayInGuiInput")) {
			PacketPlayInGuiInput packet = CRAPI.getGson().fromJson(e.getPacketContent().toJSONString(),
					PacketPlayInGuiInput.class);
			
			String input = packet.getInput();
			
			if(!UtilTime.isNumeric(input) && !(input.equalsIgnoreCase("hepsi"))) {
				UtilPlayer.sendErrorMessage(p, "Girilen miktar sadece sayı olmalı.");
				return;
			}
			
			ConfirmType type = UtilSelectedOrder.getConfirmMenu(p.getName()).getType();
			Order orderData = UtilSelectedOrder.getConfirmMenu(p.getName()).getOrder();
			
			if(type == ConfirmType.CANCEL) {
				if(input.equalsIgnoreCase("hepsi")) UtilOrder.cancelItem(p, orderData.getAmount());
				else UtilOrder.cancelItem(p, Integer.parseInt(packet.getInput()));
			} else if(type == ConfirmType.BUY) {
				if(input.equalsIgnoreCase("hepsi")) UtilOrder.buyItem(p, orderData.getAmount());
				else UtilOrder.buyItem(p, Integer.parseInt(packet.getInput()));
			}
		}
	}

}
