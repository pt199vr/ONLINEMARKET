package onlinemarket.product;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import onlinemarket.Main;


public class ProductGui extends GridPane {
	@FXML
	protected ImageView ProdImg;
	
	protected Product product;
	
	protected Double quantity;

	public final static Image defaultIMG = new Image(ProductGui.class.getResourceAsStream("defaultIMG.png"));
	
	public ProductGui(FXMLLoader fxmlLoader, Product product, Double quantity) {
		
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		this.product = product;
		this.quantity = quantity;
		
		ProdImg.setImage((new File(getIMG()).exists())? new Image("file: "+ getIMG()): defaultIMG);
		
		if(quantity == 0)
			EmptyShelf();
	}
	
	private void EmptyShelf(){
		Alert a= new Alert(Alert.AlertType.NONE,"This product is out of stock right now",ButtonType.OK);
		a.showAndWait();
		return;
		
	}
	
	private String getIMG() {
		return String.format("%s/%s_%s.png",Main.mediapath,product.getName(),product.getBrand());
	}
	public void newQuantity(Double quantity) {
		this.quantity = quantity;
		if(quantity == 0) {
			EmptyShelf();
		}
	}
}
	
	
	
