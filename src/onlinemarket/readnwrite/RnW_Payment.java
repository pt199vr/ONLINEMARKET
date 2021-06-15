package onlinemarket.readnwrite;

import onlinemarket.payment.*;

public class RnW_Payment extends RnW<Payment>{
	private static final long serialVersionUID = 17L;
	
	public RnW_Payment(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Payments:\n";
		int j = 0;
		for(Payment i : this) {
			j++;
			r += j + ") " + i.toString() + "\n";
		}
		
		return r + "\n";
	}
	
	public void Object() {
	}
		

}
