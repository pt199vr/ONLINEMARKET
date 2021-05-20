package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.account.Address;
import onlinemarket.account.EditorAccount;
import onlinemarket.account.Email;
import onlinemarket.account.Password;
import onlinemarket.stages.EditorShopStageGui;
import onlinemarket.account.Role;

public class EditorAccGui extends AnchorPane{
	@FXML
	protected TextField NameT,SurnameT,AddressT,CityT,CAPT,MailT,CelT;
	@FXML
	protected PasswordField PasswordT;
	@FXML
	protected Label IDL;
	@FXML
	protected Button ConfirmB;
	@FXML
	protected ChoiceBox<String> RoleCB;
	
	public EditorAccGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditorAccountView.fxml"));
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
		CelT.setText(f.getAccount().getPhoneNumber().toString());
		AddressT.setText(f.getAccount().getAddress().getStreet()+ " "+ f.getAccount().getAddress().getNumber());
		CAPT.setText(f.getAccount().getAddress().getCap().toString());
		CityT.setText(f.getAccount().getAddress().getCity());
		IDL.setText(f.getAccount().getId());
		
		
		
		ConfirmB.setOnAction(e -> mod());
		ConfirmB.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER)
				mod();
		});
		
		String[] roles = {Role.ADMIN.toString(), Role.EDITOR.toString()};
		for(String s: roles) {
			RoleCB.getItems().add(s);
		}
		
	}
	
	@FXML
	private void mod() {
		
		Email mail= new Email(MailT.getText());
		Password pass= new Password(PasswordT.getText());
		String name = NameT.getText(), surname = SurnameT.getText(),id = IDL.getText(), street = AddressT.getText(),city= CityT.getText();
		Long cel = Long.parseLong(CelT.getText());
		Integer CAP= Integer.parseInt(CAPT.getText());
		
		if(mail == null || pass == null || name.equals("") || surname.equals("") ||street.equals("")||city.equals("")|| CAP == null || cel == null) {
			Alert a= new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		String s = RoleCB.getSelectionModel().getSelectedItem();
		Role r = null;
		if(s == null) {
			Alert a = new Alert(Alert.AlertType.NONE,"Choose at least one role",ButtonType.OK);
			a.showAndWait();
			return;
		}
		if(s.equals(Role.ADMIN.toString()))
			r = Role.ADMIN;
		if(s.equals(Role.EDITOR.toString()))
			r = Role.EDITOR;
		
		Address addr= new Address(street, city, CAP);
		EditorAccount newAcc = new EditorAccount(id,name,surname,mail,pass,cel,addr,r);
		
		for(EditorAccount x :Main.editoraccount) {
			if(newAcc.equals(x))
				x = newAcc;
		}
		
		Main.editoraccount.write();
		Main.actionstage.hide();
		Main.shopstage.show();
	}
	
}