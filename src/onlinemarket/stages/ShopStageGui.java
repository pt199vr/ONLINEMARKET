package onlinemarket.stages;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import onlinemarket.product.Product;

public class ShopStageGui extends VBox{
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
	
	private ToggleGroup sort;
	
	
	public ShopStageGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopStage.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Thread Search = new Thread(() -> {
			searchButton.setOnAction(e -> searchFunction());
			
			searchButton.setOnKeyPressed(keyEvent -> {
				if (keyEvent.getCode() == KeyCode.ENTER)
					searchFunction();
				});

		});Search.start();
		
		Thread Cancel = new Thread(() ->{
			cancelButton.setOnAction(e -> cancelFunction());
			
		});Cancel.start();
		
		sort = new ToggleGroup();
		BrandAscendingRB.setToggleGroup(sort);
		BrandDescendingRB.setToggleGroup(sort);
		AscendingPriceRB.setToggleGroup(sort);
		DescendingPriceRB.setToggleGroup(sort);
		
		BrandAscendingRB.setSelected(true);
		
	
	}
	
	private void searchFunction() {
		
		Main.loadingstage.show();
		
		if(searchBar.getText().equals("")) {
			Alert a = new Alert(Alert.AlertType.NONE,"Search bar is empty", ButtonType.OK);
			a.showAndWait();
			Main.loadingstage.hide();
			return;
		}
		else {
			String search = searchBar.getText();
			find(search);
		}
		
	}
	
	private void cancelFunction() {
		if( !(searchBar.getText().equals(""))) 
			searchBar.setText("");	
	}
	
	private TreeSet<Product> find(String s) {
		TreeSet<Product> p = new TreeSet<Product>();
		for(Product prods : products) {
			if(prods.getName().equals(s) || prods.getBrand().equals(s)) {
				p.add(prods);
				}
		}
		
		if(p.isEmpty()) {
			Alert b = new Alert(Alert.AlertType.NONE,"I couldn't find any product",ButtonType.OK);
			b.showAndWait();
			Main.loadingstage.hide();
		}
		else {
			return p;
		}
	}

}
