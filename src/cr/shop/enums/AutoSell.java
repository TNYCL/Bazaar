package cr.shop.enums;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.google.common.collect.Lists;

public enum AutoSell {

	COW(Material.RAW_BEEF, "İnek",
			"İneklerden düşen eşyaların ###§7otomatik tüccara eklenmesi için ###§7bu ayarı aktif edebilirsin.",
			Arrays.asList(new Material[] {
					Material.RAW_BEEF,
					Material.LEATHER
			}),
			Arrays.asList(new String[] {
					"Çiğ İnek Eti",
					"Deri"
			})
	),
	SHEEP(Material.MUTTON, "Koyun",
			"Koyunlardan düşen eşyaların ###§7otomatik tüccara eklenmesi için ###§7bu ayarı aktif edebilirsin.",
			Arrays.asList(new Material[] {
					Material.WOOL,
					Material.MUTTON
			}),
			Arrays.asList(new String[] {
					"Yün",
					"Çiğ Koyun Eti"
			})
	),
	PIG(Material.PORK, "Domuz",
			"Domuzlardan düşen eşyaların ###§7otomatik tüccara eklenmesi için ###§7bu ayarı aktif edebilirsin.",
			Arrays.asList(new Material[] {
					Material.PORK
			}),
			Arrays.asList(new String[] {
					"Çiğ Domuz Pirzolası"
			})
	),
	CHICKEN(Material.RAW_CHICKEN, "Tavuk",
			"Tavuklardan düşen eşyaların ###§7otomatik tüccara eklenmesi için ###§7bu ayarı aktif edebilirsin.",
			Arrays.asList(new Material[] {
					Material.RAW_CHICKEN
			}),
			Arrays.asList(new String[] {
					"Çiğ Tavuk"
			})
	),
	RABBIT(Material.RABBIT, "Tavşan",
			"Tavşanlardan düşen eşyaların ###§7otomatik tüccara eklenmesi için ###§7bu ayarı aktif edebilirsin.",
			Arrays.asList(new Material[] {
					Material.RABBIT_HIDE,
					Material.RABBIT
			}),
			Arrays.asList(new String[] {
					"Tavşan Postu",
					"Çiğ Tavşan Eti"
			})
	),
	IRON_GOLEM(Material.IRON_INGOT, "Iron Golem", 
			"Iron Golemlerden düşen eşyaların ###§7otomatik tüccara eklenmesi için ###§7bu ayarı aktif edebilirsin.",
			Arrays.asList(new Material[] {
					Material.IRON_INGOT,
					Material.RED_ROSE
			}),
			Arrays.asList(new String[] {
					"Demir külçesi",
					"Gelincik"
			})
	),
	PIGMAN(Material.GOLD_NUGGET, "Pigman", 
			"Pigmanlerden düşen eşyaların ###§7otomatik tüccara eklenmesi için ###§7bu ayarı aktif edebilirsin.",
			Arrays.asList(new Material[] {
					Material.GOLD_NUGGET,
					Material.ROTTEN_FLESH
			}),
			Arrays.asList(new String[] {
					"Altın Parçası",
					"Çürük et"
			})
	);
	
	private Material icon;
	private String name;
	private String description;
	private List<Material> items;
	private List<String> itemsName;
	
	AutoSell(Material icon, String name, String description, List<Material> items, List<String> itemsName) {
		this.icon = icon;
		this.name = name;
		this.description = description;
		this.items = items;
		this.itemsName = itemsName;
	}
	
	public Material getIcon() {
		return this.icon;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public List<Material> getMaterials() {
		return this.items;
	}
	
	public List<String> getItemsName() {
		return this.itemsName;
	}
	
	public Material getWithMaterial(Material material) {
		return this.items.stream().filter(item -> item.equals(material)).findFirst().orElse(null);
	}
	
	public static AutoSell fromName(String name) {
		return Lists.newArrayList(AutoSell.values()).stream().filter(autoSell -> autoSell.getName().equals(name)).findFirst().orElse(null);
	}
	
	public static AutoSell fromIcon(Material icon) {
		return Lists.newArrayList(AutoSell.values()).stream().filter(autoSell -> autoSell.getIcon() == icon).findFirst().orElse(null);
	}
	
}
