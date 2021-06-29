package cr.shop.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import cr.api.util.UtilPlayer;
import cr.api.util.UtilString;
import cr.api.util.UtilTask;
import cr.essentials.CREssentials;
import cr.shop.enums.AutoSell;
import cr.shop.enums.Category;
import cr.shop.menu.MainShopMenu;
import cr.shop.menu.MaterialShopMenu;
import cr.shop.module.Order;
import cr.shop.module.PlayerSettings;

public class UtilOrder {
	
	public static void sendInfo(Player p) {
		UtilPlayer.sendMessage(p, UtilString.centerMessage("§e§l§m----------§r §6§lTüccar §e§l§m----------"));
		UtilPlayer.sendMessage(p, " ");
		UtilPlayer.sendMessage(p, " §6/tüccar aç §f- §eTüccarı açar");
		UtilPlayer.sendMessage(p, " §6/tüccar ekle <miktar/hepsi> <fiyat> §f- §eTüccara satmak istediğiniz eşyayı ekler.");
		UtilPlayer.sendMessage(p, " §6/tüccar stokekle <miktar/hepsi> §f- §eTüccara eklediğiniz bir ürüne stok ekler.");
		UtilPlayer.sendMessage(p, " ");
		UtilPlayer.sendMessage(p, UtilString.centerMessage("§e§l§m----------§r §6§lTüccar §e§l§m----------"));
	}
	
	public static void buyItem(Player p, int amount) {
		if(UtilSelectedOrder.getConfirmMenu(p.getName()) == null) return;
		
		Order order = UtilSelectedOrder.getConfirmMenu(p.getName()).getOrder();
		ItemStack item = new ItemStack(order.getMaterial(), amount, (byte) order.getData());
		long totalPrice = amount * order.getPrice();
		
		if(amount < 1) {
			UtilPlayer.sendErrorMessage(p, "En az §b1 §cadet ürün satın alabilirsin");
			return;
		}
		if(amount > order.getAmount()) {
			UtilPlayer.sendErrorMessage(p, "Girilen miktar satışta ki miktardan büyük olamaz.");
			return;
		}
		if(CREssentials.getEconomy().getPlayerMoney(p.getName()) < totalPrice) {
			UtilPlayer.sendErrorMessage(p, "§b" + amount + " §cadet ürünü satın alabilmek için yeterli dinara sahip değilsin.");		
			return;
		}
		int availableAmount = UtilSkyblock.getAvailableAmount(p.getInventory(), item);
		if(availableAmount+1 <= amount) {
			if(availableAmount == 0) UtilPlayer.sendErrorMessage(p, "Bu ürünü satın alabilmek için envanterinde boş alan olmalı.");	
			else UtilPlayer.sendErrorMessage(p, "Bu ürün için bu kadar boş alana sahip değilsin, toplamda §b" + availableAmount + "§c adet boş yerin var.");
			return;	
		}
		
		Query query = new Query();
		query.addCriteria(Criteria.where("owner").is(order.getOwner()));
		query.addCriteria(Criteria.where("material").is(order.getMaterial()));
		query.addCriteria(Criteria.where("data").is(order.getData()));
		
		UtilTask.makeAsync(() -> {
			Order data = UtilMongo.getShopData(query);
			
			UtilTask.makeSync(() -> {
				if(data.getAmount() < amount) {
					UtilPlayer.sendErrorMessage(p, "Satıcının stoğunda bu kadar ürün yok!");
					return;
				}
			});
			
			if(amount != data.getAmount()) UtilMongo.updateShopData(query, "amount", data.getAmount() - amount);
			else {
				UtilMongo.deleteShopData(query);
				AutoSell autoSell = AutoSell.fromIcon(order.getMaterial());
				PlayerSettings settings = PlayerSettings.getPlayerSettings(order.getOwner());
				if(autoSell != null) {
					if(settings != null) {
						if(settings.getSettings(autoSell).getStatus() == true) {
							settings.getSettings(autoSell).setStatus(false);
							PlayerSettings.updatePlayerSettings(order.getOwner(), settings);
							UtilPlayer.sendNormalMessage(Bukkit.getPlayer(order.getOwner()), "§6" + autoSell.getName() + 
									" §eotomatik satış ayarı stok kalmadığı için devre dışı bırakıldı.");
						}
					} else {
						UtilTask.makeAsync(() -> {
							PlayerSettings settingsData = UtilMongo.getSettingsData(Query.query(Criteria.where("playerName").is(order.getOwner())));
							settingsData.getSettings(autoSell).setStatus(false);
							UtilMongo.insertSettingsData(settingsData);
						});
					}
				}
			}
			
			UtilTask.makeSync(() -> {
				if(Bukkit.getPlayer(order.getOwner()) != null) {
					UtilPlayer.sendNormalMessage(Bukkit.getPlayer(order.getOwner()), 
							"§6" + p.getName() + " §eadlı oyuncu §b" + amount + " §eadet ürünü §b" + order.getPrice() * amount + " §edinara satın aldı.");
					UtilPlayer.sendNormalMessage(Bukkit.getPlayer(order.getOwner()), 
							"§eSatın alım sonrası stok: §b" + (data.getAmount() == amount ? "§cYOK" : data.getAmount() - amount));
				}
				
				UtilPlayer.sendMenuSuccessMessage(p, "§b" + amount + " §eadet ürünü §b" + order.getPrice() * amount + " §edinara satın aldın.");
				UtilPlayer.sendNormalMessage(p, "§aÜrünü başarıyla satın aldın.");
				MaterialShopMenu.openSelectedMenu(p, item, order.getCategory());
				CREssentials.getEconomy().takePlayerMoney(p.getName(), totalPrice);
				CREssentials.getEconomy().givePlayerMoney(order.getOwner(), totalPrice);
				p.getInventory().addItem(item);
			});
		});
	}
	
