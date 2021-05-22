package onlinemarket.product;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import onlinemarket.Main;

public class ProductSorting {
	
	public static Comparator<Product> AscendingBrand(){
		return new Comparator<Product>(){

			@Override
			public int compare(Product p1, Product p2) {
				int diff = p1.getBrand().compareTo(p2.getBrand());
				
				if(diff == 0)
					diff = p1.getQuantity().compareTo(p2.getQuantity());
				
				if(diff == 0)
					diff = p1.getPrice().compareTo(p2.getPrice());
				
				return diff;
			}
		};
	}
	
	public static Comparator<Product> DescendingBrand(){
		return new Comparator<Product>(){

			@Override
			public int compare(Product p1, Product p2) {
				int diff = p2.getBrand().compareTo(p1.getBrand());
				
				if(diff == 0)
					diff = p2.getQuantity().compareTo(p1.getQuantity());
				
				if(diff == 0)
					diff = p2.getPrice().compareTo(p1.getPrice());
				
				return diff;
			}
		};	
	}
	
	public static Comparator<Product> AscendingPrice(){
		return new Comparator<Product>() {

			@Override
			public int compare(Product p1, Product p2) {
				
				int diff = p1.getPrice().compareTo(p2.getPrice());
				
				if(diff == 0)
					diff = p1.getQuantity().compareTo(p2.getQuantity());
				
				if(diff == 0)
					diff = p1.getName().compareTo(p2.getName());
				return diff;
			}
			
		};
	}
	
	public static Comparator<Product> DescendingPrice(){
		return new Comparator<Product>() {

			@Override
			public int compare(Product p1, Product p2) {
				
				int diff = p2.getPrice().compareTo(p1.getPrice());
				
				if(diff == 0)
					diff = p2.getQuantity().compareTo(p1.getQuantity());
				
				if(diff == 0)
					diff = p2.getName().compareTo(p1.getName());
				return diff;
			}
			
		};
	}
	
	public static TreeSet<Product> sortedProds(Comparator<Product> comp,TreeSet<String> features, String s){
		TreeSet<Product> ProdTree = new TreeSet<>(comp);
		
		for(Product p : Main.product)
			if((s == null || p.getName().contains(s) || p.getBrand().contains(s)) && p.Features(features))
				ProdTree.add(p);
		
		return ProdTree;
	}
}
