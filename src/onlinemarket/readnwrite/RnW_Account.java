package onlinemarket.readnwrite;


import onlinemarket.account.*;



public class RnW_Account extends RnW<Account>{
	private static final long serialVersionUID = 4L;

	public RnW_Account(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Customers Accounts:\n";
		int j = 0;
		for(Account i : this) {
			j++;
			r += j + ") " + i.toString() + "\n";
		}
		
		return r + "\n";
	}
	
	public void Object() {
		System.out.println("Account");
	}
		
}
