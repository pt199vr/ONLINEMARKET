package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class ShopStageFeaturesGui extends ShopStageGui{

	@FXML
	private VBox featureVB, DepartmentsVB;
	
	public ShopStageFeaturesGui(){
		VBox featuresB = new VBox();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopFeatures.fxml"));
		fxmlLoader.setRoot(featuresB);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
}
