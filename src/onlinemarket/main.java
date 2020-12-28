package onlinemarket;
import onlinemarket.calendar.*;
public class main {

	public static void main(String[] args) {
		DataTime d=new DataTime(12,12,2020,13,50);
		System.out.println(d);
		DataTime d2=new DataTime(12,12,2020,13,50);
		boolean e= d.equals(d2);
		System.out.println(e);
		
	

	}

}
