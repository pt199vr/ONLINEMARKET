package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.departments.DepartmentGui;

import onlinemarket.departments.*;

public class ShopStageFeaturesGui extends ShopStageGui{

	@FXML
	private VBox featureVB, DepartmentsVB;
	
<<<<<<< HEAD
	private ArrayList<DepartmentGui> departments;
=======
	private ArrayList<DepartmentGui> Dep;
	
	public TreeSet<String> feat;
>>>>>>> branch 'main' of https://github.com/pt199vr/ONLINEMARKET
	
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

	@Override
	protected void sort() {
		mainVB.getChildren().clear();
		DepartmentsVB.getChildren().clear();
		Dep.clear();
		
		boolean Vuoto = true, NotVuoto;
		ArrayList<RadioButton> buttons= new ArrayList<>();
		for(Department d : Main.store) {
			NotVuoto = d.getGui().sort(comp, feat, search);
			
			if(NotVuoto) {
				mainVB.getChildren().add(d.getGui());
			}
		}
		// TODO Auto-generated method stub
		
	}
}
