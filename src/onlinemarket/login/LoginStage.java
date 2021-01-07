package onlinemarket.login;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main;

public class LoginStage extends Stage{
	
	private BorderPane layout;
	
	public LoginStage() {
		Main.loadingstage.show();
		if(Main.loadingstage != null) {
			System.gc();
		}
		
		
		new Thread(() -> {
			layout = new BorderPane();
			
			
			
			
			
			
		});
		
	}
	
	
}
