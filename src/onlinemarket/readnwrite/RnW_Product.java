package onlinemarket.readnwrite;

import onlinemarket.account.Account;
import onlinemarket.product.Product;

public class RnW_Product extends RnW<Product>{
	private static final long serialVersionUID = 14L;

	public RnW_Product(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Customers Accounts:\n";
		int j = 0;
		for(Product i : this) {
			j++;
			r += j + ") " + i.toString() + "\n";
		}
		
		return r + "\n";
	}
	
	public void errorReading() {
		
	}

}
