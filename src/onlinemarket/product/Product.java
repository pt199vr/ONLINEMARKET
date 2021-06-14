package onlinemarket.product;

import java.io.Serializable;
import java.util.TreeSet;
import java.lang.Object;

import onlinemarket.Main;
import onlinemarket.actionsgui.EditorProdModifyGui;
import onlinemarket.departments.*;
import onlinemarket.stages.EditorShopStage;
import onlinemarket.stages.ShopStage;

public class Product implements Serializable,Comparable<Product>, Cloneable{
	private static final long serialVersionUID = 6L;
	protected String name, brand;
	protected Double price;
	//number -> numero di prodotti di questo tipo. quantity -> numero di pezzi per confezione o peso/litri per confezione
	protected Integer quantity,number;
	protected TypeofQuantity type;
	protected TreeSet<String> features;
	protected Department department;
	protected String path;
	
	
	
	public Product(String name, String brand, Double price, Integer quantity, Integer number,TypeofQuantity type, Department department, String... features) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.number = number;
		this.type = type;		
		if(features != null && !features[0].equals("")) {
			this.features = new TreeSet<>();
			for(String feature : features)
				this.features.add(feature);
		}else
			this.features = null;
		this.department = department;
	}
	public Product(String name,String brand,Double price,Integer quantity, Integer number, TypeofQuantity type, Department department, TreeSet<String> features) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
		this.number = number;
		this.type = type;
		this.features = features;
		this.department = department;
	}
	
	public void setNumber(Integer newnumber) {
		this.number = newnumber;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public Department getDepartment() {
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
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public TypeofQuantity getType() {
		return type;
	}
	public TreeSet<String> getFeatures() {
		return features;
	}
	
	public boolean equals(Object other) {
		if(other instanceof Product) {
			Product tmp = (Product) other;
			
			if (this.name.equals(tmp.getName()) && this.brand.equals(tmp.getBrand()) &&	this.quantity.equals(tmp.getQuantity()) &&	this.price.equals(tmp.getPrice()) && this.type.equals(tmp.getType()) && this.department.equals(tmp.getDepartment()))
				return true;
		}
		return false;
	}
	
	public String toString() {
		return String.format("%s %s %s %s %s %s", name, brand, price.toString(), quantity.toString(), type.toString(), department.toString());
	}
	@Override
	public int compareTo(Product o) {
		return (name.hashCode()+brand.hashCode()) - (o.getName().hashCode()+o.getBrand().hashCode());
	}
	
	//serve per sort prodotti tramite feature
	public boolean Features(TreeSet<String> feat) {
		if(feat.isEmpty()|| feat == null) return true;
		if(features.isEmpty()|| features == null) return false;
		
		for(String s: feat)
			if(!features.contains(s))
				return false;
		
		return true;
	}
	
	public void setQuantity(Integer a) {
		this.quantity = a;
	}

	public Double getTotalPrice() {
		return this.price * this.number;
	}
	
}