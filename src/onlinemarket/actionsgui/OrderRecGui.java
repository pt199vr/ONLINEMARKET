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
import java.util.TreeSet;

import onlinemarket.Main;
import onlinemarket.fidelitycard.FidelityCard;
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
		Main.shopstage.hide();
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
		pay = new OrderPaymentGui(f.getAccount());
		date = new OrderDateGui();
		
		Fidelity(f);
		ContinueB.setOnAction(e -> {
			if(orderActions == 0) {
				Pay();
			}
			else if(orderActions == 1)
				Date();
			else { 
				date.getTime();
				date.getChosenDate();
				boolean check = true;
				for(Product p: Main.product) {
					if(f.getCart().getProducts().containsKey(p)) {
						Integer NumberInCart = f.getCart().getProducts().get(p);
						if(p.getNumber() >= NumberInCart) {
							Integer r = p.getNumber() - NumberInCart;
							p.setNumber(r);
							Main.product.write();					
						}
						else {
							Alert q = new Alert(Alert.AlertType.NONE,"The product "+p.getName().toString() + " " + p.getBrand().toString() + " Stock is insufficient!",ButtonType.OK);
							q.showAndWait();
							f.getCart().remove(p);
							check = false;
						}
					}
				}
				if(check) {
				for(FidelityCard fc : Main.fidelitycard) {
					if(fc.getAccount().equals(f.getAccount().toString())) {
						int p = f.getCart().getPrice().intValue();
						p += fc.getPoints();
						fc.setPoints(p);
					}		
				}
				
				((ShopStage)Main.shopstage).shopgui.checking();
				Main.shopstage.hide();
				Main.fidelitycard.write();
				Main.payment.write();
				
				TreeSet<Product> tree = new TreeSet<>();
				for(Product p : Main.product) {
					if(f.getCart().getProducts().containsKey(p)) {
						Product prod = new Product(p.getName(), p.getBrand(), p.getPrice(), p.getQuantity(), f.getCart().getProducts().get(p), p.getType(), p.getDepartment(), p.getFeatures());
						tree.add(prod);
					}
				}
				
				o = new Order(date.getDate(), date.getFirstTime(), date.getSecondTime(), tree, f.getAccount(),f.getCart().getPrice(), pay.getPM());
				
				
				Main.order.add(o);
				Main.order.write();
				
				Main.actionstage.hide();
				Main.orderstage = new OrderStage(f, o.getId());
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
		pay.check();
		orderActions = 2;
		setCenter(date);
	}
	
	private Order getOrder() {
		return o;
	}
}

