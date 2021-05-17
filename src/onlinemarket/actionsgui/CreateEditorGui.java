package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import onlinemarket.stages.EditorShopStageGui;

public class CreateEditorGui extends AnchorPane{

	public CreateEditorGui(EditorShopStageGui f) {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("CreateEditor.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
	}
}
