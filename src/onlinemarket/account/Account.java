package onlinemarket.account;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

import onlinemarket.person.*;


public abstract class Account extends TitledPane{

	@FXML 
	protected Label warninglabel;
	
	protected Person person;
	
	@FXML
	protected ProgressIndicator indicator;
	
	@FXML
	protected TextField name, surname, phonenumber, password;
	
	public Account(FXMLLoader loader, Person person) {
		
		loader.setRoot(this);
		loader.setController(this);
		
		try {
			loader.load();
		}catch (IOException exception) {
			
		}
		
		this.person = person;
	}
	
	protected Email checkEmail(String emailstr) {
		Email email;
		
		try {
			email = new Email(emailstr);
		}catch(IllegalArgumentException e) {
			warninglabel.setVisible(true);
			warninglabel.setText("Formato email non valido");
			return null;
		}
		
		
		
		return email;
	}
	
	
	protected Password checkpassword(String passwordstr) {
		Password password;
		
		try {
			password = new Password(passwordstr);
		}catch(IllegalArgumentException e) {
			warninglabel.setVisible(true);
			warninglabel.setText("La password deve avere almeno 7 caratteri");
			return null;
		}
		
		
		
		return password;
	}
	
	protected Long checkphonenumber(String phonenumberstr) {
		Long phonenumber;
		
		try {
			phonenumber = Long.parseLong(phonenumberstr);
			
			if(phonenumberstr.length() < 9 || phonenumberstr.length() > 10)
			 ;
			
		}catch(IllegalArgumentException e) {
			warninglabel.setVisible(true);
			warninglabel.setText("La password deve avere almeno 7 caratteri");
			return null;
		}
		
		
		
		return phonenumber;
		
		
	}
	
	
	
	
}
