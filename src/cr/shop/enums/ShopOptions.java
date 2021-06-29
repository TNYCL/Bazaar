package cr.shop.enums;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ShopOptions {
	
	BLOCKS(Arrays.asList(new ItemStack[] {
			new ItemStack(Material.ICE),
			new ItemStack(Material.PACKED_ICE),
			new ItemStack(Material.MOSSY_COBBLESTONE),
			new ItemStack(Material.BRICK),
			new ItemStack(Material.BOOKSHELF),
			new ItemStack(Material.WOOD, 1, (short) 0),
			new ItemStack(Material.WOOD, 1, (short) 1),
			new ItemStack(Material.WOOD, 1, (short) 2),
			new ItemStack(Material.WOOD, 1, (short) 3),
			new ItemStack(Material.WOOD, 1, (short) 4),
			new ItemStack(Material.WOOD, 1, (short) 5),
			new ItemStack(Material.WOOD, 1, (short) 6),
			new ItemStack(Material.WOOD, 1, (short) 7),
			new ItemStack(Material.LOG, 1, (short) 0),
			new ItemStack(Material.LOG, 1, (short) 1),
			new ItemStack(Material.LOG, 1, (short) 2),
			new ItemStack(Material.LOG, 1, (short) 3),
			new ItemStack(Material.LOG_2, 1, (short) 0),
			new ItemStack(Material.LOG_2, 1, (short) 1),
			new ItemStack(Material.SPONGE),
			new ItemStack(Material.PRISMARINE, 1, (short) 0),
			new ItemStack(Material.PRISMARINE, 1, (short) 1),
			new ItemStack(Material.PRISMARINE, 1, (short) 2),
			new ItemStack(Material.COAL_BLOCK),
			new ItemStack(Material.OBSIDIAN),
			new ItemStack(Material.PUMPKIN),
			new ItemStack(Material.LAPIS_BLOCK),
			new ItemStack(Material.SEA_LANTERN),
			new ItemStack(Material.GLOWSTONE),
			new ItemStack(Material.SOUL_SAND),
			new ItemStack(Material.SNOW_BLOCK),
			new ItemStack(Material.QUARTZ_BLOCK, 1, (short) 0),
			new ItemStack(Material.QUARTZ_BLOCK, 1, (short) 1),
			new ItemStack(Material.QUARTZ_BLOCK, 1, (short) 2),
			new ItemStack(Material.NETHER_BRICK),
			new ItemStack(Material.NETHERRACK),
			new ItemStack(Material.SMOOTH_BRICK),
			new ItemStack(Material.MYCEL),
			new ItemStack(Material.DIRT),
			new ItemStack(Material.COBBLESTONE),
			new ItemStack(Material.SAND, 1, (short) 0),
			new ItemStack(Material.SAND, 1, (short) 1),
			new ItemStack(Material.SANDSTONE),
			new ItemStack(Material.ENDER_STONE),
			new ItemStack(Material.MAGMA),
			new ItemStack(Material.CLAY),
			new ItemStack(Material.HARD_CLAY),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 0),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 1),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 2),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 3),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 4),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 5),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 6),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 7),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 8),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 9),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 10),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 11),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 12),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 13),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 14),
			new ItemStack(Material.STAINED_CLAY, 1, (short) 15),
			new ItemStack(Material.WOOL, 1, (short) 0),
			new ItemStack(Material.WOOL, 1, (short) 1),
			new ItemStack(Material.WOOL, 1, (short) 2),
			new ItemStack(Material.WOOL, 1, (short) 3),
			new ItemStack(Material.WOOL, 1, (short) 4),
			new ItemStack(Material.WOOL, 1, (short) 5),
			new ItemStack(Material.WOOL, 1, (short) 6),
			new ItemStack(Material.WOOL, 1, (short) 7),
			new ItemStack(Material.WOOL, 1, (short) 8),
			new ItemStack(Material.WOOL, 1, (short) 9),
			new ItemStack(Material.WOOL, 1, (short) 10),
			new ItemStack(Material.WOOL, 1, (short) 12),
			new ItemStack(Material.WOOL, 1, (short) 13),
			new ItemStack(Material.WOOL, 1, (short) 14),
			new ItemStack(Material.WOOL, 1, (short) 15),
			new ItemStack(Material.GLASS),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 0),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 1),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 2),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 3),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 4),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 5),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 6),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 7),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 8),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 9),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 10),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 11),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 12),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 13),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 14),
			new ItemStack(Material.STAINED_GLASS, 1, (short) 15)
	})),
	
	REDSTONE(Arrays.asList(new ItemStack[] {
			new ItemStack(Material.NOTE_BLOCK),
			new ItemStack(Material.PISTON_BASE),
			new ItemStack(Material.REDSTONE_LAMP_OFF),
			new ItemStack(Material.DAYLIGHT_DETECTOR),
			new ItemStack(Material.LEVER),
			new ItemStack(Material.STONE_BUTTON),
			new ItemStack(Material.WOOD_BUTTON),
			new ItemStack(Material.TRIPWIRE_HOOK),
			new ItemStack(Material.REDSTONE),
			new ItemStack(Material.REDSTONE_BLOCK),
			new ItemStack(Material.HOPPER),
			new ItemStack(Material.DIODE),
			new ItemStack(Material.REDSTONE_COMPARATOR),
			new ItemStack(Material.WOOD_DOOR),
			new ItemStack(Material.IRON_DOOR),
			new ItemStack(Material.SPRUCE_DOOR_ITEM),
			new ItemStack(Material.BIRCH_DOOR_ITEM),
			new ItemStack(Material.JUNGLE_DOOR_ITEM),
			new ItemStack(Material.ACACIA_DOOR_ITEM),
			new ItemStack(Material.DARK_OAK_DOOR_ITEM),
			new ItemStack(Material.POWERED_RAIL),
			new ItemStack(Material.DETECTOR_RAIL),
			new ItemStack(Material.ACTIVATOR_RAIL),
			new ItemStack(Material.RAILS),
			new ItemStack(Material.MINECART),
			new ItemStack(Material.STORAGE_MINECART),
			new ItemStack(Material.HOPPER_MINECART),
			new ItemStack(Material.POWERED_MINECART),
			new ItemStack(Material.DROPPER),
			new ItemStack(Material.DISPENSER),
			new ItemStack(Material.SADDLE),
			new ItemStack(Material.BOAT),
			new ItemStack(Material.CARROT_STICK)
	})),
	
	POTION(Arrays.asList(new ItemStack[] {
			new ItemStack(Material.POTION),
			new ItemStack(Material.GLASS_BOTTLE),
			new ItemStack(Material.GHAST_TEAR),
			new ItemStack(Material.FERMENTED_SPIDER_EYE),
			new ItemStack(Material.BLAZE_POWDER),
			new ItemStack(Material.MAGMA_CREAM),
			new ItemStack(Material.SPECKLED_MELON),
			new ItemStack(Material.GOLDEN_CARROT),
			new ItemStack(Material.RABBIT_FOOT),
			new ItemStack(Material.SULPHUR),
			new ItemStack(Material.GLOWSTONE_DUST),
			new ItemStack(Material.SUGAR),
			new ItemStack(Material.BLAZE_ROD),
			new ItemStack(Material.NETHER_STALK),
			new ItemStack(Material.BREWING_STAND_ITEM),
			new ItemStack(Material.CAULDRON_ITEM),
			new ItemStack(Material.POTION, 1, (short) 8193),
			new ItemStack(Material.POTION, 1, (short) 8225),
			new ItemStack(Material.POTION, 1, (short) 8257),
			new ItemStack(Material.POTION, 1, (short) 8194),
			new ItemStack(Material.POTION, 1, (short) 8226),
			new ItemStack(Material.POTION, 1, (short) 8258),
			new ItemStack(Material.POTION, 1, (short) 8227),
			new ItemStack(Material.POTION, 1, (short) 8259),
			new ItemStack(Material.POTION, 1, (short) 8196),
			new ItemStack(Material.POTION, 1, (short) 8228),
			new ItemStack(Material.POTION, 1, (short) 8260),
			new ItemStack(Material.POTION, 1, (short) 8261),
			new ItemStack(Material.POTION, 1, (short) 8229),
			new ItemStack(Material.POTION, 1, (short) 8230),
			new ItemStack(Material.POTION, 1, (short) 8262),
			new ItemStack(Material.POTION, 1, (short) 8232),
			new ItemStack(Material.POTION, 1, (short) 8264),
			new ItemStack(Material.POTION, 1, (short) 8201),
			new ItemStack(Material.POTION, 1, (short) 8233),
			new ItemStack(Material.POTION, 1, (short) 8265),
			new ItemStack(Material.POTION, 1, (short) 8234),
			new ItemStack(Material.POTION, 1, (short) 8266),
			new ItemStack(Material.POTION, 1, (short) 8203),
			new ItemStack(Material.POTION, 1, (short) 8235),
			new ItemStack(Material.POTION, 1, (short) 8267),
			new ItemStack(Material.POTION, 1, (short) 8237),
			new ItemStack(Material.POTION, 1, (short) 8269)
	})),
	
	MATERIAL(Arrays.asList(new ItemStack[] {
			new ItemStack(Material.EMERALD),
			new ItemStack(Material.DIAMOND),
			new ItemStack(Material.IRON_INGOT),
			new ItemStack(Material.GOLD_INGOT),
			new ItemStack(Material.GOLD_NUGGET),
			new ItemStack(Material.COAL),
			new ItemStack(Material.COAL, 1, (short) 1),
			new ItemStack(Material.NETHER_BRICK),
			new ItemStack(Material.STICK),
			new ItemStack(Material.BOWL),
			new ItemStack(Material.STRING),
			new ItemStack(Material.FEATHER),
			new ItemStack(Material.FLINT),
			new ItemStack(Material.LEATHER),
			new ItemStack(Material.CLAY_BALL),
			new ItemStack(Material.SUGAR_CANE),
			new ItemStack(Material.NETHER_STAR),
			new ItemStack(Material.RABBIT_HIDE),
			new ItemStack(Material.QUARTZ),
			new ItemStack(Material.INK_SACK),
			new ItemStack(Material.INK_SACK, 1, (short) 1),
			new ItemStack(Material.INK_SACK, 1, (short) 2),
			new ItemStack(Material.INK_SACK, 1, (short) 3),
			new ItemStack(Material.INK_SACK, 1, (short) 4),
			new ItemStack(Material.INK_SACK, 1, (short) 5),
			new ItemStack(Material.INK_SACK, 1, (short) 6),
			new ItemStack(Material.INK_SACK, 1, (short) 7),
			new ItemStack(Material.INK_SACK, 1, (short) 8),
			new ItemStack(Material.INK_SACK, 1, (short) 9),
			new ItemStack(Material.INK_SACK, 1, (short) 10),
			new ItemStack(Material.INK_SACK, 1, (short) 11),
			new ItemStack(Material.INK_SACK, 1, (short) 12),
			new ItemStack(Material.INK_SACK, 1, (short) 13),
			new ItemStack(Material.INK_SACK, 1, (short) 14),
			new ItemStack(Material.INK_SACK, 1, (short) 15),
	})),
	
	FOOD(Arrays.asList(new ItemStack[] {
			new ItemStack(Material.GOLDEN_APPLE),
			new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1),
			new ItemStack(Material.APPLE),
			new ItemStack(Material.MUSHROOM_SOUP),
			new ItemStack(Material.BREAD),
			new ItemStack(Material.PORK),
			new ItemStack(Material.GRILLED_PORK),
			new ItemStack(Material.RAW_FISH),
			new ItemStack(Material.RAW_FISH, 1, (short) 1),
			new ItemStack(Material.RAW_FISH, 1, (short) 2),
			new ItemStack(Material.RAW_FISH, 1, (short) 3),
			new ItemStack(Material.COOKED_FISH),
			new ItemStack(Material.COOKED_FISH, 1, (short) 1),
			new ItemStack(Material.CAKE),
			new ItemStack(Material.COOKIE),
			new ItemStack(Material.MELON),
			new ItemStack(Material.RAW_BEEF),
			new ItemStack(Material.COOKED_BEEF),
			new ItemStack(Material.RAW_CHICKEN),
			new ItemStack(Material.COOKED_CHICKEN),
			new ItemStack(Material.CARROT_ITEM),
			new ItemStack(Material.POTATO_ITEM),
			new ItemStack(Material.BAKED_POTATO),
			new ItemStack(Material.PUMPKIN_PIE),
			new ItemStack(Material.RABBIT),
			new ItemStack(Material.COOKED_RABBIT),
			new ItemStack(Material.RABBIT_STEW),
			new ItemStack(Material.MUTTON),
			new ItemStack(Material.COOKED_MUTTON),
	})),
	
	OTHER(Arrays.asList(new ItemStack[] {
			new ItemStack(Material.ENCHANTMENT_TABLE),
			new ItemStack(Material.BUCKET),
			new ItemStack(Material.WATER_BUCKET),
			new ItemStack(Material.LAVA_BUCKET),
			new ItemStack(Material.MILK_BUCKET),
			new ItemStack(Material.ENDER_PEARL),
			new ItemStack(Material.EYE_OF_ENDER),
			new ItemStack(Material.FISHING_ROD),
			new ItemStack(Material.WATCH),
			new ItemStack(Material.SHEARS),
			new ItemStack(Material.NAME_TAG),
			new ItemStack(Material.FLINT_AND_STEEL),
			new ItemStack(Material.BOW),
			new ItemStack(Material.ARROW),
			new ItemStack(Material.LADDER),
			new ItemStack(Material.TORCH),
			new ItemStack(Material.SIGN),
			new ItemStack(Material.ITEM_FRAME),
			new ItemStack(Material.SLIME_BALL),
			new ItemStack(Material.SKULL_ITEM, 1, (short) 1),
			new ItemStack(Material.LEASH),
	}));
	
	private List<ItemStack> items;
	
	ShopOptions(List<ItemStack> items) {
		this.items = items;
	}
	
	public static List<ItemStack> getCategoryItems(Category category) {
		if(category == Category.BLOCKS) return BLOCKS.getItems();
		if(category == Category.REDSTONE) return REDSTONE.getItems();
		if(category == Category.POTION) return POTION.getItems();
		if(category == Category.MATERIAL) return MATERIAL.getItems();
		if(category == Category.FOOD) return FOOD.getItems();
		if(category == Category.OTHER) return OTHER.getItems();
		return null;
	}
	
	public List<ItemStack> getItems() {
		return this.items;
	}

}
























