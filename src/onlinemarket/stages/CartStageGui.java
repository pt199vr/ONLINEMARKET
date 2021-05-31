package onlinemarket.stages;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.cart.Cart;
import onlinemarket.product.CustomerProdCartGui;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;



public class CartStageGui extends VBox{
	
	
	@FXML
	private Button BuyB;
	@FXML
	private VBox CartProdVB;
	
	@FXML 
	private RadioButton AscendingBrandRB,DescendingBrandRB, AscendingPriceRB,DescendingPriceRB;
	private ToggleGroup sort;
	
	@FXML
	private Label FinalPriceL,PurchasesL;
	@FXML
	private Menu Purchases;
	@FXML
	private GridPane depGrid,featuresGrid;
	@FXML
	private MenuItem profile,FDC,Orders,payment,logout;

	
	private Account t;
	private Cart cart;

	
	public CartStageGui(Account t) {
		
		this.t = t;
		
		cart = new Cart();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cart.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		PurchasesL.setOnMouseClicked(e ->{
			Main.loadingstage.show();
			Main.cartstage.hide();
			Main.shopstage.show();
			Main.loadingstage.hide();
		});
		
		for(Product product: Main.product) {
			if(cart.getProducts().containsKey(product)){
				Product x = product;
				ProductGui tmp = new CustomerProdCartGui(x);
				CartProdVB.getChildren().add(tmp);
				}
		}
		
		FinalPriceL.setText(cart.getPrice().toString());
		
		sort = new ToggleGroup();
		AscendingBrandRB.setToggleGroup(sort);
		DescendingBrandRB.setToggleGroup(sort);
		AscendingPriceRB.setToggleGroup(sort);
		DescendingPriceRB.setToggleGroup(sort);
		AscendingBrandRB.setSelected(true);
		
		
		BuyB.setOnAction(e -> GoBuy(this));
		
		logout.setOnAction(e -> logout());
		
	}
	
	public Cart getCart() {
		return cart;
	}

	private void GoBuy(CartStageGui f) {
		if(cart.getProducts().isEmpty()|| cart.getProducts() == null) {
			Alert a = new Alert(Alert.AlertType.NONE, "Your Cart is Empty",ButtonType.OK);
			a.showAndWait();
			return;
		}
		Main.loadingstage.show();
		Main.cartstage.hide();
		Main.actionstage = new ActionsStage("orderRecap",f);
		Main.loadingstage.hide();
	}

	@FXML
	public void logout() {
		Main.cartstage.close();
		Main.login();
	}
	
	
	public Account getAccount() {
		return t ;
	}
	
	public void refresh() {
		CartProdVB.getChildren().clear();

		for(Product prod: Main.product) {
			if(cart.getProducts().containsKey(prod)) {
				Product tmp = prod;
				ProductGui tmpGui = new CustomerProdCartGui(tmp);
				CartProdVB.getChildren().add(tmpGui);
				
			}
		}
		FinalPriceL.setText(cart.getPrice().toString());
	}
}
