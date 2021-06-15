package onlinemarket.product;



import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import onlinemarket.Main;
import onlinemarket.stages.ShopStage;

public class CustomerProdGui extends ProductGui{
	
	@FXML
	private Button AddCartB;
	@FXML
	private Label nameL,brandL,quantityWL,quantityL,priceQuantityL;
	@FXML
	private ImageView ProdImg;
	
	private Product product;
	private Integer quantityToCart = 0;

	private String getBetterPath() {
		return String.format("%s/%s_%s.jpg", Main.mediapath, product.getName(), product.getBrand());
	}
	
	private String getPathImg() {
		return String.format("%s/%s.jpg", Main.mediapath, product.getName());
	}
		
	public CustomerProdGui(Product p) {
		super(new FXMLLoader(CustomerProdGui.class.getResource("productCustomer.fxml")),p);
	
		product = p;
		
		if((new File(Main.mediapath + "/" + product.getName()+ "_" + product.getBrand() + ".jpg").exists())) {
			ProdImg.setImage(new Image("file:" + getBetterPath()));
		}
		else if((new File(getPathImg()).exists())) {
			ProdImg.setImage(new Image("file:" + getPathImg()));
		}
		else 
			ProdImg.setImage(ProductGui.defaultIMG);
		
		
		nameL.setText(p.getName());
		brandL.setText(p.getBrand());
		quantityL.setText("Reserves:  " + p.getNumber().toString());
		priceQuantityL.setText(p.getPrice().toString()+ " €");
		AddCartB.setOnAction(e->{
			if(quantityToCart.equals(p.getNumber())) { 
				AddCartB.setDisable(true);
			}
			else {
			quantityToCart++;
			quantityWL.setText(" + " + quantityToCart);
			((ShopStage) Main.shopstage).shopgui.getCart().add(p);
			}
		});
	}
	public void changeCartQuantity(Integer cartStock) {
		quantityToCart = cartStock;
	}
	public Product getProduct() {
		return product;
	}
	
	
}