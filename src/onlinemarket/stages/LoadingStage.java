package onlinemarket.stages;

import onlinemarket.*;
import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;



public class LoadingStage extends Stage{
	
	public LoadingStage() {
		new ThreadExceptionHandler();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loading.fxml"));
		AnchorPane pane = new AnchorPane();
		fxmlLoader.setRoot(pane);
		fxmlLoader.setController(this);
		
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		setScene(new Scene(pane));
		initStyle(StageStyle.UNDECORATED);
		getIcons().add(Main.logo);
		initStyle(StageStyle.TRANSPARENT);
		getScene().setFill(Color.TRANSPARENT);
		setTitle(Main.title);
			
	}
	
	
}
