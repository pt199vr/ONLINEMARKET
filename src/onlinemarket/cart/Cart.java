package onlinemarket.cart;

import onlinemarket.product.*;
import java.util.HashMap;

public class Cart {
	
	HashMap<Product, Integer> products;
	Double price;
	
	public Cart() {
		products = new HashMap<>();
		price = 0.0;
	}
		
	public void remove(Product a) {
		Integer tmp = products.get(a);
		if(tmp > 1) {
			tmp--;
			price -= a.getPrice();
			products.put(a, tmp);
		}		
		else {
			products.remove(a);
			price -= a.getPrice();
		}
	}
	
	public void add(Product a) {
		if(products.containsKey(a)) {
			Integer tmp = products.get(a);
			tmp++;
			products.put(a, tmp);
			price += a.getPrice();
		}
		else {
			products.put(a, 1);
			price += a.getPrice();
		}
	}
	
	public HashMap<Product, Integer> getProducts(){
		return products;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public String toString() {
		return products.toString() + " " + price;
	}
}
