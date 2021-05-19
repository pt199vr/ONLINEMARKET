package onlinemarket.departments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;
import onlinemarket.product.ProductSorting;
import onlinemarket.Main;

public class DepartmentGui extends TitledPane {
	@FXML
	protected VBox prodVB;
	protected TreeSet<Product> sortProd;
	private Department department;
	
	
		
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
	public boolean sort(Comparator<Product> comp, TreeSet<String> feature, String search) {
		for(Product x : Main.product) {
			if(x.getName().equalsIgnoreCase(search))
				return true;
		}
		
		return false;		
	}
}