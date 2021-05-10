package onlinemarket.stages;

import java.io.IOException;
import java.util.Iterator;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.account.*;



public class LoginGui extends AnchorPane{
	@FXML
	private Button LogB, BackB, ELogB, EBackB;
	@FXML
	private TextField EmailF, id;
	@FXML
	private PasswordField PasswordF, EPasswordF;
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
			ELogB.setOnAction(e -> loginEditor());
			
			LogB.setOnKeyPressed(keyEvent->{
				if(keyEvent.getCode() == KeyCode.ENTER)
					login();
			});
			ELogB.setOnKeyPressed(keyEvent->{
				if(keyEvent.getCode() == KeyCode.ENTER)
					login();
			});
			
		});buttons.start();
		
				
	}
	
private void loginEditor() {
	Main.loginstage.hide();
	Main.loadingstage.show();
	
	
	if(id.getText() == "" || EPasswordF.getText() == ""){
		Alert a = new Alert(Alert.AlertType.NONE, "Fill all fields", ButtonType.OK);
		Main.loadingstage.hide();
		a.showAndWait();
		Main.loginstage.show();
		return;
	}
	
	try {
		String a = id.getText(), b = EPasswordF.getText();
		boolean bo = false;
		if(Main.editoraccount.read()) {
			for(EditorAccount e : Main.editoraccount) {
				if(a.equals(e.getId()) && b.equals(e.getPassword().toString())) {	
					bo = true;
					shoppingEditor(e);	
				}
			}
		}
		
		
		if(!bo) {
			Alert c = new Alert(Alert.AlertType.NONE, "Wrongs Credentials", ButtonType.CLOSE);
			Main.loadingstage.hide();
			c.showAndWait();
			Main.loginstage.show();
		}
			
	}catch(IllegalArgumentException e) {
		Alert b = new Alert(Alert.AlertType.NONE, e.toString(), ButtonType.OK);
		b.setHeight(0);
		b.setWidth(0);			
		Main.loadingstage.hide();
		b.showAndWait();
		Main.loginstage.show();
		return;
	}
		
	
}
	
private void login() {
		
		Main.loginstage.hide();
		Main.loadingstage.show();
		
		if(EmailF.getText() == "" || PasswordF.getText() == "") {
			Alert a = new Alert(Alert.AlertType.NONE, "Fill all fields", ButtonType.OK);
			Main.loadingstage.hide();
			a.showAndWait();
			Main.loginstage.show();
			return;
		}
		
		try {
			Email email = new Email(EmailF.getText()); 
			Password password = new Password(PasswordF.getText());
			boolean bo = false;
			if(Main.account.read()) {
				for(Account t : Main.account) {
					if(t.getEmail().toString().equals(email.toString()) && t.getPassword().toString().equals(password.toString())) {						
						bo = true;
						shopping(t);	
					}
				}
			}
			
			
			if(!bo) {
				Alert c = new Alert(Alert.AlertType.NONE, "Wrongs Credentials", ButtonType.CLOSE);
				Main.loadingstage.hide();
				c.showAndWait();
				Main.loginstage.show();
			}
				
		}catch(IllegalArgumentException e) {
			Alert b = new Alert(Alert.AlertType.NONE, e.toString(), ButtonType.OK);
			b.setHeight(0);
			b.setWidth(0);			
			Main.loadingstage.hide();
			b.showAndWait();
			Main.loginstage.show();
			return;
		}
		
	}
	
	private void shopping(Account t) {
		Main.shopping(t);
	}
	
	private void shoppingEditor(EditorAccount t) {
		
	}
	
	private void backFunction() {
		Main.loginstage.hide();
		Main.firststage.show();
	}
	
}
