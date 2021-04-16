package onlinemarket.readnwrite;


import onlinemarket.account.*;
import onlinemarket.readnwrite.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RnW_Account extends RnW<Account>{
	private static final long serialVersionUID = 0L;

	public RnW_Account(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Customers Accounts:\n";
		int j = 0;
		for(Account i : this) {
			j++;
			r += j+ ") " + i.toString() + "\n";
		}
		
		return r + "\n";
	}
	
	public void errorReading() {
		
	}
		
}
