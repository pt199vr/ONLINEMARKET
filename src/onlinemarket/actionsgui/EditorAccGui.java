package onlinemarket.actionsgui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioMenuItem;
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

public class EditorAccGui extends AnchorPane{
	@FXML
	private TextField NameT,SurnameT,MailT,CelT;
	@FXML
	private PasswordField PasswordT;
	@FXML
	private Label IDL;
	@FXML
	private Button ConfirmB;
	@FXML
	private MenuButton RoleMB;
	
	public EditorAccGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditorAccountView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		EditorAccount acc = f.getAccount();

		NameT.setText(acc.getName());
		SurnameT.setText(acc.getSurname());
		MailT.setText(acc.getEmail().toString());
		PasswordT.setText(acc.getPassword().toString());
		CelT.setText(acc.getPhoneNumber().toString());
		
		ConfirmB.setOnAction(e -> mod(acc));
		ConfirmB.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER)
				mod(acc);
		});
		
	}
	
	@FXML
	private void mod(EditorAccount Acc) {
		
		Email mail= new Email(MailT.getText());
		Password pass= new Password(PasswordT.getText());
		String name = NameT.getText(), surname = SurnameT.getText(),id = IDL.getText();
		Long cel = Long.parseLong(CelT.getText());
		
		if(mail == null || pass == null || name.equals("") || surname.equals("") || cel == null) {
			Alert a= new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.NO);
			a.showAndWait();
			return;
		}
		ArrayList<String> role = new ArrayList<>();
		for(MenuItem mi: RoleMB.getItems()) {
			RadioMenuItem rmi= (RadioMenuItem)mi;
			if(rmi.isSelected())
				role.add(rmi.getText());
		}
		if(role.size() == 0) {
			Alert a = new Alert(Alert.AlertType.NONE,"Choose at least one role",ButtonType.OK);
			a.showAndWait();
			return;
		}
		Address addr= new Address("", "", 1);
		EditorAccount newAcc = new EditorAccount(id,name,surname,mail,pass,cel,addr);
		for(Account x :Main.editoraccount) {
			if(x.equals(Acc))
				Acc = newAcc;
		}
		
		Main.editoraccount.write();
		Main.actionstage.hide();
		Main.shopstage.show();
		
		
		
	}
}

