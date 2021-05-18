package onlinemarket.stages;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.account.*;

public class RegistrationGui extends AnchorPane{
	
	@FXML 
	private TextField NameT, MailT, CelT, CAPT, CityT, SurnameT, AddrT;
	@FXML
	private PasswordField PasswordF;
	@FXML
	private Button BackB, RegB;
	@FXML
	private Label NameL, SurnameL, CityL, MailL, CAPL, CelL, PasswordL, AddrL;
	
	
	public RegistrationGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registration.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Thread back = new Thread(() -> {
			
			BackB.setOnAction(e -> backFunction());
			
			RegB.setOnAction(e -> registerFunction());
			RegB.setOnKeyPressed(keyEvent->{
				if(keyEvent.getCode() == KeyCode.ENTER)
					registerFunction();
			});
			
		});back.start();
		
	}

	private void backFunction() {
		Main.registrationstage.hide();
		Main.firststage.show();
	}
	
	private void registerFunction() {
		
		Main.registrationstage.hide();
		Main.loadingstage.show();
		
		if(NameT.getText() == "" || SurnameT.getText() == "" || CityT.getText() == "" || AddrT.getText() == "" || MailT.getText() == "" ||  PasswordF.getText() == "" || CelT.getText() == "" || CAPT.getText() == "") {
			Alert a = new Alert(Alert.AlertType.NONE, "Fill all fields", ButtonType.OK);
			Main.loadingstage.hide();
			a.showAndWait();
			Main.registrationstage.show();
			return;
		}
		
		Account account;
		
		try {
			String name = NameT.getText(), surname = SurnameT.getText(), city = CityT.getText(),  address = AddrT.getText();
			Email email = new Email(MailT.getText()); 
			Password password = new Password(PasswordF.getText());
			Long phonenumber = Long.parseLong(CelT.getText());
			Integer cap = Integer.parseInt(CAPT.getText());
			
			try {
				checkAll(name, surname, phonenumber, cap, city, address);
			}catch(IllegalArgumentException e) {
				Alert d=new Alert(Alert.AlertType.NONE,e.toString(),ButtonType.CLOSE);
				d.showAndWait();
				Main.registrationstage.show();
				return;
			}
			
			Address where = new Address(address, city, cap);
			if(where.getNumber() == "" || where.getStreet() == "") {
				Alert d=new Alert(Alert.AlertType.NONE,"Compilare il campo indirizzo con Via Nomedellavia Numcivico",ButtonType.CLOSE);
				d.showAndWait();
				Main.registrationstage.show();
				return;
			}
			account = new Account(name, surname, email, password, phonenumber, where);
			
			
			try {
				Main.account.read();
			}catch(IllegalArgumentException e) {
			};
			
			if(Main.account.add(account)) {				
				Main.account.write();
			}else {
				Alert c = new Alert(Alert.AlertType.NONE, "Impossible to register your account!", ButtonType.CLOSE);
				Main.loadingstage.hide();
				c.showAndWait();
				Main.registrationstage.show();
				return;
			}
				
		}catch(IllegalArgumentException e) {
			Alert b = new Alert(Alert.AlertType.NONE, e.toString(), ButtonType.OK);
			b.setHeight(0);
			b.setWidth(0);			
			Main.loadingstage.hide();
			b.showAndWait();
			Main.registrationstage.show();
			return;
		}
		Main.shopping(account);
	}
	
	private void checkAll(String name, String surname, Long phonenumber, Integer cap, String city, String address) throws IllegalArgumentException{
		if(checkName(name))
			throw new IllegalArgumentException("Name field can be filled only by letters from a to z (low and high case)");
		if(checkSurname(surname))
			throw new IllegalArgumentException("Surname field can be filled only by letters from a to z (low and high case)");
		if(checkPhoneNumber(phonenumber))
			throw new IllegalArgumentException("Phone number field can be filled only by numbers from 0 to 9");
		if(checkCap(cap))
			throw new IllegalArgumentException("CAP field can be filled only by numbers from 0 to 9");
		if(checkCity(city))
			throw new IllegalArgumentException("City field can be filled only by letters from a to z (low and high case)");
		if(checkAddress(address))
			throw new IllegalArgumentException("Address field can be filled only by letters from a to z (low and high case) and numbers from 0 to 9");
	}
	
	
	private boolean checkCity(String city) {
		for(int i = 0; i < city.length(); i++) {
			if(!(((char)city.charAt(i) >= 'a' && (char)city.charAt(i) <= 'z') || ((char)city.charAt(i) >= 'A' && (char)city.charAt(i) <= 'Z')|| ((char)city.charAt(i) == ' ')))
				return true;
		}
		
		return false;
	}
	
	private boolean checkCap(Integer cap) {
		String s = cap.toString();
		
		if(s.length() != 5)
			return true;
		
		for(int i = 0; i < s.length(); i++) {
			if(!((char) s.charAt(i) >= '0' && (char) s.charAt(i) <= '9'))
				return true;
		}
		
		return false;
	}
	
	private boolean checkPhoneNumber(Long phonenumber) {
		String s = phonenumber.toString();
		
		for(int i = 0; i < s.length(); i++) {
			if(!((char) s.charAt(i) >= '0' && (char) s.charAt(i) <= '9'))
				return true;
		}
		
		return false;		
	}
	
	private boolean checkSurname(String surname) {
		for(int i = 0; i < surname.length(); i++) {
			if(!(((char)surname.charAt(i) >= 'a' && (char)surname.charAt(i) <= 'z') || ((char)surname.charAt(i) >= 'A' && (char)surname.charAt(i) <= 'Z')|| ((char)surname.charAt(i) == ' ')))
				return true;
		}
		
		return false;
	}
	private boolean checkAddress(String address) {
		for(int i = 0; i < address.length(); i++) {
			if(!(
					   ((char)address.charAt(i) >= 'a' && (char)address.charAt(i) <= 'z') 
					|| ((char)address.charAt(i) >= 'A' && (char)address.charAt(i) <= 'Z') 
					|| ((char)address.charAt(i) == ' ') 
					|| ((char)address.charAt(i) >= '0' && (char)address.charAt(i) <= '9')
					))
				return true;
		}
		
		return false;
	}
	
	private boolean checkName(String name) {
		for(int i = 0; i < name.length(); i++) {
			if(!(((char) name.charAt(i) >= 'a' && (char)name.charAt(i) <= 'z') || ((char)name.charAt(i) >= 'A' && (char)name.charAt(i) <= 'Z')))
				return true;
		}
		
		return false;
	}
	
}
