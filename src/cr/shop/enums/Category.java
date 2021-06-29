package cr.shop.enums;

public enum Category {

	BLOCKS("Bloklar"),
	REDSTONE("Kızıltaş ve Türevleri"),
	POTION("İksirler"),
	MATERIAL("Malzemeler"),
	FOOD("Yemekler"),
	OTHER("Diğer");
	
	public String categoryName;

	Category(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryName() {
		return this.categoryName;
	}
	
}
