package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import onlinemarket.stages.EditorShopStageGui;

public class EditorOrderTable extends AnchorPane{
	
	@FXML
	private Label showProds;
	@FXML
	private MenuItem setDelivering,setDelivered;
	
	public EditorOrderTable(EditorShopStageGui f) {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("EditorOrders.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		showProds.setOnMouseClicked(e -> {
			
		});
		
		setDelivering.setOnAction(e ->{
			System.out.println("Done");
		});
		setDelivered.setOnAction(e->{
			System.out.println("Re-done");
		});
	}
}
