package cr.shop.menu;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
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
import cr.essentials.CREssentials;
import cr.shop.enums.Category;
import cr.shop.enums.ConfirmType;
import cr.shop.enums.ShopOptions;
import cr.shop.module.Confirm;
import cr.shop.module.Order;
import cr.shop.utils.UtilMenu;
import cr.shop.utils.UtilMongo;
import cr.shop.utils.UtilSelectedOrder;
import cr.shop.utils.UtilSkyblock;

public class MaterialShopMenu {
	
	public static void openMenu(Player p, Category category) {
		List<ItemStack> categoryItems = ShopOptions.getCategoryItems(category);
		
		FakeInventory inv = new FakeInventory("Tüccar » " + category.getCategoryName(), 121, new FakeInventoryHandler() {
			public void onClose(FakeInventoryCloseEvent e) {}
			public void onClick(FakeInventoryClickEvent e) {
				ItemStack currentItem = e.getCurrentItem();
				String clickedName = UtilString.removeColors(currentItem.getItemMeta().getDisplayName());
				
				if(clickedName == null) {
					UtilTask.makeAsync(() -> {
						Query query = new Query();
						query.addCriteria(Criteria.where("material").is(currentItem.getType()));
						query.addCriteria(Criteria.where("data").is(currentItem.getDurability()));
						int amount = (int) UtilMongo.getShopDataCount(query);
						
						UtilTask.makeSync(() -> {
							if(amount == 0) {
								UtilPlayer.sendMenuErrorMessage(p, "Bu ürünün stoğu yok.");
							} else {
								openSelectedMenu(p, currentItem, category);
							}
						});
					});
				}else if(clickedName.equalsIgnoreCase("geri dön")) {
					MainShopMenu.openMenu(p);
					return;
				}
			}
		});
		ItemStack back = UtilItem.createItem(Material.ARROW, 0, "§aGeri dön");
		inv.setItem(110, back);
		
		inv.setEnabledNoDisplayNameItemClick(true);
		
		int i = 0;
		for(ItemStack items : categoryItems) {
			int a = i;
			
			UtilTask.makeAsync(() -> {
				Query query = new Query();
				query.addCriteria(Criteria.where("material").is(items.getType()));
				query.addCriteria(Criteria.where("data").is(items.getDurability()));
				
				int amount_data = (int) UtilMongo.getShopDataCount(query);
				
				UtilTask.makeSync(() -> {
					int amount = amount_data;
					if(amount > 64) amount = 64;
					
					ItemStack item = UtilItem.createItem(items.getType(), amount, items.getItemMeta().getDisplayName(), 
							"###Satıcı Sayısı: §e" + String.valueOf(UtilMongo.getShopDataCount(query)) + (amount == 0 ? "######§cBu ürünün stoğu yok    " : "######§eAçmak için tıkla    "), items.getDurability(), items.getDurability()
					);
						
					inv.setItem(a, item);
					if(a == categoryItems.size()-1) inv.open(p);
				});
			});
			i++;
		}
	}
	
