package onlinemarket.product;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class UserProdGui extends ProductGui{
	
	@FXML
	private Button AddCartB;
	@FXML
	private Label nameL,brandL,quantityWL,quantityL,priceQuantityL;
	@FXML
	private ImageView ProdImg;
	
	private String code;
	
	private Product product;
	

	public UserProdGui(Product p) {
		super(new FXMLLoader(UserProdGui.class.getResource("productUser.fxml")),p);
	
		product = p;
		
		code = p.toString();
		
		
		nameL.setText(p.getName());
		brandL.setText(p.getBrand());
		quantityL.setText("Reserves:  " + p.getQuantity().toString());
		priceQuantityL.setText(p.getPrice().toString()+ " €");
	
		if(p.getNumber() == 0)
			AddCartB.setDisable(true);
	}
	
	public Button getAdd() {
		return AddCartB;
	}
	
	public Product getProduct() {
		return product;
	}
	
}