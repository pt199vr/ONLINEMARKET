package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class EditorsTableGui extends AnchorPane{
	
	public EditorsTableGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditorsAccounts.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
