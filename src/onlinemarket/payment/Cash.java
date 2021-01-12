package onlinemarket.payment;

import java.io.Serializable;
public class Cash implements Payment, Serializable{
	private static final long serialVersionUID = 8L;
	
	public String toString() {
		return "CashPayment";
	}
}
