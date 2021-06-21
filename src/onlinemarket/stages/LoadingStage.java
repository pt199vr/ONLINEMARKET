package onlinemarket.stages;

import onlinemarket.*;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
		setTitle(Main.title);	
		getIcons().add(Main.logo);
		initStyle(StageStyle.UNDECORATED);
		initStyle(StageStyle.TRANSPARENT);
		getScene().setFill(Color.TRANSPARENT);
		
		
				
	}
	
	
}