	public static void openSelectedMenu(Player p, ItemStack material, Category category) {
		UtilSelectedOrder.removeConfirmMenu(p.getName());
		
		FakeInventory inv = new FakeInventory("Tüccar » " + category.getCategoryName(), 121, new FakeInventoryHandler() {
			public void onClose(FakeInventoryCloseEvent e) {}
			public void onClick(FakeInventoryClickEvent e) {
				ItemStack currentItem = e.getCurrentItem();
				String clickedName = UtilString.removeColors(e.getCurrentItem().getItemMeta().getDisplayName());
				if(clickedName == null) {
					String owner = UtilSkyblock.getItemOwner(currentItem.getItemMeta().getLore().get(1));
					
					if(owner.equals("SEN")) {
						UtilPlayer.sendMenuErrorMessage(p, "§cKendi ürününü satın alamazsın.");
						return;
					}
					
					Query query = new Query();
					query.addCriteria(Criteria.where("owner").is(owner));
					query.addCriteria(Criteria.where("material").is(currentItem.getType()));
					query.addCriteria(Criteria.where("data").is(currentItem.getDurability()));
					
					UtilTask.makeAsync(() -> {
						Order order = UtilMongo.getShopData(query);
						
						UtilTask.makeSync(() -> {
							int amount = order.getAmount() >= 64 ? 64 : order.getAmount();
							amount = e.getClickType() == ClickType.SHIFT_LEFT ? amount : 1;
							long totalPrice = amount * order.getPrice();
							
							if(e.getClickType() != ClickType.SHIFT_RIGHT) {
								if(CREssentials.getEconomy().getPlayerMoney(p.getName()) < totalPrice) {
									UtilPlayer.sendMenuErrorMessage(p, "§b" + amount + " §cadet ürünün fiyatını karşılamıyorsun.");	
									return;
								}
							}
							if(e.getClickType() == ClickType.SHIFT_LEFT) ConfirmShopMenu.openSelectedMenu(p, order, amount);
							else if(e.getClickType() == ClickType.LEFT) ConfirmShopMenu.openSelectedMenu(p, order, 1);
							else if(e.getClickType() == ClickType.SHIFT_RIGHT) UtilMenu.openBuyItemMenu(p, order);
						});
						UtilSelectedOrder.addConfirmMenu(p.getName(), new Confirm(order, ConfirmType.BUY));
					});
				} else if(clickedName.equalsIgnoreCase("geri dön")) {
					UtilMenu.openBackMenu(p, category);
				}
			}
		});
		ItemStack back = UtilItem.createItem(Material.ARROW, 0, "§aGeri dön");
		inv.setItem(110, back);
		
		inv.setEnabledNoDisplayNameItemClick(true);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("material").is(material.getType()));
		query.addCriteria(Criteria.where("data").is(material.getDurability()));
		
		UtilTask.makeAsync(() -> {
			List<Order> orders = UtilMongo.getSortShopData(query, "price");
			
			UtilTask.makeSync(() -> {
				int i = 0;
				
				for(Order order : orders) {
					int amount = order.getAmount();
					int possible_amount;
					
					if(amount >= 64) possible_amount = 64;
					else possible_amount = amount;
					
					ItemStack item;
					
					if(p.getName().equals(order.getOwner())) {
						item = UtilItem.createItem(material.getType(), 1, material.getItemMeta().getDisplayName(),
								"###Satıcı: §eSEN" +
								"###Stok Sayısı: §d" + amount +
								"###Son Stok Eklediğin Zaman: §e" + UtilSkyblock.getTime(order.getTimeStamp()) + "      " +
								"######Birim Fiyatı: §6" + order.getPrice() + " Dinar" +
								"###§d" + possible_amount + "§7 Adet Fiyatı: §6" + order.getPrice() * possible_amount + " Dinar",
								material.getDurability(), material.getDurability()
						);
						item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
						inv.setItem(i, UtilItem.removeItemFlag(item, ItemFlag.HIDE_ENCHANTS));
					} else {
						item = UtilItem.createItem(material.getType(), 1, material.getItemMeta().getDisplayName(),
								"###Satıcı: §e" + order.getOwner() +
								"###Satıcıdaki Stok: §d" + amount +
								"###Son Stok Eklenme Zamanı: §e" + UtilSkyblock.getTime(order.getTimeStamp()) + "      " +
								"######Birim Fiyatı: §6" + order.getPrice() + " Dinar" +
								"###§d" + possible_amount + "§7 Adet Fiyatı: §6" + order.getPrice() * possible_amount + " Dinar" +
								"######§eSatın almak için sol tıkla" +
								"###§d" + possible_amount + "§e adet almak için Shift+Sol tıkla     " +
								"###§eİstediğin miktarda almak için Shift+Sağ tıkla     ",
								material.getDurability(), material.getDurability()
						);
						inv.setItem(i, item);
					}
					if(i == orders.size()-1) inv.open(p);
					i++;
				}
			});
		});
	}

}
