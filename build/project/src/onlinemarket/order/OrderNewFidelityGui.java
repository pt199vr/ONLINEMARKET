package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.fidelitycard.FidelityCard;
import onlinemarket.stages.ShopStage;

public class OrderNewFidelityGui extends AnchorPane{
		
	@FXML
	private Button GetCardB;
	
	public OrderNewFidelityGui() {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("OrderNewFidelity.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		GetCardB.setOnAction(e -> {
			Main.fidelitycard.add(new FidelityCard(((ShopStage)Main.shopstage).getAccount()));
			Main.fidelitycard.write();
		});
	}

}
