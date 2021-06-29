package cr.shop.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import cr.api.util.UtilInventory;
import cr.api.util.UtilString;
import cr.api.utils.Util;
import cr.shop.enums.Category;
import cr.shop.enums.ShopOptions;

public class UtilSkyblock {

	public static Category getItemCategory(ItemStack item) {
		Category category = null;
		Material type = item.getType();
		
		for(ItemStack blocks : ShopOptions.getCategoryItems(Category.BLOCKS)) {
			if(blocks.getType() == type && blocks.getDurability() == item.getDurability()) category = Category.BLOCKS;
		}
		for(ItemStack redstone : ShopOptions.getCategoryItems(Category.REDSTONE)) {
			if(redstone.getType() == type && redstone.getDurability() == item.getDurability()) category = Category.REDSTONE;
		}
		for(ItemStack potion : ShopOptions.getCategoryItems(Category.POTION)) {
			if(potion.getType() == type && potion.getDurability() == item.getDurability()) category = Category.POTION;
		}
		for(ItemStack material : ShopOptions.getCategoryItems(Category.MATERIAL)) {
			if(material.getType() == type && material.getDurability() == item.getDurability()) category = Category.MATERIAL;
		}
		for(ItemStack food : ShopOptions.getCategoryItems(Category.FOOD)) {
			if(food.getType() == type && food.getDurability() == item.getDurability()) category = Category.FOOD;
		}
		for(ItemStack other : ShopOptions.getCategoryItems(Category.OTHER)) {
			if(other.getType() == type && other.getDurability() == item.getDurability()) category = Category.OTHER;
		}
		
		return category;
	}
	
	public static String getTime(long timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("dd.M.yyyy hh:mm:ss", Locale.getDefault());
		return format.format(timestamp);
	}
	
	public static String getItemOwner(String meta) {
		meta = UtilString.removeColors(meta);
		String owner = meta.replaceAll("Satıcı: ", "");
		return owner;
	}
	
	public static int getAvailableAmount(Inventory inventory, ItemStack item) {
		int value = 0;
		for(int i =0;i<inventory.getSize();i++) {
			ItemStack selected = inventory.getItem(i);
			if(selected == null) value += item.getMaxStackSize();
			if(selected != null && selected.getType() == item.getType()) {
				if(selected.getAmount() == item.getMaxStackSize()) continue;
				else value += selected.getMaxStackSize() - selected.getAmount();
			}
		}
		return value;
	}
	
	public static ArrayList<Integer> getFirstSlots(Inventory inventory) {
		ArrayList<Integer> pl = new ArrayList<Integer>();
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getItem(i) == null) {
				pl.add(i);
			}
		}
		return pl;
	}
	
	@SuppressWarnings("deprecation")
	public static int getItemSize(Inventory inventory, ItemStack item){
		Material material = item.getType();
		
		if(!UtilInventory.hasMaterial(inventory, material)){
			return 0;
		}
		
		int size = 0;
		
		for(int i = 0; i < inventory.getSize(); i++){
			ItemStack it = inventory.getItem(i);
			if(
				it != null 
				&& !it.getItemMeta().hasDisplayName()
				&& !it.getItemMeta().hasEnchants()
				&& !it.getItemMeta().hasLore()
				&& it.getData().getData() == item.getData().getData()
				&& it.getDurability() == item.getDurability()
				&& it.getType().equals(material)){
					size += it.getAmount();
			}
		}
		
		return size;
	}
	
	@SuppressWarnings("deprecation")
	public static int removeAll(Player player, ItemStack item) {
		HashMap<Integer, ? extends ItemStack> values = player.getInventory().all(item.getType());

		Util.log("Items: " + values.toString());
		
		int removedSize = 0;
		
	    for (int i : values.keySet()) {
	        ItemStack stack = player.getInventory().getItem(i);
	        if (stack == null || stack.getType().equals(Material.AIR)) continue;
	        if (!stack.getEnchantments().isEmpty()) continue;

	        boolean isProvideItem = stack.getData().getData() == item.getData().getData();
	        if (!isProvideItem) continue;
	        
            player.getInventory().setItem(i, null);
			
            int foundAmount = stack.getAmount();
            removedSize += foundAmount;
	    }

	    player.updateInventory();
	    return removedSize;
	}

}
