package onlinemarket.product;

import java.util.TreeSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserProdGui extends ProductGui{
	
	protected TreeSet<Product> cartProd;
	@FXML
	private Button AddCartB;
	@FXML
	private Label nameL,brandL,quantityWL,quantityL,priceQuantityL;
	

	public UserProdGui(Product p, Integer quantity) {
		super(new FXMLLoader(UserProdGui.class.getResource("productUser.fxml")), p, quantity);
		
		nameL.setText(p.getName());
		brandL.setText(p.getBrand());
		quantityL.setText("Reserves:  " + p.getQuantity().toString());
		priceQuantityL.setText(p.getPrice().toString()+ " €");
		
		AddCartB.setOnAction(e -> InCart(p));
		
		if(quantity == 0)
			AddCartB.setDisable(true);
	}
	
	@FXML
	private void InCart(Product p) {
	}
}