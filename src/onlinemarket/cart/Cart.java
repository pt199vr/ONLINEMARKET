package onlinemarket.cart;

import onlinemarket.product.Sorting;

public class Cart extends Sorting{
	private static final long serialVersionUID = 20L;
	
	private transient CartGui gui;
	
	public void setGui() {
		gui = new CartGui();
	}
	
	public CartGui getGui() {
		return gui;
	}

}
