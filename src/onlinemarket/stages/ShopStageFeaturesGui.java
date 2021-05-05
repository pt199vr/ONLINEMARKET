package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.departments.DepartmentGui;
import onlinemarket.shop.Shop;

import onlinemarket.departments.*;

public class ShopStageFeaturesGui extends ShopStageGui{

	@FXML
	private VBox featureVB, DepartmentsVB;

	
	public ShopStageFeaturesGui(){
		ArrayList<Thread> t = new ArrayList<>(deps.size());
		deps.forEach(d ->{
			Thread thread= new Thread(()->d.setGui());
			thread.start();
			t.add(thread);
		});
		VBox featuresB = new VBox();
		FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("ShopFeatures.fxml"));
		fxmlLoader2.setRoot(featuresB);
		fxmlLoader2.setController(this);
				
		try {
			fxmlLoader2.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		filterVB.getChildren().add(featuresB);
				
		for(String f: Shop.features) {
			CheckBox cb= new CheckBox(f);
			cb.selectedProperty().addListener((o,ov,nv)->{
				if(nv)
					feat.add(cb.getText());
				else
					feat.remove(cb.getText());	
				});
			featureVB.getChildren().add(cb);
		}
	}
}
