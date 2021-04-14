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
	@FXML
	private RadioButton EditorRB,CustomerRB;
	
	
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
			
		});back.start();
		
		ToggleGroup rbGroup= new ToggleGroup();
		EditorRB.setToggleGroup(rbGroup);
		CustomerRB.setToggleGroup(rbGroup);
		CustomerRB.setSelected(true);
		EditorRB.requestFocus();
		
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
		
		try {
			String name = NameT.getText(), surname = SurnameT.getText(), city = CityT.getText(),  address = AddrT.getText();
			Email email = new Email(MailT.getText()); 
			Password password = new Password(PasswordF.getText());
			Long phonenumber = Long.parseLong(CelT.getText()), cap = Long.parseLong(CAPT.getText());
			
			Account account = new Account(name, surname, email, password, phonenumber);
			
			if(Main.account.add(account))
				Main.account.write();
			else {
				Alert c = new Alert(Alert.AlertType.NONE, "Impossible to register your account!", ButtonType.CLOSE);
				Main.loadingstage.hide();
				c.showAndWait();
				Main.registrationstage.show();
			}
				
		}catch(IllegalArgumentException e) {
			Alert b = new Alert(Alert.AlertType.NONE, e.toString(), ButtonType.OK);
			Main.loadingstage.hide();
			b.showAndWait();
			Main.registrationstage.show();
			return;
		}	
		Main.account.read();
		System.out.println(Main.account.toString());
	}
		
	private void backFunction() {
		Main.registrationstage.hide();
		Main.firststage.show();
	}
	
}
