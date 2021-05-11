package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.departments.Department;


public class CreateDepGui extends AnchorPane{
	
	@FXML
	private TextField DepNameT;
	@FXML
	private Button CreateDepB;

	public CreateDepGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		CreateDepB.setOnAction(e -> create());
		CreateDepB.setOnKeyPressed(k-> {
			if(k.getCode() == KeyCode.ENTER)
				create();
		});
		
		DepNameT.setOnKeyPressed(keyEvent ->{
			if(keyEvent.getCode() == KeyCode.ENTER)
				create();
		});
		
	}
	
	@FXML
	private void create() {
		String name = DepNameT.getText();
		if(name.isEmpty()) {
			return;
		}
		
		for(Department d: Main.department)
			if(name.equalsIgnoreCase(d.getName())) {
				
				return;
			}
		
		Department d= new Department(name);
		Main.department.add(d);
		Main.department.getGui().rfct(d);
		DepNameT.setText("");
		
		new Thread(() -> Main.department.write()).start();
	}
	
}
