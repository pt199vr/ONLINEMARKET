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
import onlinemarket.fidelitycard.FidelityCard;
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
		Account acc = f.getAccount();
		
		NameT.setText(acc.getName());
		SurnameT.setText(acc.getSurname());
		MailT.setText(acc.getEmail().toString());
		PasswordT.setText(acc.getPassword().toString());
		CityT.setText(acc.getAddress().getCity());
		StreetT.setText(acc.getAddress().getStreet());
		NrT.setText(acc.getAddress().getNumber());
		CAPT.setText(acc.getAddress().getCap().toString());
		CelT.setText(acc.getPhoneNumber().toString());

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
		
		for(FidelityCard x: Main.fidelitycard) {
			if(x.getAccount().equals(t.toString()))
				Main.fidelitycard.remove(x);
		}
		Main.account.remove(t);
		
		Main.account.write();
		Main.fidelitycard.write();
		Main.actionstage.close();
		Main.login();
		
	}
	@FXML
	private void mod(Account acc) {
		
		Email mail= new Email(MailT.getText());
		Password pass= new Password(PasswordT.getText());
		String name = NameT.getText(), surname = SurnameT.getText(),street= StreetT.getText();
		Long cel = Long.parseLong(CelT.getText());
		
		if(mail.toString().equals("") || pass.toString().equals("") || name.equals("") || surname.equals("") || cel== null) {
			Alert a= new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.NO);
			a.showAndWait();
			return;
		}
		
		Integer CAP = checkCAP();
		
		Address addr = new Address(street,CityT.getText(),CAP);
		Account newAcc = new Account(name,surname,mail,pass,cel,addr);
		Account oldAcc = null;
		for(Account x :Main.account) {
			if(x.equals(acc)) {
				oldAcc = acc;
			}
		}
		
		
		Main.account.remove(oldAcc);
		Main.account.add(newAcc);
		Main.account.write();
		
		
		
		Main.actionstage.hide();
		Main.shopstage.show();
		
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
