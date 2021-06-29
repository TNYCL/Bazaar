package cr.shop.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import cr.clientapi.packets.PacketPlayOutGuiInput;
import cr.shop.enums.Category;
import cr.shop.menu.MaterialShopMenu;
import cr.shop.module.Order;

public class UtilMenu {
	
	public static void openCancelItemMenu(Player p, Order stock) {
		PacketPlayOutGuiInput packet = new PacketPlayOutGuiInput();
		
		packet.setInputTitle("§7İptal etmek istediğin stok miktarı:");
		List<String> desc = Arrays.asList(new String[] {
			"",
			"",
			"",
			"",
			"",
			"",
			"§eMevcut Stok: §d" + stock.getAmount(),
			"§eHepsini aktarmak için '§bhepsi§e' yaz.",
			"",
			"§c* Mevcut stoktan daha fazla bir sayı girersen işlem iptal olur.",
			""
		});
		packet.setMessages(desc);
		packet.setTitle("§e§lİptal Et");
		packet.setMaxStringLength(16);
		packet.setMinStringLength(1);
	    
		packet.send(p);
	}
	
	public static void openBuyItemMenu(Player p, Order order) {
		PacketPlayOutGuiInput packet = new PacketPlayOutGuiInput();
		
		packet.setInputTitle("§7Satın almak istediğin miktar:");
		List<String> desc = Arrays.asList(new String[] {
			"",
			"",
			"",
			"",
			"",
			"",
			"§eSatıcıdaki Stok: §d" + order.getAmount(),
			"§eHepsini satın almak için '§bhepsi§e' yaz.",
			"",
			"§c* Mevcut stoktan daha fazla bir sayı girersen işlem iptal olur.",
			""
		});
		packet.setMessages(desc);
		packet.setTitle("§e§lSatın al");
		packet.setMaxStringLength(16);
		packet.setMinStringLength(1);
	    
		packet.send(p);
	}
	
	public static void openBackMenu(Player p, Category category) {
		MaterialShopMenu.openMenu(p, category);
	}

}
