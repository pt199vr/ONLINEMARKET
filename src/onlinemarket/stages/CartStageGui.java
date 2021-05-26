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
import onlinemarket.fidelitycard.FidelityCard;
import onlinemarket.product.CustomerProdCartGui;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;

public class CartStageGui extends VBox{
	
	
	@FXML
	private Button BuyB;
	@FXML
	private VBox filterVB,CartProdVB;
	@FXML 
	private RadioButton AscendingBrandRB,DescendingBrandRB, AscendingPriceRB,DescendingPriceRB;
	@FXML
	private Label FinalPriceL,PurchasesL;
	@FXML
	private Menu Purchases;
	@FXML
	private GridPane depGrid,featuresGrid;
	@FXML
	private MenuItem profile,FDC,Orders,payment,logout;
	
	private ToggleGroup sort;
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
		
		sort = new ToggleGroup();
		AscendingBrandRB.setToggleGroup(sort);
		DescendingBrandRB.setToggleGroup(sort);
		AscendingPriceRB.setToggleGroup(sort);
		DescendingPriceRB.setToggleGroup(sort);
		AscendingBrandRB.setSelected(true);
		
		
		BuyB.setOnAction(e -> GoBuy(this));
		
		//profile.setOnAction(e -> showAcc(f));
		//FDC.setOnAction(e -> FC(f));
		//Orders.setOnAction(e -> showOrders());
		//payment.setOnAction(e -> setPayment(f));
		logout.setOnAction(e -> logout());
		
	}
	
	public Cart getCart() {
		return cart;
	}

	private void GoBuy(CartStageGui f) {
		if(CartProdVB.getChildren().isEmpty()) {
			Alert a =  new Alert(Alert.AlertType.NONE,"You got nothing in your cart!\nPlease add some products first than continue.",ButtonType.OK);
			a.showAndWait();
			return ;
		}
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("orderRecap",f);
		Main.loadingstage.hide();
	}

	@FXML
	public void logout() {
		Main.cartstage.close();
		Main.login();
	}
	
	/*public void FC(ShopStageGui f) {
		Main.cartstage.hide();
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
		Main.cartstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("payment",f);
		Main.loadingstage.hide();
	}
	
	public void showAcc(ShopStageGui f) {
		Main.cartstage.hide();
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("showaccount",f);
		Main.loadingstage.hide();
	}
	*/
	
	public Account getAccount() {
		return t ;
	}
	
	public void refresh() {
		CartProdVB.getChildren().clear();
<<<<<<< HEAD
=======
		for(Product prod: Main.product) {
			if(cart.getProducts().containsKey(prod)) {
				Product tmp = prod;
				ProductGui tmpGui = new CustomerProdCartGui(tmp);
				CartProdVB.getChildren().add(tmpGui);
			}
		}
		
>>>>>>> branch 'main' of https://github.com/pt199vr/ONLINEMARKET
		FinalPriceL.setText(cart.getPrice().toString());
	}

	
	
	
	
	
	
	

}
