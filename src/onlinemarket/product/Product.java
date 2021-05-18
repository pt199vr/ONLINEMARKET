package onlinemarket.product;

import java.io.Serializable;
import java.util.TreeSet;

import onlinemarket.actionsgui.EditorProdModifyGui;

public class Product implements Serializable,Comparable<Product>{
	private static final long serialVersionUID = 6L;
	
	transient ProductGui gui = null;
	
	protected final String name, brand;
	protected Double price, quantity;
	protected TypeofQuantity type;
	protected final TreeSet<String> features;
	protected String department;
	protected String path;
	
	public Product(String name, String brand, Double price, Double quantity, TypeofQuantity type, String department, String... features) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.type = type;		
		if(features != null && !features[0].equals("")) {
			this.features = new TreeSet<>();
			for(String feature : features)
				this.features.add(feature);
		}else
			this.features = null;
		this.department = department;
	}
	public Product(String name,String brand,Double price,Double quantity,TypeofQuantity type, String department, TreeSet<String> features) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.type = type;
		this.features = features;
		this.department = department;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public TypeofQuantity getType() {
		return type;
	}
	public TreeSet<String> getFeatures() {
		return features;
	}
	
	public void setGui() {
		gui = new EditorProdModifyGui(this,quantity);
	}
	
	public ProductGui getGui() {
		return gui;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Product) {
			Product tmp = (Product) other;
			
			if (this.name.equals(tmp.getName()) && this.brand.equals(tmp.getBrand()) &&	this.quantity.equals(tmp.getQuantity()) &&	this.price.equals(tmp.getPrice()) && this.type.equals(tmp.getType()))
				return true;
		}
		return false;
	}
	
	public String toString() {
		return String.format("%s %s %s %s %s %s", name, brand, price.toString(), quantity.toString(), type.toString(), department);
	}
	@Override
	public int compareTo(Product o) {
		return (name.hashCode()+brand.hashCode()) - (o.getName().hashCode()+o.getBrand().hashCode());
	}
	
}