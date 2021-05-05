package onlinemarket.departments;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import onlinemarket.product.Product;

public class DepartmentGui extends TitledPane {
	@FXML
	private VBox prodVB;
	protected TreeSet<Product> sortProd;
	private Department department;
	
	public boolean sort(Comparator<Product> comp, TreeSet<String> feature, String search) {
		if(department.getName().toLowerCase().contains(search))
			search = null;
		
		sortProd= department.prod.get(comp,feature,search);
		show();
		return (sortProd.size() == 0)? false : true;
		
	}
	public void show() {
		
	}
	
	public DepartmentGui(Department department) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(DepartmentGui.class.getResource("DepView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		setText(department.getName());
		this.department = department;
		
	}
	

}
