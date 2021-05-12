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
import onlinemarket.departments.*;
import onlinemarket.stages.EditorShopStageGui;
import onlinemarket.product.*;

public class ModifyDepGui extends AnchorPane {

	@FXML
	private Label wL;
	@FXML 
	private Button modifyDepB;
	@FXML
	private ChoiceBox<String> DepChoiceB;
	@FXML
	private TextField NewDepNameT;
	
	public ModifyDepGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("DepModify.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
			
		init();
		modifyDepB.setOnAction(e -> modify(f));
		
		modifyDepB.setOnKeyPressed(k ->{
			if(k.getCode() == KeyCode.ENTER)
				modify(f);
		});
		
		NewDepNameT.setOnKeyPressed(k->{
			if(k.getCode() == KeyCode.ENTER)
				modify(f);
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
	private void modify(EditorShopStageGui f) {
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
		Department x = null;
		for(Department d : Main.department) 
			if(DepChoiceB.getValue().equalsIgnoreCase(d.getName())) {
				x = d;
				d.setName(name);
				Main.depmap.remove(x);
				Main.depmap.put(d, new DepartmentGui(d));
				for(Product p : Main.product) {
					if(x.equals(Main.prodepmap.get(p))) {
						Main.prodepmap.put(p, d);
					}
				}
				
			}
		Main.department.write();	
		f.checking();
		Main.actionstage.hide();		
	}

}
