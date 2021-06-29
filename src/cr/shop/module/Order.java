package cr.shop.module;

import org.bukkit.Material;

import cr.shop.enums.Category;

public class Order {
	
	private String 	owner;
	private long 	price;
	private long 	timestamp;
	private Category	category;
	private Material	material;
	private int		data;
	private int		amount;
	
	public Order(String owner, long price, long timestamp, Category category, Material material, int amount, int data) {
		this.owner = owner;
		this.price = price;
		this.timestamp = timestamp;
		this.category = category;
		this.material = material;
		this.amount = amount;
		this.data = data;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public long getPrice() {
		return this.price;
	}
	
	public long getTimeStamp() {
		return this.timestamp;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public int getData() {
		return this.data;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setTimeStamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
