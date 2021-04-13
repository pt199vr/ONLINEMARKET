package onlinemarket.stages;

import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import onlinemarket.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

public class RegistrationStage extends Stage{
	
	private BorderPane layout;
	public RegistrationGui registrationgui;
	
	public RegistrationStage() {
		
		Main.loadingstage.show();
		System.gc();
			
		new Thread(() -> {
			layout = new BorderPane();
			registrationgui = new RegistrationGui();
			layout.setCenter(registrationgui);
			
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(768);
			setWidth(1024);
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
