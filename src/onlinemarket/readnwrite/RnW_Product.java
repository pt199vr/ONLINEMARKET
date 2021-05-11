package onlinemarket.readnwrite;

import onlinemarket.account.Account;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;


public class RnW_Product extends RnW<Product>{
	private static final long serialVersionUID = 14L;
	
	transient private ProductGui gui;

	public static String [] features= {"Gluten Free", "Bio" , "Milk Free", "Vegan"};
	
	public RnW_Product(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Products:\n";
		int j = 0;
		for(Product i : this) {
			j++;
			r += j + ") " + i.toString() + "\n";
		}
		
		return r + "\n";
	}
	
	public void Object() {
		System.out.println("Product");
	}
	
}
