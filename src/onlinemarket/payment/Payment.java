package onlinemarket.payment;

import onlinemarket.account.*;
import onlinemarket.datentime.*;
import java.io.Serializable;

public class Payment implements Serializable, Comparable<Payment>{
	private static final long serialVerisonUID = 18L;
	private PaymentType type;
	private Email email;
	private Password password;
	private String number, owner, cvv;
	private Account account;
	private Date date;
	
	
	public Payment(Account account) {
		this.type = PaymentType.CASH;
		this.account = account;
		email = null;
		password = null;
		date = null;
		number = null;
		owner = null;
		cvv = null;
	}
	
	public Payment(String number, String owner, Integer year, Integer month, String cvv, Account account) {
		this.type = PaymentType.CREDITCARD;
		this.number = number;
		this.owner = owner;
		this.cvv = cvv;
		this.date = new Date(year, month, 1);
		this.account = account;
		email = null;
		password = null;
	}
	
	public Payment(Email email, Password password, Account account) {
		this.type = PaymentType.PAYPAL;
		this.email = email;
		this.password = password;
		this.account = account;
		date = null;
		number = null;
		owner = null;
		cvv = null;
	}
	
	public Date getDate(){
		return date;	}
	
	
	public Email getEmail() {
		return email;
	}
	
	public Password getPassword() {
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
	
	public PaymentType getType() {
		return type;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public String toString() {
		if(type.toString().equals(PaymentType.CASH.toString()))
			return PaymentType.CASH.toString();
		if(type.toString().equals(PaymentType.CREDITCARD.toString()))
			return PaymentType.CREDITCARD.toString();
		if(type.toString().equals(PaymentType.PAYPAL.toString()))
			return PaymentType.PAYPAL.toString();
		
		return account.toString();
	}
	
	public boolean equals(Object t) {
		if(t instanceof Payment) {
			Payment tmp = (Payment) t;
			
			if(tmp.getType().equals(type.toString())) {
				if(tmp.getType().equals(PaymentType.CASH.toString()))
					return true;
				if(tmp.getType().equals(PaymentType.CREDITCARD.toString()) && tmp.getCVV().equals(cvv) && tmp.getDate().equals(this.date)&& tmp.getNumber().equals(number) && tmp.getOwner().equals(owner)) {
					return true;
				}
				if(tmp.getType().equals(PaymentType.PAYPAL.toString()) && tmp.getEmail().equals(email) && tmp.getPassword().equals(password)) {
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
