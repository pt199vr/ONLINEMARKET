package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.departments.*;
import onlinemarket.stages.EditorShopStageGui;
import onlinemarket.product.*;
import java.util.TreeSet;

public class ModifyDepGui extends AnchorPane {

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
			Alert a = new Alert(Alert.AlertType.NONE,"Fill the new name field",ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		if(name.equalsIgnoreCase(DepChoiceB.getValue())) {
			Alert a= new Alert(Alert.AlertType.NONE,"This name is already the department name",ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		for(Department d: Main.department) 
			if(name.equalsIgnoreCase(d.getName())) {
				Alert a= new Alert(Alert.AlertType.NONE,"This name has been given to another department already",ButtonType.OK);
				a.showAndWait();
				return;
				}
		Department x = null,temp=null;
		TreeSet<Product> tmp = new TreeSet<>();
		for(Department d : Main.department) 
			if(DepChoiceB.getValue().equalsIgnoreCase(d.getName())) {
				x = d;
				temp = d;
				x.setName(name);
				Main.depmap.remove(d);
				Main.depmap.put(x, new DepartmentGui(x));
				for(Product p : Main.product) {
					if(p.getDepartment().toString().equals(temp.toString())) {
						tmp.add(p);
					}
				}
				Main.product.removeAll(tmp);
			}
		Main.department.remove(temp);
		Main.department.add(x);
		Main.department.write();	
		f.checking();
		Main.actionstage.hide();		
	}

}