	public static void cancelItem(Player p, int amount) {
		if(UtilSelectedOrder.getConfirmMenu(p.getName()) == null) return;
		
		Order order = UtilSelectedOrder.getConfirmMenu(p.getName()).getOrder();
		ItemStack item = new ItemStack(order.getMaterial(), amount, (byte) order.getData());
		
		if(amount < 1) {
			UtilPlayer.sendErrorMessage(p, "En az §b1 §cadet ürünü iptal edebilirsin.");
			return;
		}
		if(amount > order.getAmount()) {
			UtilPlayer.sendErrorMessage(p, "Girilen miktar satışta ki miktardan büyük olamaz.");
			return;
		}
		int availableAmount = UtilSkyblock.getAvailableAmount(p.getInventory(), item);
		if(availableAmount+1 <= amount) {
			if(availableAmount == 0) UtilPlayer.sendErrorMessage(p, "Bu ürünü iptal edebilmek için envanterinde boş alan olmalı.");	
			else UtilPlayer.sendErrorMessage(p, "Bu ürün için bu kadar boş alana sahip değilsin, toplamda §b" + availableAmount + "§c adet boş yerin var.");
			return;	
		}
		
		Query query = new Query();
		query.addCriteria(Criteria.where("owner").is(p.getName()));
		query.addCriteria(Criteria.where("material").is(order.getMaterial()));
		query.addCriteria(Criteria.where("data").is(order.getData()));
		
		if(amount == order.getAmount()) {
			UtilTask.makeAsync(() -> {
				UtilMongo.deleteShopData(query);
				
				UtilTask.makeSync(() -> {
					UtilPlayer.sendSuccessMessage(p, "Ürün satıştan kaldırıldı ve envanterinize aktarıldı.");
				});
			});
		} else {
			UtilTask.makeAsync(() -> {
				UtilMongo.updateShopData(query, "amount", order.getAmount() - amount);
				
				UtilTask.makeSync(() -> {
					UtilPlayer.sendSuccessMessage(p, "§b" + amount + " §eadet ürün satıştan kaldırıldı ve envanterinize aktarıldı.");
				});
			});
		}
		
		p.getInventory().addItem(item);
		MainShopMenu.openMenu(p);
	}
	
	public static void addNewItem(Player p, ItemStack item, Category category, int price, int amount) {
		int item_data = item.getDurability();
		
		Order order = new Order(p.getName(), price, System.currentTimeMillis(), category, item.getType(), amount, item_data);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("material").is(item.getType()));
		query.addCriteria(Criteria.where("owner").is(p.getName()));
		query.addCriteria(Criteria.where("data").is(item_data));
		
		UtilTask.makeAsync(() -> {
			Order data = UtilMongo.getShopData(query);
			
			if(data != null) {
				UtilPlayer.sendErrorMessage(p, "Bu ürünü zaten tüccara eklemişsin. Tekrar eklemeden önce mevcut satışını iptal etmelisin.");
				return;
			}
			
			UtilTask.makeSync(() -> {
				UtilPlayer.sendSuccessMessage(p, "§b" + amount + " §eadet ürünü §b" + price + " §efiyatıyla tüccara eklediniz.");
				p.getInventory().removeItem(new ItemStack(item.getType(), amount, (short) item_data));
			});
			
			UtilMongo.insertShopData(order);
		});
	}
	
	public static void addStock(Player p, Category category, ItemStack item, int amount) {
		int item_data = item.getDurability();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("material").is(item.getType()));
		query.addCriteria(Criteria.where("owner").is(p.getName()));
		query.addCriteria(Criteria.where("data").is(item_data));
		
		UtilTask.makeAsync(() -> {
			Order shop = UtilMongo.getShopData(query);
			
			if(shop == null) {
				UtilPlayer.sendErrorMessage(p, "Stok ekleyebilmek için daha önce bu eşyayı tüccara eklemiş olman gerekiyor.");
				return;
			}
			
			UtilTask.makeSync(() -> {
				UtilPlayer.sendSuccessMessage(p, "§b" + amount + " §eadet ürünü stok olarak tüccara eklediniz.");
				p.getInventory().removeItem(new ItemStack(item.getType(), amount, (short) item_data));
			});
			
			shop.setAmount(shop.getAmount() + amount);
			
			Update update = new Update();
			update.set("amount", shop.getAmount());
			update.set("timestamp", System.currentTimeMillis());
			
			UtilMongo.updateShopData(query, update);
		});
	}

}
