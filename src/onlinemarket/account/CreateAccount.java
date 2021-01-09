package onlinemarket.account;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Button;

import onlinemarket.person.*;

public class CreateAccount extends Account{

	@FXML
	protected TextField email;
	
	@FXML
	protected Button button;
	
	public CreateAccount(FXMLLoader loader, Person person) {
		super(loader, person);
		
		
	}
	
}
