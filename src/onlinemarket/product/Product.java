package onlinemarket.product;

import java.util.TreeSet;
import java.io.Serializable;



public class Product implements Serializable{
	private static final long serialVersionUID = 14L;
	
	protected final String name, brand;
	protected final HowMany howmany;
	protected final Price price;
	protected final TreeSet<String> feature;
	
	public Product(String name, String brand, HowMany howmany, Price price, String...strings) {
		this.name = name;
		this.brand = brand;
		this.howmany = howmany;
		this.price = price;
		if(strings != null && !strings[0].equals("")) {
			this.feature = new TreeSet<String>();
			for(String cicle : strings)
				this.feature.add(cicle);
		}
		else
			this.feature = null;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public HowMany getHowMany() {
		return howmany;
	}
	
	public Price getPrice() {
		return price;
	}
	
	public TreeSet<String> getFeature(){
		return feature;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", name, brand, howmany, price, feature);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Product) {
			Product tmp = (Product) other;
			
			if(this.name.equals(tmp.getName()) && this.brand.equals(tmp.getBrand()) && this.howmany.equals(tmp.getHowMany()) 
					&& this.price.equals(tmp.getPrice()))
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean contains(String string) {
		return string == null || name.toLowerCase().contains(string) || brand.toLowerCase().contains(string);
	}
	
	public boolean hasFeatures(TreeSet<String> other) {
		
		if(other == null || other.isEmpty())
			return true;
		
		if(feature == null || feature.isEmpty())
			return false;
		
		for(String cicle : other) {
			if(!feature.contains(other))
				return false;
		}
		
		return true;
		
	}
	
}
