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
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.account.Address;
import onlinemarket.account.EditorAccount;
import onlinemarket.account.Email;
import onlinemarket.account.Password;
import onlinemarket.account.Role;
import onlinemarket.stages.ActionsStage;
import onlinemarket.stages.EditorShopStageGui;

public class CreateEditorGui extends AnchorPane{
	
	@FXML
	private Label IDL;
	@FXML
	private TextField NameT,SurnameT,AddressT,CityT,CAPT,MailT,CelT;
	@FXML
	private PasswordField PasswordT;
	@FXML
	private ChoiceBox<String> RoleCB;
	@FXML
	private Button CreateB;

	public CreateEditorGui(EditorShopStageGui f) {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("CreateEditor.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		String[] roles = {Role.ADMIN.toString(), Role.EDITOR.toString()};
		for(String s: roles) {
			RoleCB.getItems().add(s);
		}
		
		String id = Main.createId();
		IDL.setText(id);
		CreateB.setOnAction(e ->create(f));
		
	}
	
	private void create(EditorShopStageGui f) {
		String name = NameT.getText(), surname = SurnameT.getText(),address= AddressT.getText(),city = CityT.getText();
		Email mail= new Email(MailT.getText());
		Password pass= new Password(PasswordT.getText());
		
		if(name.equals("")||surname.equals("")|| address.equals("")||city.equals("")|| mail==null || pass == null || CelT.getText().equals("") || CAPT.getText().equals("")) {
			Alert a = new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			a.showAndWait();
			return;
		}
	
		Long cel;
		Integer CAP;
		
		try {
			cel = Long.parseLong(CelT.getText());
			CAP = Integer.parseInt(CAPT.getText()); 
		}catch(NumberFormatException e) {
			Alert b = new Alert(Alert.AlertType.NONE,"Wrong format for CAP or Telephone number",ButtonType.OK);
			b.showAndWait();
			return;
		}
		
		String s= RoleCB.getSelectionModel().getSelectedItem();
		Role r;
		if(s.equals(Role.ADMIN.toString()))
			r =Role.ADMIN;
		else if(s.equals(Role.EDITOR.toString()))
			r=Role.EDITOR;
		else {
			Alert c= new Alert(Alert.AlertType.NONE,"Choose one role please",ButtonType.OK);
			c.showAndWait();
			return;
		}
		
		Address addr= new Address(address, city, CAP);
		
		String id= IDL.getText();
		
		EditorAccount acc = new EditorAccount(id, name, surname, mail, pass, cel, addr, r); 
		
		for(EditorAccount x: Main.editoraccount) {
			if(acc.equals(x)) {
				Alert d= new Alert(Alert.AlertType.NONE,"This account already exists",ButtonType.OK);
				d.showAndWait();
				return;
			}
		}
		
		Main.loadingstage.show();
		Main.editoraccount.add(acc);
		Main.editoraccount.write();
		Main.actionstage.hide();
		Main.actionstage = new ActionsStage("showeditors",f);
		Main.actionstage.show();
		Main.loadingstage.hide();
		}
}
