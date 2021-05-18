package onlinemarket.payment;

import onlinemarket.account.*;
import onlinemarket.datentime.*;
import java.io.Serializable;

public class Payment implements Serializable, Comparable<Payment>{
	private static final long serialVerisonUID = 18L;
	private PaymentType type;
	private String email;
	private String password;
	private String number, owner, cvv, account;
	private Integer month, year;
	
	
	public Payment(String account) {
		this.type = PaymentType.CASH;
		this.account = account;
		email = null;
		password = null;
		month = null;
		year = null;
		number = null;
		owner = null;
		cvv = null;
	}
	
	public Payment(String number, String owner, Integer year, Integer month, String cvv, String account) {
		this.type = PaymentType.CREDITCARD;
		this.number = number;
		this.owner = owner;
		this.cvv = cvv;
		this.month = month;
		this.year = year;
		this.account = account;
		email = null;
		password = null;
	}
	
	public Payment(String email, String password, String account) {
		this.type = PaymentType.PAYPAL;
		this.email = email;
		this.password = password;
		this.account = account;
		month = null;
		year = null;
		number = null;
		owner = null;
		cvv = null;
	}
	
	public Integer getYear(){
		return year;
	}
	
	public Integer getMonth() {
		return month;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getCVV() {
		return cvv;
	}
	
	public String getType() {
		return type.toString();
	}
	
	public String getAccount() {
		return account;
	}
	
	public String toString() {
		if(type.toString().equals(PaymentType.CASH))
			return PaymentType.CASH + " " +  account;
		if(type.toString().equals(PaymentType.CREDITCARD))
			return PaymentType.CREDITCARD + " " + number + " " + owner + " " + cvv + " " + year + " " + month + account;
		if(type.toString().equals(PaymentType.CREDITCARD))
			return PaymentType.CREDITCARD + " " + email + " " + password + account;
		
		return account;
	}
	
	public boolean equals(Object t) {
		if(t instanceof Payment) {
			Payment tmp = (Payment) t;
			
			if(tmp.getType().equals(type.toString())) {
				if(tmp.getType().equals(PaymentType.CASH))
					return true;
				if(tmp.getType().equals(PaymentType.CREDITCARD) && tmp.getCVV().equals(cvv) && tmp.getYear().equals(year) && tmp.getMonth().equals(month)&& tmp.getNumber().equals(number) && tmp.getOwner().equals(owner)) {
					return true;
				}
				if(tmp.getType().equals(PaymentType.PAYPAL) && tmp.getEmail().equals(email) && tmp.getPassword().equals(password)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int compareTo(Payment t) {
		return account.hashCode() - t.getAccount().hashCode();
	}
}
