package cr.shop.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cr.api.fakeinventory.FakeInventory;
import cr.api.fakeinventory.FakeInventory.FakeInventoryHandler;
import cr.api.fakeinventory.FakeInventoryClickEvent;
import cr.api.fakeinventory.FakeInventoryCloseEvent;
import cr.api.util.UtilItem;
import cr.api.util.UtilString;
import cr.essentials.CREssentials;
import cr.shop.enums.Category;
import cr.shop.module.Order;
import cr.shop.utils.UtilOrder;

public class ConfirmShopMenu {
	
	public static void openSelectedMenu(Player p, Order order, int amount) {
		Category category = order.getCategory();
		ItemStack item = new ItemStack(order.getMaterial(), 1, (byte) order.getData());
		
		FakeInventory inv = new FakeInventory("§cSatın alınıyor...", 27, new FakeInventoryHandler() {
			public void onClose(FakeInventoryCloseEvent e) {}
			public void onClick(FakeInventoryClickEvent e) {
				String clicked = UtilString.removeColors(e.getCurrentItem().getItemMeta().getDisplayName());
				if(clicked.equalsIgnoreCase("vazgeçtim")) {
					MaterialShopMenu.openSelectedMenu(p, item, category);
				}
				if(clicked.equalsIgnoreCase("satın alıyorum")) {
					UtilOrder.buyItem(p, amount);
					MaterialShopMenu.openSelectedMenu(p, item, category);
				}
			}
		});
		ItemStack space = UtilItem.createItem(Material.STAINED_GLASS_PANE, 0, "", "", 15, 15);
		for(int i=0;i<inv.size;i++) {
			inv.setItem(i, space);
		}
		long price = amount * order.getPrice();
		ItemStack orderItem = UtilItem.createItem(order.getMaterial(), amount, null,
				"###Satıcı: §e" + order.getOwner() + "        " +
				"###Satın Alınan Stok: §d" + amount +
				"###Ödenecek Dinar: §6" + price +
				"######Satın Alım Sonrası Dinar: §6" + (CREssentials.getEconomy().getPlayerMoney(p.getName()) - price)
		);
		inv.setItem(4, orderItem);
		ItemStack cancel = UtilItem.createItem(Material.WOOL, 0, "§cVazgeçtim",
				"§eSatın alma kararımdan vazgeçtim.   ", 14, 14
		);
		inv.setItem(11, cancel);
		ItemStack accept = UtilItem.createItem(Material.WOOL, 0, "§aSatın Alıyorum",
				"§eYukarıda belirtilen ürünü satın almayı kabul ediyorum." + 
				"###§eSatın aldıktan sonra geri iade işlemi kesinlikle yapılamaz.     ",
				5, (byte) 5
		);
		inv.setItem(15, accept);
		
		inv.setEnabledNoDisplayNameItemClick(true);
		
		inv.open(p);
	}

}
