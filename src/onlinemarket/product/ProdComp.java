package onlinemarket.product;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ProdComp {
	//Serial ID
	
	public static Comparator<Product> getAscPrice(){
		return new Comparator<Product>(){

			@Override
			public int compare(Product p1, Product p2) {
				int diff = p1.getPrice().compareTo(p2.getPrice());
				
				if(diff == 0) {
					diff = p1.getName().compareToIgnoreCase(p2.getName());
				}
				if(diff == 0) {
					diff = p1.getBrand().compareToIgnoreCase(p2.getBrand());
				}
				return diff;
			}
				
		};
	}
	public static Comparator<Product> getDescPrice(){
		return new Comparator<Product>(){

			@Override
			public int compare(Product p1, Product p2) {
				int diff = p2.getPrice().compareTo(p1.getPrice());
				
				if(diff == 0) {
					diff = p2.getName().compareToIgnoreCase(p1.getName());
				}
				if(diff == 0) {
					diff = p2.getBrand().compareToIgnoreCase(p1.getBrand());
				}
				return diff;
			}
				
		};
	}
	public static Comparator<Product> getAscBrand(){
		return new Comparator<Product>(){

			@Override
			public int compare(Product p1, Product p2) {
				int diff = p1.getName().compareToIgnoreCase(p2.getName());
				
				
				if(diff == 0) {
					diff = p1.getBrand().compareToIgnoreCase(p2.getBrand());				
					}
				
				if(diff == 0) {
					diff = p1.getPrice().compareTo(p2.getPrice());
				}				
				return diff;
			}
		};
	}
	public static Comparator<Product> getDescBrand(){
		return new Comparator<Product>(){

			@Override
			public int compare(Product p1, Product p2) {
				int diff = p2.getName().compareToIgnoreCase(p1.getName());
				
				
				if(diff == 0) {
					diff = p2.getBrand().compareToIgnoreCase(p1.getBrand());				
					}
				
				if(diff == 0) {
					diff = p2.getPrice().compareTo(p1.getPrice());
				}				
				return diff;
			}
		};
	}

		
	public Double TotalPrice() {
		Double res = 0D;
		/*for(Product prod : )
			res += prod.getPrice();*/
		return res;
	}
	public TreeSet<Product> get(Comparator<Product> comp, TreeSet<String> feature, String s) {
		TreeSet<Product> sortProd= new TreeSet<>(comp);
		return sortProd;
	}
}