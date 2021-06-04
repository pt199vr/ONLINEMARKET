package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import onlinemarket.Main;
import onlinemarket.stages.ShopStageGui;

public class CustomerOrderTable extends AnchorPane{
	
	
	@FXML
	private Label showProds;
	
	public CustomerOrderTable(ShopStageGui f) {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("CustomerOrders.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		showProds.setOnMouseClicked(e -> {
			
		});
		
		
		/*BackB.setOnAction(e ->{
			Main.actionstage.hide();
			Main.loadingstage.show();
			Main.shopstage.show();
			Main.loadingstage.hide();
		});*/
	}

}
