package onlinemarket.departments;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DepartmentGui extends BorderPane {
	@FXML
	private VBox productsVB;
	
	public DepartmentGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		
		
		
		
	}

}
