package onlinemarket.order;

import java.io.Serializable;
import onlinemarket.product.*;
import onlinemarket.Main;
import onlinemarket.datentime.*;
import onlinemarket.account.*;
import onlinemarket.payment.*;
import java.util.TreeSet;
import java.util.HashMap;

public class Order implements Serializable{
	private static final long serialVersionUID = 20L;
	private String ID;
	private Date date;
	private Time time1, time2;
	private TreeSet<Product> products;
	private Account account;
	private Float price;
	private Payment payment;
	private Integer points;
	
	public Order(Date date, Time time1, Time time2, HashMap<Product, Integer> products, Account account, Float price, Payment payment) {
		this.ID = Main.getIdOrder();
		this.date = date;
		this.time1 = time1;
		this.time2 = time2;
		this.products = new TreeSet<>();
		if(!products.isEmpty()) {
			for(Product a : Main.product) {
				if(products.containsKey(a)) {
					Product tmp = a;
					tmp.setQuantity(products.get(tmp));
					this.products.add(tmp);
				}
			}
		}
		this.account = account;
		this.price = price;
		this.payment = payment;
		this.points = price.intValue();		
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public TreeSet<Product> getProducts(){
		return products;
	}
	
	public Time getTime2() {
		return time2;
	}
	
	public Time getTime1() {
		return time1;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getId() {
		return ID;
	}
}
