package onlinemarket.stages;

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
			
			setTitle(Main.title);
			setHeight(768);
			setWidth(1024);
			setResizable(false);
			setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			
			Platform.runLater(() ->{
				setScene(new Scene(layout));
				show();
				//Main.loadingstage.hide();
				
			});
			
			
			
			
		}).start();
		
	}
	
	
}
