package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class OrderFidelityGui extends AnchorPane{
	
	public OrderFidelityGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FidelityRecap.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
