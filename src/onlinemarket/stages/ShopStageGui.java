package onlinemarket.stages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.actionsgui.EditorProdModifyGui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

import onlinemarket.cart.*;
import onlinemarket.departments.Department;
import onlinemarket.departments.DepartmentGui;
import onlinemarket.fidelitycard.FidelityCard;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;
import onlinemarket.product.ProductSorting;
import onlinemarket.product.UserProdGui;
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
	private Label CartL,PurcasesL;
	@FXML
	private MenuItem profile,FDC,Orders,payment,logout;
	
	private ToggleGroup sort;
	
	private final HashMap<RadioButton,Comparator<Product>> sortProd;
	
	protected Comparator<Product> comp;
	protected String search;
	
	private ArrayList<DepartmentGui> selD;
	protected TreeSet<String> feat;
	private ArrayList<RadioButton> bs = new ArrayList<>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	private Account t;
	private Cart c;

	
	public ShopStageGui(Account t) {
		
		this.t = t;
		
		CartStageGui cartgui = ((CartStage)Main.cartstage).getCartGui();
		c = cartgui.getCart();
		
		search = "";
		
		feat = new TreeSet<>();
		selD = new ArrayList<>();
		bs = new ArrayList<>();
		
		ArrayList<Thread> threads = new ArrayList<>(Main.department.size());
		Main.department.forEach(d->{
			Thread thread = new Thread(() -> Main.depmap.put(d, new DepartmentGui(d)));
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
			mainVB.getChildren().add(Main.depmap.get(d));
				RadioButton depRB= new RadioButton(d.getName());
				bs.add(depRB);
				depRB.setOnMouseClicked(e->{
					selD.clear();
					bs.forEach(b->b.setSelected(false));
					depRB.setSelected(true);
					selD.add(Main.depmap.get(d));
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
		
		//Purchases.setOnAction(e -> showPurchases());
		CartL.setOnMouseClicked(e -> cart(cartgui));
		profile.setOnAction(e -> showAcc(this));
		FDC.setOnAction(e -> FC(this));
		Orders.setOnAction(e -> showOrders(this));
		payment.setOnAction(e -> setPayment(this));
		logout.setOnAction(e -> logout());
		
		try {
			for(Thread thread: threads)
					thread.join();
		}catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
			scrollP.setVvalue(0);
			scrollR.setMinHeight(0);
			sort();
	}

	public void sort() {
		mainVB.getChildren().clear();
		DepartmentsVB.getChildren().clear();
		selD.clear();	
		bs.clear();
		boolean notFound = true, Found;
		
		for(Department d: Main.department) {
			Found = Main.depmap.get(d).sort(comp, feat, search);
			if(Found) {
				mainVB.getChildren().add(Main.depmap.get(d));
				RadioButton depRB= new RadioButton(d.getName());
				bs.add(depRB);
				depRB.setMinHeight(20);
				depRB.setOnMouseClicked(e->{
					selD.clear();
					bs.forEach(b->b.setSelected(false));
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
			
			DepartmentsVB.getChildren().add(new Label("No department"));
		}
		expand();
	}
	
	
	public void cancelFunction() { 
		searchBar.setText("");
		search = "";
		
		featuresVB.getChildren().forEach(f -> ((CheckBox)f).setSelected(false));
		
		sort();
	}
	
	public void expand() {
		if(selD.size() < Main.department.size()) 
			for(DepartmentGui depG: Main.depmap.values()) {
				depG.setExpanded(false);
			}
		selD.forEach(depGui ->depGui.setExpanded(true));
	}
	
	public void rfct(Department d) {
		
		cancelFunction();
		selD.clear();
		selD.add(Main.depmap.get(d));
		expand();
	}
	
	@FXML
	public void logout() {
		Main.shopstage.close();
		Main.login();
	}
	
	public void FC(ShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		
		for(FidelityCard fc: Main.fidelitycard) {
			if(f.getAccount().toString().equals(fc.getAccount())) {
				Main.actionstage = new ActionsStage("fidelity",f);
				Main.loadingstage.hide();
				return;
			}			
				
		}
		Main.actionstage = new ActionsStage("newFidelity",f);
		Main.loadingstage.hide();
	}
	
	public void setPayment(ShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("payment",f);
		Main.loadingstage.hide();
	}
	
	public void showAcc(ShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("showaccount",f);
		Main.loadingstage.hide();
	}
	
	public void showOrders(ShopStageGui f) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("showOrders",f); 
	}


	public Account getAccount() {
		return t ;
	}
	
	//necessario per aggiornamento shopstage dopo conferma ordine
	public void checking() {
		tt();
		for(Product p : Main.product) {
			ProductGui g = new UserProdGui(p);
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

	
	
	private void cart(CartStageGui cartgui) {
		Main.shopstage.hide();
		Main.loadingstage.show();
		cartgui.refresh();
		Main.cartstage.show();
		Main.loadingstage.hide();
	}
	
	public Cart getCart() {
		return c;
	}
		
}