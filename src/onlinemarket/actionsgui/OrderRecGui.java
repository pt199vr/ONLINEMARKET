package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import onlinemarket.Main;
import onlinemarket.order.Order;
import onlinemarket.order.OrderDateGui;
import onlinemarket.order.OrderFidelityGui;
import onlinemarket.order.OrderNewFidelityGui;
import onlinemarket.order.OrderPaymentGui;
import onlinemarket.product.Product;
import onlinemarket.stages.CartStageGui;
import onlinemarket.stages.OrderStage;
import onlinemarket.stages.ShopStage;

public class OrderRecGui extends BorderPane {
	
	@FXML
	private Label FinalPriceL;
	@FXML
	private Button BackB,ContinueB;
	@FXML
	private VBox recapVB;
	private Order o = null;
	private int orderActions = 0;
	
	OrderNewFidelityGui newFC;
	OrderFidelityGui FC;
	OrderPaymentGui pay;
	OrderDateGui date;
	
	public OrderRecGui(CartStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderRecap.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		String s = ""; s += f.getCart().getPrice().intValue() + ".";
		Double doub = f.getCart().getPrice() - f.getCart().getPrice().intValue();
		doub *= 100;
		s += doub.intValue();		
		FinalPriceL.setText(s);
		
		
		FC = new OrderFidelityGui();
		pay = new OrderPaymentGui();
		date = new OrderDateGui();
		
		Fidelity(f);
		
		ContinueB.setOnAction(e -> {
			if(orderActions == 0) {
				Pay();
			}
			else if(orderActions == 1)
				Date();
			else { 
				boolean check = true;
				for(Product p: Main.product) {
					if(f.getCart().getProducts().containsKey(p)) {
						Integer NumberInCart = f.getCart().getProducts().get(p) ;
						if(p.getNumber() >= NumberInCart) {
							p.setNumber(p.getNumber() - NumberInCart);
							Main.product.write();
							((ShopStage)Main.shopstage).getGui().checking();
						}
						else {
							Alert q = new Alert(Alert.AlertType.NONE,"The product "+p.getName().toString() +" "+" " + p.getBrand().toString()+ " Stock is insufficient!",ButtonType.OK);
							q.showAndWait();
							f.getCart().remove(p);
							check = false;
						}
					}
				}
				if(check) {
				o = new Order(date.getDate(), null, null, f.getCart().getProducts(), f.getAccount(),f.getCart().getPrice(), pay.getPM());
				Main.order.add(o);
				Main.order.write();
				f.getCart().getProducts().clear();
				
				
				Main.actionstage.hide();
				Main.orderstage = new OrderStage(f.getAccount());
				Main.orderstage.show();
				}
			}
			
		});
		
		
		BackB.setOnAction(e -> {
			if(orderActions == 0) {
			Main.loadingstage.show();
			Main.actionstage.hide();
			Main.cartstage.show();
			Main.loadingstage.hide();
			}
			else if(orderActions == 1) {
				Fidelity(f);
			}
			else
				Pay();
		});
	}
	
	private void Fidelity(CartStageGui f) {
		orderActions = 0;
		
		setCenter(FC);
	}
	
	private void Pay() {
		orderActions = 1;
		setCenter(pay);
		
	}
	private void Date() {
		if(!pay.check()) {
			Alert a= new Alert(Alert.AlertType.NONE,"Please choose a payment method first",ButtonType.OK);
			a.showAndWait();
			return;
		}
		orderActions = 2;
		setCenter(date);
	}
	
	private Order getOrder() {
		return o;
	}
}

