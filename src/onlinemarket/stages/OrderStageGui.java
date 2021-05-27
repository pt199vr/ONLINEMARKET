package onlinemarket.stages;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import onlinemarket.account.Account;
import onlinemarket.order.OrderTableGui;
import onlinemarket.order.ProductTableGui;

public class OrderStageGui extends BorderPane{
	
	@FXML
	private BorderPane orderBorder;
	
	private Account t;
	private OrderTableGui orders;
	private ProductTableGui prods;
	
	public OrderStageGui(Account t) {
		
		this.t=t;
		
		orders = new OrderTableGui();
		prods = new ProductTableGui();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Order.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	private Account getAccount() {
		return t;
	}
}
