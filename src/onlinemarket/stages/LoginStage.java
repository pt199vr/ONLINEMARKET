package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;




import onlinemarket.Main;

public class LoginStage extends Stage{
	
	private BorderPane layout;
	public LoginGui loginGui;
		
	public LoginStage() {
				
		Main.loadingstage.show();
		System.gc();
			
		new Thread(() -> {
			layout = new BorderPane();
			loginGui = new LoginGui();
			layout.setCenter(loginGui);
			
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(480);
			setWidth(600);
			setResizable(false);			
			Platform.runLater(() -> {
		        setScene(new Scene(layout));
		        show();		
		        Main.loadingstage.hide();
			}); 
						
			setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
		}).start();
		
	}
	
	
}
