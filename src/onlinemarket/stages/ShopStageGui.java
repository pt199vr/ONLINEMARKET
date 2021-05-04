package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.product.Product;

public abstract class ShopStageGui extends VBox{
	@FXML
	private Button searchButton, cancelButton;
	@FXML
	private TextField searchBar;
	@FXML 
	private RadioButton BrandAscendingRB,BrandDescendingRB, AscendingPriceRB,DescendingPriceRB;
	@FXML
	private ScrollPane scrollP;
	@FXML
	private SplitPane splitP;
	@FXML
	private Region scrollR;
	@FXML
	private GridPane searchGridPane;
	@FXML
	protected VBox filterVB,mainVB;
	@FXML
	protected MenuBar MenuB;
	
	private ToggleGroup sort;
	
	protected Comparator<Product> comp;
	protected String search;
	
	protected ArrayList<Department> deps;
	
	public ShopStageGui() {
		
		deps =new ArrayList<>();
		
		deps.add(new Department("Fruits"));
		deps.add(new Department("Meat"));
		deps.add(new Department("Vegetables"));
		
		
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopStage.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		searchButton.setOnAction(e ->{ 
			search = searchBar.getText().toLowerCase();
			sort();
			});
		searchButton.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER) {
				search = searchBar.getText().toLowerCase();
				sort();
				}
		});
		searchBar.setOnKeyPressed(searchButton.getOnKeyPressed());
		
		cancelButton.setOnAction(e -> cancelFunction());
		
		sort = new ToggleGroup();
		BrandAscendingRB.setToggleGroup(sort);
		BrandDescendingRB.setToggleGroup(sort);
		AscendingPriceRB.setToggleGroup(sort);
		DescendingPriceRB.setToggleGroup(sort);
		BrandAscendingRB.setSelected(true);
	
	}
	
	
	
	protected abstract void sort();
	
	private void cancelFunction() {
		if( !(searchBar.getText().equals(""))) 
			searchBar.setText("");
	}
	
	

}
