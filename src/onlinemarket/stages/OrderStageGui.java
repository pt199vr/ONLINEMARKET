package onlinemarket.stages;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.order.OrderTableGui;
import onlinemarket.order.ProductTableGui;

public class OrderStageGui extends GridPane{
	
	@FXML
	private GridPane TablesGridPane;
	
	private Account t;
	
	@FXML
	private Button MenuB;
	
	private OrderTableGui orders;
	private ProductTableGui prods;

	
	
	public OrderStageGui(Account t) {
		
		this.t = t;
		
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
		
		MenuB.setOnAction(e ->{
			Main.loadingstage.show();
			Main.orderstage.hide();
			Main.shopstage.show();
			Main.loadingstage.hide();
		});
		
		TablesGridPane.add(orders, 0, 1);
		TablesGridPane.add(prods, 1, 1);
		
	}
	
	private Account getAccount() {
		return t;
	}
}
