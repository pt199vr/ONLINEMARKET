package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;


public class OrderTableGui extends TableView<Order>{
	@FXML
	private TableView<Order> orders;
	public OrderTableGui() {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("OrdersTable.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
