package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.departments.DepartmentGui;
import onlinemarket.product.Product;
import onlinemarket.shop.Shop;

public class ShopStageGui extends VBox{
	@FXML
	private Button searchButton, cancelButton;
	@FXML
	private TextField searchBar;
	@FXML 
	private RadioButton AscendingBrandRB,DescendingBrandRB, AscendingPriceRB,DescendingPriceRB;
	@FXML
	private ScrollPane scrollP;
	@FXML
	private SplitPane splitP;
	@FXML
	private Region scrollR;
	@FXML
	private GridPane searchGridPane;
	@FXML
	protected VBox filterVB,mainVB, featuresVB, DepartmentsVB;
	@FXML
	protected MenuBar MenuB;
	
	private ToggleGroup sort;
	
	protected Comparator<Product> comp;
	protected String search;
	
	protected ArrayList<Department> deps;
	private ArrayList<DepartmentGui> selD;
	protected TreeSet<String> feat;
	
	public ShopStageGui() {
		search="";
		deps = new ArrayList<>();
		deps.add(new Department("Fruits"));
		deps.add(new Department("Meat"));
		deps.add(new Department("Vegetables"));
		
		ArrayList<Thread> threads = new ArrayList<>(deps.size());
		deps.forEach(d->{
			Thread thread = new Thread(() -> d.setGui());
			thread.start();
			threads.add(thread);
		});
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShopStage.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		for(Department d: deps) {
			mainVB.getChildren().add(d.getGui());
			ArrayList<RadioButton> bs= new ArrayList<>();
				RadioButton depRB= new RadioButton(d.getName());
				bs.add(depRB);
				depRB.setOnMouseClicked(e->{
					selD.clear();
					bs.forEach(b->b.setSelected(false));
					depRB.setSelected(true);
					selD.add(d.getGui());
				});
				DepartmentsVB.getChildren().add(depRB);	
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
		AscendingBrandRB.setToggleGroup(sort);
		DescendingBrandRB.setToggleGroup(sort);
		AscendingPriceRB.setToggleGroup(sort);
		DescendingPriceRB.setToggleGroup(sort);
		AscendingBrandRB.setSelected(true);
		
		feat = new TreeSet<>();
		selD = new ArrayList<>();
		for(String f: Shop.features) {
			CheckBox cb= new CheckBox(f);
			cb.selectedProperty().addListener((o,ov,nv)->{
				if(nv)
					feat.add(cb.getText());
				else
					feat.remove(cb.getText());
				sort();
			});
			featuresVB.getChildren().add(cb);
		}
		try {
			for(Thread thread: threads)
					thread.join();
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		
			sort();
	}
	
	
	private void sort() {
	
		mainVB.getChildren().clear();
		DepartmentsVB.getChildren().clear();
		selD.clear();
	
		boolean Found,notFound = true;
		for(Department d:deps) {
			Found = d.getGui().sort(comp, feat, search);
			if(Found) {
				mainVB.getChildren().add(d.getGui());
			}
			if(Found) {
				selD.add(d.getGui());
				notFound=false;
			}
			
		}
		if(notFound) {
			Pane p = new Pane();
			Label l= new Label("Not a product has been found");
			p.getChildren().add(l);
			mainVB.getChildren().add(p);
			
		}
	}
	
	
	private void cancelFunction() { 
		searchBar.setText("");
		search="";
		
		featuresVB.getChildren().forEach(f ->((CheckBox)f).setSelected(false));
	}
	
	

}
