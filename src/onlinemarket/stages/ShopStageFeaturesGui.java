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
	
	private ArrayList<DepartmentGui> Dep;
	
	public TreeSet<String> feat;

	
	public ShopStageFeaturesGui(){
		
		VBox featuresB = new VBox();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopFeatures.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}

		filterVB.getChildren().add(featuresB);
		
		for(Department d: deps) {
			mainVB.getChildren().add(d.getGui());
		}
		
		Dep = new ArrayList<>();
		feat = new TreeSet<>();
		
		sort();

		
		
		

	}
		

	
	
	
	@Override
	protected void sort() {
		}
}
