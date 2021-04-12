package onlinemarket.stages;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import onlinemarket.Main;

public class FirstGui extends AnchorPane{
	
	private Label Login, Register;
	private Button toLogin, CreateAccount;
	
	public FirstGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstStage.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		Thread usersThread = new Thread(() -> {
							
			toLogin.setDisable(false);
    		toLogin.setOnAction(event -> Main.login());
        	toLogin.setOnKeyPressed(event -> Main.login());
        	
        	CreateAccount.setDisable(false);
        	CreateAccount.setOnAction(event -> Main.registration());
        	CreateAccount.setOnKeyPressed(event -> Main.registration());
		usersThread.start();
		
		
		
	}
	

}
