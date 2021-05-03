package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.departments.DepartmentGui;

public class ShopStageFeaturesGui extends ShopStageGui{

	@FXML
	private VBox featureVB, DepartmentsVB;
	
	private ArrayList<DepartmentGui> Dep;
	
	public TreeSet<String> feat;
	
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
		Main.loadingstage.show();
		mainVB.getChildren().clear();
		DepartmentsVB.getChildren().clear();
		
		boolean Vuoto = true, NonVuoto;
		ArrayList<RadioButton> buttons = new ArrayList<>();
		for(Department d: Main.departments) {
			NonVuoto = true;
			if(NonVuoto) {
			mainVB.getChildren().add(d.getGui());
			RadioButton depRB = new RadioButton(d.getName());
			buttons.add(depRB);
			depRB.setOnMouseClicked(e ->{
				Dep.clear();
				buttons.forEach(b->b.setSelected(false));
				depRB.setSelected(true);
				Dep.add(d.getGui());
				});
			DepartmentsVB.getChildren().add(depRB);
			}
			if(NonVuoto) {
				Dep.add(d.getGui());
				Vuoto = false;
			}
		}
		if(Vuoto) {
			Main.loadingstage.hide();
			Alert a = new Alert(Alert.AlertType.NONE,"Not Found", ButtonType.OK);
			a.showAndWait();
			
		}
		Main.loadingstage.hide();
		
	}
}
