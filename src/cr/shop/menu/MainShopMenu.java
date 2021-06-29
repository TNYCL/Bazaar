package cr.shop.menu;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cr.api.fakeinventory.FakeInventory;
import cr.api.fakeinventory.FakeInventory.FakeInventoryHandler;
import cr.api.fakeinventory.FakeInventoryClickEvent;
import cr.api.fakeinventory.FakeInventoryCloseEvent;
import cr.api.util.UtilItem;
import cr.api.util.UtilString;
import cr.shop.enums.Category;
import cr.shop.utils.UtilOrder;

public class MainShopMenu {
	
	public static void openMenu(Player p) {
		FakeInventory inv = new FakeInventory("Tüccar", 27, new FakeInventoryHandler() {
			public void onClose(FakeInventoryCloseEvent e) {}
			public void onClick(FakeInventoryClickEvent e) {
				String clicked = UtilString.removeColors(e.getCurrentItem().getItemMeta().getDisplayName());
				if(clicked.equalsIgnoreCase("satışta ki ürünlerim")) {
					OwnShopMenu.openMenu(p);
					return;
				}
				if(clicked.equalsIgnoreCase("bloklar")) {
					MaterialShopMenu.openMenu(p, Category.BLOCKS);
					return;
				}
				if(clicked.equalsIgnoreCase("kızıltaş ve türevleri")) {
					MaterialShopMenu.openMenu(p, Category.REDSTONE);
					return;
				}
				if(clicked.equalsIgnoreCase("iksirler")) {
					MaterialShopMenu.openMenu(p, Category.POTION);
					return;
				}
				if(clicked.equalsIgnoreCase("malzemeler")) {
					MaterialShopMenu.openMenu(p, Category.MATERIAL);
					return;
				}
				if(clicked.equalsIgnoreCase("yemekler")) {
					MaterialShopMenu.openMenu(p, Category.FOOD);
					return;
				}
				if(clicked.equalsIgnoreCase("diğer")) {
					MaterialShopMenu.openMenu(p, Category.OTHER);
					return;
				}
				if(clicked.equalsIgnoreCase("tüccar sistemi nedir?")) {
					UtilOrder.sendInfo(p);
					p.closeInventory();
				}
				if(clicked.equalsIgnoreCase("otomatik satış ayarları")) {
					SettingsShopMenu.openMenu(p);
					return;
				}
			}
		});
		ItemStack help = UtilItem.createItem(Material.BOOK, 0, "§aTüccar Sistemi Nedir?", 
			"Tüccar aracılığı ile bazı eşyalar ###satın alabilir ve satabilirsin." +
			"###" +
			"###Burada ki eşyaların isimleri ###değiştirilmemiş açıklama eklenmemiş ###ve enchant basılmamış olmalıdır." + 
			"###" +
			"###§e/tüccar §7komutu ile eşya ###ekleyip çıkarabilirsin."
		);
		ItemStack ownShop = UtilItem.createItem(Material.CHEST, 0, "§aSatışta ki Ürünlerim",
			"Daha önceden eklediğin ###eşyaları buradan yönetebilirsin." +
			"######§eAçmak için tıkla"
		);
		ItemStack settings = UtilItem.createItem(Material.ENDER_PEARL, 0, "§aOtomatik Satış Ayarları",
			"Tüccara eklediğin ürünler için ###otomatik satış ayarını yönetebilirsin.      " +
			"######§eAçmak için tıkla"
		);
		for(int i=0;i<27;i++) {
			inv.setItem(i, UtilItem.createItem(Material.STAINED_GLASS_PANE, 1, "", 15, 15));
		}
		List<ItemStack> item1 = Arrays.asList(new ItemStack[] {
			UtilItem.createItem(Material.GRASS, 0, "§eBloklar"),
			UtilItem.createItem(Material.REDSTONE, 0, "§eKızıltaş ve Türevleri"),
			UtilItem.createItem(Material.BREWING_STAND_ITEM, 0, "§eİksirler")
		});
		List<ItemStack> item2 = Arrays.asList(new ItemStack[] {
			UtilItem.createItem(Material.DIAMOND, 0, "§eMalzemeler"),
			UtilItem.createItem(Material.APPLE, 0, "§eYemekler"),
			UtilItem.createItem(Material.LAVA_BUCKET, 0, "§eDiğer")
		});
		int i = 10;
		for(ItemStack item : item1) {
			inv.setItem(i, item);
			i++;
		}
		int a = 14;
		for(ItemStack item : item2) {
			inv.setItem(a, item);
			a++;
		}
		inv.setItem(22, help);
		inv.setItem(26, ownShop);
		inv.setItem(18, settings);
		inv.open(p);
	}

}
