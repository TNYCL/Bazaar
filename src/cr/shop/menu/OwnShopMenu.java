package cr.shop.menu;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cr.api.fakeinventory.FakeInventory;
import cr.api.fakeinventory.FakeInventory.FakeInventoryHandler;
import cr.api.fakeinventory.FakeInventoryClickEvent;
import cr.api.fakeinventory.FakeInventoryCloseEvent;
import cr.api.util.UtilItem;
import cr.api.util.UtilPlayer;
import cr.api.util.UtilString;
import cr.api.util.UtilTask;
import cr.shop.enums.ConfirmType;
import cr.shop.module.Confirm;
import cr.shop.module.Order;
import cr.shop.utils.UtilMenu;
import cr.shop.utils.UtilMongo;
import cr.shop.utils.UtilSelectedOrder;

public class OwnShopMenu {
	
	public static void openMenu(Player p) {
		UtilSelectedOrder.removeConfirmMenu(p.getName());
		
		UtilTask.makeAsync(() -> {
			Order check = UtilMongo.getShopData(Query.query(Criteria.where("owner").is(p.getName())));
			UtilTask.makeSync(() -> {
				if(check == null) {
					UtilPlayer.sendMenuErrorMessage(p, "Satışta hiç ürünün yok.");
					return;
				}
			});
		});
		
		FakeInventory inv = new FakeInventory("Satışta ki Ürünlerim", 121, new FakeInventoryHandler() {
			public void onClose(FakeInventoryCloseEvent e) {}
			@SuppressWarnings("deprecation")
			public void onClick(FakeInventoryClickEvent e) {
				ItemStack currentItem = e.getCurrentItem();
				String clicked = UtilString.removeColors(e.getCurrentItem().getItemMeta().getDisplayName());
				
				if(e.getCurrentItem().getType() == Material.ARROW && clicked.equalsIgnoreCase("geri dön")) {
					MainShopMenu.openMenu(p);
				} else {
					Query query = new Query();
					query.addCriteria(Criteria.where("owner").is(p.getName()));
					query.addCriteria(Criteria.where("material").is(currentItem.getType()));
					query.addCriteria(Criteria.where("data").is(currentItem.getData().getData()));
					
					UtilTask.makeAsync(() -> {
						Order stock = UtilMongo.getShopData(query);
						
						UtilTask.makeSync(() -> {
							UtilMenu.openCancelItemMenu(p, stock);
							UtilSelectedOrder.addConfirmMenu(p.getName(), new Confirm(stock, ConfirmType.CANCEL));
						});
					});
				}
			}
		});
		
		ItemStack back = UtilItem.createItem(Material.ARROW, 0, "§aGeri dön");
		inv.setItem(110, back);
		
		inv.setEnabledNoDisplayNameItemClick(true);
		
		UtilTask.makeAsync(() -> {
			List<Order> orders = UtilMongo.getShopData("owner", p.getName());
			
			UtilTask.makeSync(() -> {
				int i = 0;
				
				for(Order order : orders) {
					ItemStack item = new ItemStack(order.getMaterial(), 1, (byte) order.getData());
					ItemStack item_with_data = UtilItem.createItem(item.getType(), 1, item.getItemMeta().getDisplayName(), 
						"###Stok: §d" + order.getAmount() + 
						"###§7Birim Fiyatı: §6" + order.getPrice() + " Dinar       " +
						"######§eİptal etmek için tıkla",
						item.getDurability(), order.getData()
					);
					inv.setItem(i, item_with_data);
					if(i == orders.size()-1) inv.open(p);
					i++;
				}
			});
		});
	}

}
