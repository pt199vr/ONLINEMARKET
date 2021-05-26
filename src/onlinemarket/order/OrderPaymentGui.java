package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class OrderPaymentGui extends AnchorPane{
	
	public OrderPaymentGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderPayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
