package onlinemarket.stages;

import onlinemarket.*;
import onlinemarket.readnwrite.RnW_Account;

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
import onlinemarket.*;

public class StartingStage extends Stage{

	public StartingStage() {
		 new Thread(() -> {				
			 checkPath();
			 Main.threads.put(RnW_Account.class, new Thread(() -> Main.account.start()));
			 Main.threads.values().forEach(thread -> thread.start());
		 }).start();
		 Main.loadingstage.show();	
	}
	
	private void checkPath() {
		File path = new File(Main.path), media = new File(Main.mediapath);
		
		if((!path.exists() && !path.mkdir()) || (!media.mkdir() && !media.exists())) {
			Alert a = new Alert(Alert.AlertType.NONE, "Path wrong" , ButtonType.CLOSE);
			a.initModality(Modality.APPLICATION_MODAL);
			a.initOwner(this);
			a.showAndWait();
			Platform.exit();
			System.exit(0);
		}
			
	}
	
	
}
