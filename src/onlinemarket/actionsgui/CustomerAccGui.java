package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.account.Address;
import onlinemarket.account.Email;
import onlinemarket.account.Password;
import onlinemarket.stages.ShopStageGui;

public class CustomerAccGui extends AnchorPane{
	
	@FXML
	private TextField NameT, SurnameT,MailT,CityT,CAPT,CelT,StreetT,NrT;
	@FXML
	private PasswordField PasswordT;
	@FXML
	private Button DeleteB,BackB;
	
	public CustomerAccGui(ShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserAccountView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		NameT.setText(f.getAccount().getName());
		SurnameT.setText(f.getAccount().getSurname());
		MailT.setText(f.getAccount().getEmail().toString());
		PasswordT.setText(f.getAccount().getPassword().toString());
		CityT.setText(f.getAccount().getAddress().getCity());
		StreetT.setText(f.getAccount().getAddress().getStreet());
		NrT.setText(f.getAccount().getAddress().getNumber());
		CAPT.setText(f.getAccount().getAddress().getCap().toString());
		CelT.setText(f.getAccount().getPhoneNumber().toString());
		
		Account acc = f.getAccount();

		DeleteB.setOnAction(e -> delete());
		DeleteB.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
				delete();
		});
		
		BackB.setOnAction(e -> mod(acc));
		BackB.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER)
				mod(acc);
		});
		
	}
	@FXML
	private void delete() {
		
		if(new Alert(Alert.AlertType.NONE, "You will delete this account!\n\n Continue?",ButtonType.YES,ButtonType.NO).showAndWait().orElse(ButtonType.NO) == ButtonType.NO)
			return;
		String mail= MailT.getText(), pass= PasswordT.getText(); 
		Account t= null;
		
		for(Account x : Main.account) {
			if(mail.equals(x.getEmail().toString()) && pass.equals(x.getPassword().toString()))
				t = x;
		}
		
		Main.account.remove(t);
		Main.account.write();
		
		Main.login();
		
	}
	@FXML
	private void mod(Account acc) {
		
		Email mail= new Email(MailT.getText());
		Password pass= new Password(PasswordT.getText());
		String name = NameT.getText(), surname = SurnameT.getText(),street= StreetT.getText();
		Long cel = Long.parseLong(CelT.getText());
		
		if(mail.equals("") || pass.equals("") || name.equals("") || surname.equals("") || cel.equals("")) {
			Alert a= new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.NO);
			a.showAndWait();
			return;
		}
		
		Integer CAP = checkCAP();
		
		Address addr = new Address(street,CityT.getText(),CAP);
		Account newAcc = new Account(name,surname,mail,pass,cel,addr);
		for(Account x :Main.account) {
			if(x.equals(acc))
				acc = newAcc;
		}
		
		Main.account.write();
		
		
	}
	private Integer checkCAP() {
		Integer cap;
		try {
			String SCap= CAPT.getText();
			cap = Integer.parseInt(SCap);
			if(SCap.length() != 5)
				throw new NumberFormatException();
		}catch(NumberFormatException e) {
			Alert a= new Alert(Alert.AlertType.NONE,"Invalid CAP",ButtonType.OK);
			a.showAndWait();
			cap= null;
		}
		return cap;
	}

}
