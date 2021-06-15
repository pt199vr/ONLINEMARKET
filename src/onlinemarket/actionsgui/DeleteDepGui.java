package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.stages.EditorShopStageGui;
import onlinemarket.product.*;
import java.util.TreeSet;

public class DeleteDepGui extends AnchorPane {
	
	@FXML
	private ChoiceBox<String> DepChoice;
	@FXML
	private Label wL;
	
	@FXML
	private Button DepDelete;
	
	public DeleteDepGui(EditorShopStageGui f) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepDelete.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		init();
		
		DepDelete.setOnAction(e -> delete(f));
		DepDelete.setDisable(Main.department.size() == 1);
		DepDelete.setOnKeyPressed(keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ENTER)
				delete(f);
		});
	}
	private void init() {
		DepChoice.getItems().clear();
		for(Department d: Main.department)
			DepChoice.getItems().add(d.getName());
		
		DepChoice.getSelectionModel().selectFirst();
	}
	
	@FXML
	private void delete(EditorShopStageGui f) {

		if(new Alert(Alert.AlertType.NONE, "If you delete this repartment you will delete all the products!\n\nContinue?",ButtonType.YES,ButtonType.NO).showAndWait().orElse(ButtonType.NO) == ButtonType.NO)
			return;
		
		String delDep = DepChoice.getValue();
		
		Department x = null;
		for(Department d : Main.department) 
			if(d.getName().equalsIgnoreCase(delDep)) 
				x = d;
		Main.department.remove(x);
		Main.depmap.remove(x);
		
		TreeSet<Product> tree = new TreeSet<Product>();
		for(Product p : Main.product) {
			if(x.equals(p.getDepartment())) {
				Main.prodmap.remove(p);
				tree.add(p);
			}
		}
		for(Product c : tree) {
			Main.product.remove(c);
		}
		
		Main.product.write();
		Main.department.write();	
		f.checking();
		Main.actionstage.hide();
	}
}
