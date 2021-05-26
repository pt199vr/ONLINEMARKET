package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.order.OrderDateGui;
import onlinemarket.order.OrderFidelityGui;
import onlinemarket.order.OrderPaymentGui;
import onlinemarket.stages.CartStageGui;

public class OrderRecGui extends AnchorPane {
	
	@FXML
	private Label FinalPriceL;
	@FXML
	private Button BackB,ContinueB;
	@FXML
	private VBox recapVB;
	
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
		
		BackB.setOnAction(e -> {
			Main.loadingstage.show();
			Main.actionstage.hide();
			Main.cartstage.show();
			Main.loadingstage.hide();
		});
		
		
		OrderFidelityGui fc = new OrderFidelityGui();
		OrderPaymentGui pay= new OrderPaymentGui();
		OrderDateGui date= new OrderDateGui();
		
		FidelityCard x;
		
		/*recapVB.getChildren().add(fc);
		recapVB.getChildren().add(pay);
		recapVB.getChildren().add(date);*/
	}
}