package cr.shop.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import cr.api.util.UtilPlayer;
import cr.api.util.UtilTime;
import cr.shop.enums.Category;
import cr.shop.menu.MainShopMenu;
import cr.shop.utils.UtilOrder;
import cr.shop.utils.UtilSkyblock;

public class Tüccar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Bu komut konsoldan kullanılamaz.");
			return false;
		}
		Player p = (Player) sender;
		ItemStack itemInHand = p.getItemInHand();
		Inventory inventory = p.getInventory();
		
		if(args.length == 0) {
			UtilOrder.sendInfo(p);
			return false;
		}
		if(args[0].equalsIgnoreCase("aç") || args[0].equalsIgnoreCase("open")) {
			MainShopMenu.openMenu(p);
		} else if(args[0].equalsIgnoreCase("ekle") || args[0].equalsIgnoreCase("add")) {
			if(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null) {
				UtilPlayer.sendErrorMessage(p, "Elinizde bir eşya olmalı.");
				return false;
			}
			if(args.length == 1) {
				UtilOrder.sendInfo(p);
				return false;
			}
			if(!UtilTime.isNumeric(args[1]) && !(args[1].equalsIgnoreCase("hepsi"))) {
				UtilPlayer.sendErrorMessage(p, "Girilen miktar sadece sayı olmalı.");
				return false;
			}
			if(itemInHand.getItemMeta().getDisplayName() != null) {
				UtilPlayer.sendErrorMessage(p, "Bu eşyanın ismi değiştirildiği için tüccara ekleyemezsin.");
				return false;
			}
			int amount;
			if(args[1].equalsIgnoreCase("hepsi")) amount = UtilSkyblock.getItemSize(inventory, itemInHand);
			else amount = Integer.parseInt(args[1]);
			if(UtilSkyblock.getItemSize(inventory, itemInHand) < amount && !args[1].equalsIgnoreCase("hepsi")) {
				UtilPlayer.sendErrorMessage(p, "Girilen miktar envanterinizde bulunandan fazla, toplamda §b" + UtilSkyblock.getItemSize(inventory, itemInHand) + "§c adete sahipsin.");
				return false;
			}
			if(args.length == 2) {
				UtilOrder.sendInfo(p);
				return false;
			}
			if(!UtilTime.isNumeric(args[2])) {
				UtilPlayer.sendErrorMessage(p, "Girilen değer sadece sayı olmalı.");
				return false;
			}
			if(amount < 1) {
				UtilPlayer.sendErrorMessage(p, "En az §b1 §cadet ürünü satışa çıkartabilirsin.");
				return false;
			}
			int price = Integer.parseInt(args[2]);
			if(price < 10) {
				UtilPlayer.sendErrorMessage(p, "En az §b10 §cdinardan ürünü satışa çıkartabilirsin.");
				return false;
			}
			
			Category category = UtilSkyblock.getItemCategory(itemInHand);
			
			if(category == null) {
				UtilPlayer.sendErrorMessage(p, "Bu ürünü tüccara ekleyemezsin.");
				return false;
			}
			
			UtilOrder.addNewItem(p, itemInHand, category, price, amount);
		} else if(args[0].equalsIgnoreCase("stokekle") || args[0].equalsIgnoreCase("addstock")) {
			if(p.getItemInHand().getType() == Material.AIR || p.getItemInHand() == null) {
				UtilPlayer.sendErrorMessage(p, "Elinizde bir eşya olmalı.");
				return false;
			}
			if(args.length == 1) {
				UtilOrder.sendInfo(p);
				return false;
			}
			if(!UtilTime.isNumeric(args[1]) && !(args[1].equalsIgnoreCase("hepsi"))) {
				UtilPlayer.sendErrorMessage(p, "Girilen miktar sadece sayı olmalı.");
				return false;
			}
			if(itemInHand.getItemMeta().getDisplayName() != null) {
				UtilPlayer.sendErrorMessage(p, "Bu eşyanın ismi değiştirildiği için tüccara ekleyemezsin.");
				return false;
			}
			int amount;
			if(args[1].equalsIgnoreCase("hepsi")) amount = UtilSkyblock.getItemSize(inventory, itemInHand);
			else amount = Integer.parseInt(args[1]);
			if(UtilSkyblock.getItemSize(inventory, itemInHand) < amount && !args[1].equalsIgnoreCase("hepsi")) {
				UtilPlayer.sendErrorMessage(p, "Girilen miktar envanterinizde bulunandan fazla, toplamda §b" + UtilSkyblock.getItemSize(inventory, itemInHand) + "§c adete sahipsin.");
				return false;
			}
			if(amount < 1) {
				UtilPlayer.sendErrorMessage(p, "En az §b1 §cadet ürünü stok ekleyebilirsin.");
				return false;
			}
			
			Category category = UtilSkyblock.getItemCategory(itemInHand);
			
			UtilOrder.addStock(p, category, itemInHand, amount);
		}
		return true;
	}

}
