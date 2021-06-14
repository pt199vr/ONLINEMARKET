package onlinemarket.departments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;
import onlinemarket.product.ProductSorting;
import onlinemarket.product.UserProdGui;
import onlinemarket.stages.ShopStage;
import onlinemarket.Main;
import onlinemarket.actionsgui.EditorProdModifyGui;

public class DepartmentGui extends TitledPane {
	@FXML
	protected VBox prodVB;
	protected TreeSet<Product> sortProd = new TreeSet<>();
	private Department department;
	private ProductGui g;


		
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
		for(Product p: Main.product) {
			if(p.getDepartment().equals(department)) {
				if(Main.shopstage instanceof ShopStage)
					g = new UserProdGui(p);
				else
					g = new EditorProdModifyGui(p);
				prodVB.getChildren().add(g);
				
			}
		}		
	}
	
	public boolean sort(Comparator<Product> comp, TreeSet<String> feature, String search) {
		if(department.getName().toLowerCase().contains(search)||department.getName().toLowerCase().equals(search))
			search = null;
		sortProd = ProductSorting.sortedProds(comp, feature, search);
		view();
		
		return (sortProd.size() == 0)? false: true;		
	}
	
	public void view() {
		if(sortProd == null)
			sort(ProductSorting.AscendingBrand(), new TreeSet<>(), "");
		prodVB.getChildren().clear();
		sortProd.forEach(p ->{
			if(p.getDepartment().equals(department)) {
				if(Main.shopstage instanceof ShopStage)
					g = new UserProdGui(p);
				else
					g = new EditorProdModifyGui(p);
				prodVB.getChildren().add(g);
			}
		});
		
	}
	
	public ObservableList<Node> getProds() {
		return prodVB.getChildren();	
	}
}