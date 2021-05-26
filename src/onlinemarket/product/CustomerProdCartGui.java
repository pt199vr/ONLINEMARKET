package onlinemarket.product;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CustomerProdCartGui extends ProductGui{
	
	@FXML
	private ImageView prodIMG;
	@FXML
	private Label NameL,BrandL,quantityL,NumberL,singlePriceL,totalPriceL;
	@FXML
	private Button removeB;

<<<<<<< HEAD
	public CustomerProdCartGui(Product product, Integer number) {
		super(new FXMLLoader(CustomerProdCartGui.class.getResource("prodCartView.fxml")), product, number);
=======
	public CustomerProdCartGui( Product product) {
		super(new FXMLLoader(CustomerProdCartGui.class.getResource("prodCartView.fxml")), product);
		prodIMG.setImage(defaultIMG);
>>>>>>> branch 'main' of https://github.com/pt199vr/ONLINEMARKET
		
		NameL.setText(product.getName());
		BrandL.setText(product.getBrand());
		quantityL.setText(product.getQuantity().toString());
		NumberL.setText(product.getNumber().toString());
		singlePriceL.setText(product.getPrice().toString());
		
		//totalPriceL.setText((product.getPrice()*product.getNumber()));
		
		
		removeB.setOnAction(e -> {
			
		});
		
		
	}

}