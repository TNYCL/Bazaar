package cr.shop.module;

import cr.shop.enums.ConfirmType;

public class Confirm {
	
	private Order order;
	private ConfirmType type;
	
	public Confirm(Order order, ConfirmType type) {
		this.order = order;
		this.type = type;
	}

	public Order getOrder() {
		return this.order;
	}
	
	public ConfirmType getType() {
		return this.type;
	}
	
}
