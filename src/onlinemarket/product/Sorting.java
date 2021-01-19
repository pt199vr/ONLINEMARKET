package onlinemarket.product;

import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;



public class Sorting extends HashMap<Product, Integer>{
	private static final long serialVersionUID = 18L;
	
	public static Comparator<Product> priceAscendSorting(){
		return new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				int diff = p1.getPrice().compareTo(p2.getPrice());
				
				if(diff == 0)
					diff = p1.getName().compareToIgnoreCase(p2.getName());
				
				if(diff == 0)
					diff = p1.getBrand().compareToIgnoreCase(p2.getBrand());
				
				return diff;
			}
		};
	}
	
	public static Comparator<Product> priceDescendSorting(){
		return new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				int diff = p2.getPrice().compareTo(p1.getPrice());
				
				if(diff == 0)
					diff = p2.getName().compareToIgnoreCase(p1.getName());
				
				if(diff == 0)
					diff = p2.getBrand().compareToIgnoreCase(p1.getBrand());
				
				return diff;
			}
		};
	}
	
	public static Comparator<Product> brandAscendSorting(){
		return new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				int diff = p1.getName().compareToIgnoreCase(p2.getName());
				
				if(diff == 0)
					diff = p1.getBrand().compareToIgnoreCase(p2.getBrand());
				
				if(diff == 0)
					diff = p1.getPrice().compareTo(p2.getPrice());
				
				return diff;
			}
		};
	}
	
	public static Comparator<Product> brandDescendSorting(){
		return new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				int diff = p2.getName().compareToIgnoreCase(p1.getName());
				
				if(diff == 0)
					diff = p2.getBrand().compareToIgnoreCase(p1.getBrand());
				
				if(diff == 0)
					diff = p2.getPrice().compareTo(p1.getPrice());
				
				return diff;
			}
		};
	}
	
	public SortedSet<Product> get(Comparator<Product> comparator, TreeSet<String> features, String key){
		SortedSet<Product> sortedproducts = new TreeSet<>(comparator);
		
		for(Product product : keySet())
			if(product.hasFeatures(features) && product.contains(key)) 
				sortedproducts.add(product);
		
		return sortedproducts;
	}
	
	public Price getTotalPrice() {
		Float result = 0F;
		
		for(Product product : keySet())
			result += (product.getPrice().getPrice() * get(product));
		
		return new Price(result);
	}
		
}
