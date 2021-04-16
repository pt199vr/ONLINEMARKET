package onlinemarket.stages;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.account.Email;
import onlinemarket.account.Password;



public class LoginGui extends AnchorPane{
	@FXML
	private Button LogB, BackB, ELogB, EBackB;
	@FXML
	private TextField EmailF, id;
	@FXML
	private PasswordField PasswordF, EPassword;
	@FXML
	private Label LogL,MailL,PasswordL, ELogL, Matricola, EPasswordL;
	@FXML
	private Tab EditorTab, CustomerTab;


	
		
	public LoginGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Thread buttons = new Thread( () -> {
			
			BackB.setOnAction(e -> backFunction());
			EBackB.setOnAction(e ->backFunction());
			
			LogB.setOnAction(e -> login());
			ELogB.setOnAction(e -> login());
			
		});buttons.start();
		
				
	}
	
private void nextStep(Account t) {
	
}	
	
private void login() {
		
		Main.registrationstage.hide();
		Main.loadingstage.show();
		
		if(EmailF.getText() == "" || PasswordF.getText() == "") {
			Alert a = new Alert(Alert.AlertType.NONE, "Fill all fields", ButtonType.OK);
			Main.loadingstage.hide();
			a.showAndWait();
			Main.registrationstage.show();
			return;
		}
		
		try {
			Email email = new Email(EmailF.getText()); 
			Password password = new Password(PasswordF.getText());
			
			if(Main.account.read()) {
				for(Account t : Main.account.iterator()) {
					if(t.getEmail() == email && t.getPassword() == password)
						nextStep(t);
				}
					
			
			}else {
				Alert c = new Alert(Alert.AlertType.NONE, "Wrongs Credentials", ButtonType.CLOSE);
				Main.loadingstage.hide();
				c.showAndWait();
				Main.registrationstage.show();
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
	}
	
	
	
	private void backFunction() {
		Main.loginstage.hide();
		Main.firststage.show();
	}
	
}
