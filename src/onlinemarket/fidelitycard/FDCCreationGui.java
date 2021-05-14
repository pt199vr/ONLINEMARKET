package onlinemarket.fidelitycard;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import onlinemarket.stages.ShopStageGui;

public class FDCCreationGui extends AnchorPane{
	
	public FDCCreationGui(ShopStageGui f) {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("FidelityCardCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException	e) {
			throw new RuntimeException(e);
		}
	}

}
