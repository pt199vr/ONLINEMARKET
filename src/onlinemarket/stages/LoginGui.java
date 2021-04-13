package onlinemarket.stages;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;



public class LoginGui extends AnchorPane{
	@FXML
	private Button LogB, BackB;
	@FXML
	private TextField EmailF;
	@FXML
	private PasswordField PasswordF;
	@FXML
	private Label LogL,MailL,PasswordL;
	@FXML
	private TabPane EditorTab, CustomerTab;


	
		
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
			
		});buttons.start();
				
	}
	
	private void backFunction() {
		Main.loginstage.hide();
		Main.firststage.show();
	}
	
}
