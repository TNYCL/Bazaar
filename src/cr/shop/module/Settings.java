package cr.shop.module;

import cr.shop.enums.AutoSell;

public class Settings {
	
	private boolean status;
	private AutoSell selected;
	
	public Settings(boolean status, AutoSell selected) {
		this.status = status;
		this.selected = selected;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public AutoSell getSelected() {
		return this.selected;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

}
