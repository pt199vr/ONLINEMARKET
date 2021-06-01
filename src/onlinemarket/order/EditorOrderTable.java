package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import onlinemarket.Main;
import onlinemarket.stages.ActionsStage;
import onlinemarket.stages.EditorShopStageGui;

public class EditorOrderTable extends GridPane{
	
	@FXML
	private Button BackB,ProdTableB;
	
	private OrderTableGui orders;
	
	@FXML 
	private GridPane Tables;
	
	public EditorOrderTable(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("AllOrders.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		orders = new OrderTableGui();
		
		
		BackB.setOnAction(e ->{
			Main.actionstage.hide();
			Main.loadingstage.show();
			Main.shopstage.show();
			Main.loadingstage.hide();
		});
		
		Tables.add(orders, 0, 1);
		
	}
}
