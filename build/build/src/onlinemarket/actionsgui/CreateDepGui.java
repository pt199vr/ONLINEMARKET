package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.stages.EditorShopStage;
import onlinemarket.stages.EditorShopStageGui;


public class CreateDepGui extends AnchorPane{
	
	@FXML
	private TextField DepNameT;
	@FXML
	private Button CreateDepB;

	public CreateDepGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		CreateDepB.setOnAction(e -> create(f));
		CreateDepB.setOnKeyPressed(k-> {
			if(k.getCode() == KeyCode.ENTER)
				create(f);
		});
		
		DepNameT.setOnKeyPressed(keyEvent ->{
			if(keyEvent.getCode() == KeyCode.ENTER)
				create(f);
		});
		
	}
	
	@FXML
	private void create(EditorShopStageGui f) {
		
		if(DepNameT.getText().equals("")) {
			Alert a = new Alert(Alert.AlertType.NONE, "Fill the field", ButtonType.OK);
			Main.actionstage.hide();
			a.showAndWait();
			Main.actionstage.show();
			return;
		}
		String name = DepNameT.getText();
		
		if(Main.department.read()) {
			for(Department d : Main.department) {
				if(name.equalsIgnoreCase(d.getName())) {
					Alert a = new Alert(Alert.AlertType.NONE, "This departmente already exists", ButtonType.OK);
					Main.actionstage.hide();
					a.showAndWait();
					Main.actionstage.show();
					return;
				}
			}
			Main.department.add(new Department(name));
			Main.department.write();
			
		}		
		f.checking();
		Main.actionstage.hide();
	}
	
}
