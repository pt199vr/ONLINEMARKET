package onlinemarket.stages;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.person.user.User;


public class LoginGui extends AnchorPane{
	@FXML
	private Button LogB;
	@FXML
	private RadioButton ClientRB,EmpRB; 
	@FXML
	private TextField EmailF;
	@FXML
	private PasswordField PasswordF;
	@FXML
	private Label LogL,MailL,PasswordL;
	
		
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
			
			
		});buttons.start();
				
	}
	
}
