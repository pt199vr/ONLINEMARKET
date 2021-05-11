package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
import onlinemarket.readnwrite.RnW_Product;

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
	private VBox filterVB,mainVB, featuresVB, DepartmentsVB;
	@FXML
	private Menu Purchases, Cart;
	@FXML
	private MenuItem profile,FDC,Orders,payment,logout;
	
	private ToggleGroup sort;
	
	protected Comparator<Product> comp;
	protected String search;
	
	private ArrayList<DepartmentGui> selD;
	protected TreeSet<String> feat;
	private ArrayList<RadioButton> bs = new ArrayList<>();
	
	
	
	public ShopStageGui() {
		
		search="";
		feat = new TreeSet<>();
		selD = new ArrayList<>();
		bs = new ArrayList<>();
		
		ArrayList<Thread> threads = new ArrayList<>(Main.department.size());
		Main.department.forEach(d->{
			Thread thread = new Thread(() -> d.setGui());
			thread.start();
			threads.add(thread);
		});
		
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("CustomerShopStage.fxml"));
		fxml.setRoot(this);
		fxml.setController(this);
			
		try {
			fxml.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		for(Department d: Main.department) {
			mainVB.getChildren().add(d.getGui());
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
		
		for(String f: RnW_Product.features) {
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
		
		//Purchases.setOnAction(e -> showPurchases());
		//Cart.setOnAction(e -> showCart());
		//profile.setOnAction(e -> showAcc());
		//FDC.setOnAction(e -> showFC());
		//Orders.setOnAction(e -> showOrders());
		//payment.setOnAction(e -> setPayment());
		logout.setOnAction(e -> logout());
		
		try {
			for(Thread thread: threads)
					thread.join();
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
		
			sort();
	}
	
	
	public void sort() {

		mainVB.getChildren().clear();
		selD.clear();	
		boolean notFound = true, Found;
		
		for(Department d: Main.department) {
			Found = d.getGui().sort(comp, feat, search);
			if(Found) {
				bs.clear();
				mainVB.getChildren().add(d.getGui());
				RadioButton depRB= new RadioButton(d.getName());
				bs.add(depRB);
				depRB.setOnMouseClicked(e->{
					selD.clear();
					bs.forEach(b->b.setSelected(false));
					depRB.setSelected(true);
					selD.add(d.getGui());
				});
				
				
			}
			if(Found) {
				selD.add(d.getGui());
				notFound = false;
			}
			
		}
		if(notFound) {
			Pane p = new Pane();
			Label l= new Label("Not a product has been found");
			p.getChildren().add(l);
			l.layoutXProperty().bind(p.widthProperty().subtract(l.widthProperty()).divide(2));
			l.layoutYProperty().bind(p.heightProperty().subtract(l.heightProperty()).divide(2));
			mainVB.getChildren().add(p);
		}
	}
	
	
	public void cancelFunction() { 
		searchBar.setText("");
		search = "";
		
		featuresVB.getChildren().forEach(f ->((CheckBox)f).setSelected(false));
		
		sort();
	}
	
	public void rfct(Department dep) {
		dep.setGui();
		
		cancelFunction();
		selD.clear();
		selD.add(dep.getGui());
	}
	
	@FXML
	public void logout() {
		Main.shopstage.close();
		Main.login();
	}
	
}



