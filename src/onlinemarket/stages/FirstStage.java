package onlinemarket.stages;

import onlinemarket.*;
import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;



public class FirstStage extends Stage{
	
	private BorderPane layout;
	public FirstGui firstGui;
	
	public FirstStage() {
		
		pathcontrol();	
		
		new Thread(() -> {
			layout = new BorderPane();
			firstGui = new FirstGui();
	    	layout.setCenter(firstGui);
			
	    	
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(400);
			setWidth(600);
			setResizable(false);
			setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
			
			Platform.runLater(() -> {
		        setScene(new Scene(layout));
		        show();		
		        Main.loadingstage.hide();
			}); 	 
			
		}).start();
		
		
	}
	
	private void pathcontrol() {
		File f = new File(Main.path), g = new File(Main.mediapath);
		if(!f.exists() && !f.mkdir())
			FirstError(f);
		if(!g.exists() && !g.mkdir())
			FirstError(g);
	}
	
	private void FirstError(File p) {
		Alert a = new Alert(Alert.AlertType.NONE, "Folder Creation Failed " + p.getName(), ButtonType.OK);
		a.initModality(Modality.APPLICATION_MODAL);
		a.initOwner(this);
		
		if(a.showAndWait().orElse(ButtonType.OK) != null) {
			Platform.exit();
			System.exit(0);
		}
	}
}
