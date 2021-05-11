package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.departments.Department;

public class ModifyDepGui extends AnchorPane {

	@FXML
	private Label wL;
	@FXML 
	private Button modifyDepB;
	@FXML
	private ChoiceBox<String> DepChoiceB;
	@FXML
	private TextField NewDepNameT;
	
	public ModifyDepGui() {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("DepModify.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
			
		modifyDepB.setOnAction(e -> modify());
		
		modifyDepB.setOnKeyPressed(k ->{
			if(k.getCode() == KeyCode.ENTER)
				modify();
		});
		
		NewDepNameT.setOnKeyPressed(k->{
			if(k.getCode() == KeyCode.ENTER)
				modify();
		});
	}
	
	@FXML
	private void init(){
		DepChoiceB.getItems().clear();
		
		for(Department d: Main.department)
			DepChoiceB.getItems().add(d.getName());
		DepChoiceB.setValue(Main.department.first().getName());
	}
	
	@FXML
	private void modify() {
		String name = NewDepNameT.getText();
		if(name.isEmpty()) {
			wL.setText("Fill the new name field");
			return;
		}
		
		if(name.equalsIgnoreCase(DepChoiceB.getValue())) {
			wL.setText("This name is already the department name");
			return;
		}
		
		for(Department d: Main.department) 
			if(name.equalsIgnoreCase(d.getName())) {
				wL.setText("This name has been given to another department already");
				return;
				}
		Department dep = Main.department.get(DepChoiceB.getValue());
		dep.setName(name);
		Main.department.getGui().rfct(dep);
		new Thread(()-> Main.department.write()).start();
		
		init();
		
		NewDepNameT.setText("");
		wL.setText("Finished");
		
	}

}
