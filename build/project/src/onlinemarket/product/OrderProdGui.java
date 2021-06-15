package onlinemarket.product;

import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import onlinemarket.Main;
import onlinemarket.stages.CartStage;


public class OrderProdGui extends ProductGui{
	
	@FXML
	private ImageView prodImg;
	@FXML
	private Label NameL,BrandL,priceL,NumberL,TotalPriceL;

	private String getBetterPath() {
		return String.format("%s/%s_%s.jpg", Main.mediapath, product.getName(), product.getBrand());
	}
	
	private String getPathImg() {
		return String.format("%s/%s.jpg", Main.mediapath, product.getName());
	}
	
	public OrderProdGui(Product product) {
		super(new FXMLLoader(OrderProdGui.class.getResource("orderProdGui.fxml")), product);
		
		NameL.setText(product.getName());
		BrandL.setText(product.getBrand());
		priceL.setText(product.getPrice().toString() +" €");
		
		NumberL.setText("Orderd quantity: " + product.getNumber());
		
		Double totl = product.getNumber() * product.getPrice();
		TotalPriceL.setText(totl.toString() + "€");
		
		if((new File(Main.mediapath + "/" + product.getName()+ "_" + product.getBrand() + ".jpg").exists())) {
			prodImg.setImage(new Image("file:" + getBetterPath()));
		}
		else if((new File(getPathImg()).exists())) {
			prodImg.setImage(new Image("file:" + getPathImg()));
		}
		else 
			prodImg.setImage(ProductGui.defaultIMG);
	}

}
