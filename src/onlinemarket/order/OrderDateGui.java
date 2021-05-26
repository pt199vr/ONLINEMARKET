package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

public class OrderDateGui extends AnchorPane{
	
	@FXML
	private ChoiceBox<String> FHourCB,FMinuteCB,SHourCB,SMinuteCB,DayCB;
	
	public OrderDateGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderDate.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Integer day = java.time.LocalDateTime.now().getDayOfMonth();
		Integer month = java.time.LocalDateTime.now().getMonthValue();
		Integer year = java.time.LocalDateTime.now().getYear();
		
		
	}

}
