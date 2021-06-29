package cr.shop.menu;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cr.api.fakeinventory.FakeInventory;
import cr.api.fakeinventory.FakeInventory.FakeInventoryHandler;
import cr.api.fakeinventory.FakeInventoryClickEvent;
import cr.api.fakeinventory.FakeInventoryCloseEvent;
import cr.api.util.UtilInventory;
import cr.api.util.UtilItem;
import cr.api.util.UtilPlayer;
import cr.api.util.UtilString;
import cr.api.util.UtilTask;
import cr.api.utils.ItemBuilder;
import cr.shop.enums.AutoSell;
import cr.shop.module.Order;
import cr.shop.module.PlayerSettings;
import cr.shop.utils.UtilMongo;

public class SettingsShopMenu {

	public static void openMenu(Player p) {
		PlayerSettings data = PlayerSettings.getPlayerSettings(p.getName());
		
		FakeInventory inv = new FakeInventory("Tüccar » Ayarlar", 45, new FakeInventoryHandler() {
			public void onClose(FakeInventoryCloseEvent e) {
			}

			@SuppressWarnings("deprecation")
			public void onClick(FakeInventoryClickEvent e) {
				String clicked = UtilString.removeColors(e.getCurrentItem().getItemMeta().getDisplayName());
				if(e.getCurrentItem().getType() == Material.ARROW && clicked.equalsIgnoreCase("geri dön")) {
					MainShopMenu.openMenu(p);
					return;
				}
				
				AutoSell sellType = AutoSell.fromName(e.getClickedName());
				if(data.getSettings(sellType).getStatus() == false) {
					Query query = new Query();
					query.addCriteria(Criteria.where("owner").is(p.getName()));
					query.addCriteria(Criteria.where("material").is(e.getCurrentItem().getType()));
					query.addCriteria(Criteria.where("data").is(e.getCurrentItem().getData().getData()));
					
					UtilTask.makeAsync(() -> {
						Order order = UtilMongo.getShopData(query);
						if(order != null) {
							UtilPlayer.sendMenuSuccessMessage(p, "§6" + sellType.getName() + " §esatış ayarı başarıyla aktif edildi.");
							data.getSettings(sellType).setStatus(true);
							PlayerSettings.updatePlayerSettings(p.getName(), data);
							SettingsShopMenu.openMenu(p);
							return;
						} else {
							UtilPlayer.sendMenuErrorMessage(p, "Bu ayarı aktif edebilmek için eşyayı tüccara eklemen gerekiyor.");
							return;
						}
					});
				} else {
					UtilPlayer.sendMenuSuccessMessage(p, "§6" + sellType.getName() + " §esatış ayarı başarıyla devre dışı bırakıldı.");
					data.getSettings(sellType).setStatus(false);
					PlayerSettings.updatePlayerSettings(clicked, data);
					SettingsShopMenu.openMenu(p);
					return;
				}
			}
		});
		ItemStack back = UtilItem.createItem(Material.ARROW, 0, "§aGeri dön");
		for(int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, UtilItem.createItem(Material.STAINED_GLASS_PANE, 1, "", 15, 15));
		}

		int i = 10;

		for(AutoSell items : AutoSell.values()) {
			i = UtilInventory.fixSlot(i);

			ItemBuilder itemBuilder = new ItemBuilder(items.getIcon());
			itemBuilder.setDisplayName("§6§l" + items.getName());
			itemBuilder.setAmount(1);

			StringBuilder loreBuilder = new StringBuilder();
			loreBuilder.append("###");
			loreBuilder.append("§7" + items.getDescription() + "######§7Gereken malzemeler:     ###");
			for(String needed : items.getItemsName()) {
				loreBuilder.append("###§7- §e" + needed);
			}
			loreBuilder.append("### ###");

			if(data.getSettings(items).getStatus() == true) {
				loreBuilder.append("§7Durum: §aAktif");
				loreBuilder.append("######§eDevre dışı bırakmak için tıklayın!        ");
				itemBuilder.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
			} else {
				loreBuilder.append("§7Durum: §cKapalı");
				loreBuilder.append("######§eAktif etmek için tıklayın!");
			}

			itemBuilder.setLores(loreBuilder.toString());

			itemBuilder.removeItemFlags();

			inv.setItem(i, itemBuilder.getItemStack());
			i++;
		}

		inv.setItem(36, back);
		inv.open(p);
	}

}
