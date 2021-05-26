package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class OrderDateGui extends AnchorPane{
	
	public OrderDateGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderDate.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
