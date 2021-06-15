package onlinemarket.order;

import java.math.*;
import java.io.Serializable;
import onlinemarket.product.*;
import onlinemarket.Main;
import onlinemarket.datentime.*;
import onlinemarket.account.*;
import onlinemarket.payment.*;
import onlinemarket.fidelitycard.*;
import java.util.TreeSet;
import java.util.HashMap;

public class Order implements Serializable,Comparable<Order>{
	private static final long serialVersionUID = 20L;
	private String ID;
	private Date date;
	private Time time1, time2;
	private TreeSet<Product> products;
	private Account account;
	private Double price;
	private Payment payment;
	private Integer points = 0;
	private OrderStatus status = OrderStatus.CONFIRMED;
	
	public Order(Date date, Time time1, Time time2, TreeSet<Product> products, Account account, Double price, Payment payment) {
		this.ID = Main.getIdOrder();
		this.date = date;
		this.time1 = time1;
		this.time2 = time2;
		this.products = products;				
		this.account = account;
		this.price = Math.round(price * 100.0) / 100.0;
		this.payment = payment;
		for(FidelityCard f : Main.fidelitycard) {
			if(f.getAccount().equals(this.account.toString()))
				this.points = price.intValue();		
		}
	}
	
	public String getPaymentType() {
		return this.payment.getType().toString();
	}
	
	public void setDELIVERED() {
		this.status = OrderStatus.DELIVERED;
	}
	
	public void setDELIVERING() {
		this.status = OrderStatus.DELIVERING;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public TreeSet<Product> getProducts(){
		return products;
	}
	
	public String getTimeInterval() {
		return time1 + " - " + time2;
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
	
	public String toString() {
		return ID + "||" + account.toString() + "||" + payment.toString() + "||" + price.toString() + "||" + points.toString()
		+ "||" + date.toString() + "||" + time1.toString() + "->" + time2.toString() + "||" + products.toString();
	}

	@Override
	public int compareTo(Order o) {
		return this.getId().hashCode() - o.getId().hashCode();
	}
	
}
