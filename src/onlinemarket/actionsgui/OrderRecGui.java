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
import onlinemarket.order.OrderDateGui;
import onlinemarket.order.OrderFidelityGui;
import onlinemarket.order.OrderNewFidelityGui;
import onlinemarket.order.OrderPaymentGui;
import onlinemarket.stages.CartStageGui;
import onlinemarket.stages.OrderStage;

public class OrderRecGui extends BorderPane {
	
	@FXML
	private Label FinalPriceL;
	@FXML
	private Button BackB,ContinueB;
	@FXML
	private VBox recapVB;
	
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
		
		FinalPriceL.setText(f.getCart().getPrice().toString());
		
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
				
				Main.actionstage.hide();
				Main.orderstage = new OrderStage(f.getAccount());
				Main.orderstage.show();
				
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
}

