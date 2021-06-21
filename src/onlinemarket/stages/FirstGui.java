package onlinemarket.stages;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import onlinemarket.Main;

public class FirstGui extends AnchorPane{
	@FXML
	private Button toLogin, CreateAccount;
	
	public FirstGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("firststage.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		Thread choose = new Thread(() -> {			
			
			toLogin.setOnAction(event -> login());			
			
			CreateAccount.setOnAction(event -> registration());			
			
		});	choose.start();
			
	}
	
	private void login() {
		Main.login();
	};
	
	private void registration() {
		Main.registration();
	};
		
}