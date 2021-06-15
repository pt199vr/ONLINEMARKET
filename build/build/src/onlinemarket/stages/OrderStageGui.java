package onlinemarket.stages;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.order.Order;
import onlinemarket.product.OrderProdGui;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;


public class OrderStageGui extends AnchorPane{
	
	
	private Account account;
	private Order order;
	
	@FXML
	private Button MenuB;
	@FXML
	private Label IDL,NameL,SurnameL,priceL,paymentL,DeliveryDateL,DeliveryTimeL;
	@FXML
	private TitledPane OrderedProducts;
	@FXML
	private VBox orderProdVB;
	
	public OrderStageGui(Account t, String id) {
		
		this.account = t;
		for(Order o: Main.order) {
			if(t.equals(o.getAccount()) && id.equals(o.getId())) {
				order  = o;
			}
		}
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Order.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		IDL.setText(order.getId());
		NameL.setText(order.getAccount().getName());
		SurnameL.setText(order.getAccount().getSurname());
		priceL.setText(order.getPrice().toString());
		paymentL.setText(order.getStatus().toString());
		DeliveryDateL.setText("Delivery on: "+order.getDate().toString());
		DeliveryTimeL.setText("At: "+ order.getTime1().toString()+ "-" +order.getTime2().toString());
		
		OrderedProducts.setExpanded(false);
		
		for(Product p: order.getProducts()) {
			ProductGui tmp = new OrderProdGui(p);
			orderProdVB.getChildren().add(tmp);
		}
		
		MenuB.setOnAction(e ->{	
			Main.loadingstage.show();
			Main.shopstage.show();
			Main.loadingstage.hide();
			((CartStage) Main.cartstage).getCartGui().newCart();
			Main.orderstage.close();
		});
	}
	
	private Account getAccount() {
		return account;
	}
}
