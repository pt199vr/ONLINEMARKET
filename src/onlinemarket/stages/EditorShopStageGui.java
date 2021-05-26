package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.stage.Modality;
import onlinemarket.Main;
import onlinemarket.account.EditorAccount;
import onlinemarket.account.Role;
import onlinemarket.actionsgui.EditorProdModifyGui;
import onlinemarket.departments.Department;
import onlinemarket.departments.DepartmentGui;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;
import onlinemarket.product.ProductSorting;
import onlinemarket.readnwrite.RnW_Product;

public class EditorShopStageGui extends VBox{
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
	private Menu Orders;
	@FXML
	private MenuItem create, modify, delete, createProd, deleteProd, profile, editorsAcc, customersAcc, logout;
	
	private ToggleGroup sort;
	private final HashMap<RadioButton,Comparator<Product>> sortProd;
	
	protected Comparator<Product> comp;
	protected String search;
	
	protected ArrayList<Department> deps;
	private ArrayList<DepartmentGui> selD;
	protected TreeSet<String> feat;
	private ArrayList<RadioButton> bs = new ArrayList<>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	private EditorAccount t;
	
	public EditorShopStageGui(EditorAccount t) {
		
		this.t = t;
		
		search = "";
		feat = new TreeSet<>();
		selD = new ArrayList<>();
		bs = new ArrayList<>();
		
		tt();
		
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("EditorShopStage.fxml"));
		fxml.setRoot(this);
		fxml.setController(this);
			
		try {
			fxml.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		tfxml();
		
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
		
		comp = ProductSorting.AscendingBrand();

		sortProd = new HashMap<>(4);
		sortProd.put(AscendingBrandRB, ProductSorting.AscendingBrand());
		sortProd.put(DescendingBrandRB, ProductSorting.DescendingBrand());
		sortProd.put(AscendingPriceRB, ProductSorting.AscendingPrice());
		sortProd.put(DescendingPriceRB, ProductSorting.DescendingPrice());
		
		sort.selectedToggleProperty().addListener((o,oT,nT)->{
			comp = sortProd.get((RadioButton)sort.getSelectedToggle());
			sort();
		});
		
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
		
		create.setOnAction(e -> {
			creation(this);
			sort();
			});
		modify.setOnAction(e -> {
			modification(this);
			sort();
			});
		delete.setOnAction(e -> {
			delete(this);
			sort();
			});
		createProd.setOnAction(e -> {
			createprod(this);
			sort();
			});
		deleteProd.setOnAction(e -> {
			deleteprod(this);
			sort();
		});
		
		profile.setOnAction(e -> showAcc(this));
		editorsAcc.setOnAction(e -> showEd(this));
		customersAcc.setOnAction(e->customers(this));
		logout.setOnAction(e -> logout());
		Orders.setOnAction(e -> showOrders(this));
		
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
		DepartmentsVB.getChildren().clear();
		selD.clear();	
		bs.clear();
		boolean notFound = true, Found;
		
		for(Department d:Main.department) {
			Found = Main.depmap.get(d).sort(comp, feat, search);
			if(Found) {
				mainVB.getChildren().add(Main.depmap.get(d));
				RadioButton depRB = new RadioButton(d.getName());
				bs.add(depRB);
				depRB.setMinHeight(20);
				depRB.setOnMouseClicked(e->{
					selD.clear();
					bs.forEach(b -> b.setSelected(false));
					depRB.setSelected(true);
					selD.add(Main.depmap.get(d));
					expand();
				});
				DepartmentsVB.getChildren().add(depRB);	
				selD.add(Main.depmap.get(d));
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
		expand();
	}
	
	
	public void cancelFunction() { 
		searchBar.setText("");
		search = "";
		
		featuresVB.getChildren().forEach(f ->((CheckBox)f).setSelected(false));
		
		sort();
	}
	
	public void expand() {
		if(selD.size() < Main.department.size()) 
			for(DepartmentGui depG: Main.depmap.values()) {
				depG.setExpanded(false);
			}
		selD.forEach(depGui ->depGui.setExpanded(true));
	}
	
	
	public void rfct(Department dep) {
		cancelFunction();
		selD.clear();
		selD.add(Main.depmap.get(dep));
		
		expand();
	}
	@FXML
	public void logout() {
		Main.department.write();
		Main.product.write();
		Main.shopstage.close();
		Main.login();
	}
	
	private void creation(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("create", f);
		Main.loadingstage.hide();
	}
	
	private void modification(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("modify", f);
		Main.loadingstage.hide();
		
	}
	
	private void delete(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("delete", f);
		Main.loadingstage.hide();
	}
	
	private void createprod(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("prodCreation",f);
		Main.loadingstage.hide();
	}
	private void deleteprod(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("prodElimination",f);
		Main.loadingstage.hide();
	}
	private void showAcc(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("showaccount", f);
		Main.loadingstage.hide();
	}
	
	private void showEd(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		if(f.t.getRole() == Role.ADMIN)
			Main.actionstage = new ActionsStage("showeditors", f);
		else {
			Alert a = new Alert(Alert.AlertType.NONE, "You must be an admin to access to this part", ButtonType.OK);
			a.initModality(Modality.APPLICATION_MODAL);
			a.showAndWait();		
		}
		Main.loadingstage.hide();
	}
	
	private void customers(EditorShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("showcustomers", f);
		Main.loadingstage.hide();
	}
	
	private void showOrders(EditorShopStageGui f){
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("showorders", f);
		Main.loadingstage.hide();
	}
	
	public void checking() {
		tt();
		for(Product p : Main.product) {
			ProductGui g = new EditorProdModifyGui(p);
			Main.prodmap.put(p, g);
		}
		for(Department d : Main.department) {
			Main.depmap.put(d, new DepartmentGui(d));	
		}
		mainVB.getChildren().clear();
		tfxml();
		
		Main.shopstage.show();
	}
	private void tt() {
		Main.department.read();
		Main.department.forEach(d->{
			Thread thread = new Thread(() -> Main.depmap.put(d, new DepartmentGui(d)));
			thread.start();
			threads.add(thread);
		});
	}
	
	private void tfxml() {
		DepartmentsVB.getChildren().clear(); 
		for(Department d: Main.department) {
			mainVB.getChildren().add(Main.depmap.get(d));
				RadioButton depRB= new RadioButton(d.getName());
				bs.add(depRB);
				depRB.setMinHeight(20);
				depRB.setOnMouseClicked(e -> {
					selD.clear();
					bs.forEach(b -> b.setSelected(false));
					depRB.setSelected(true);
					selD.add(Main.depmap.get(d));
					expand();
				});
				DepartmentsVB.getChildren().add(depRB);	
		}
	}

	public EditorAccount getAccount() {
		return t;
	}
}


