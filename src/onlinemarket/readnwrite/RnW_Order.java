package onlinemarket.readnwrite;


import onlinemarket.order.*;

public class RnW_Order extends RnW<Order>{
	private static final long serialVersionUID = 19L;
	
	public RnW_Order(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Orders :\n";
		int i = 0;
		for(Order j: this) {
			i++;
			r += i + ")" +j.toString()+"\n";
			
		}
		return r +"\n";
	}
	
	public void Object() {
	}
	
	
}
