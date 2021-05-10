package onlinemarket.departments;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import onlinemarket.Main;

public class EditorCreateDepGui {
	@FXML
	private TextField DepNameT;
	@FXML
	private Button CreateDepB;
	@FXML
	private Label wL;
	
	public EditorCreateDepGui() {
		
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
			wL.setText("Fill all the fields");
			return;
		}
		
		for(Department d: Main.department)
			if(name.equalsIgnoreCase(d.getName())) {
				wL.setText("This name has been given to another Department previously");
				return;
			}
		
		Department d= new Department(name);
		Main.department.add(d);
		Main.department.getGui().rfct(d);
		DepNameT.setText("");
		wL.setText("Finished!");
		
		new Thread(() -> Main.department.write()).start();
	}

}
