package onlinemarket.product;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class ProductGui extends GridPane {
	@FXML
	protected ImageView ProdImg;
	
	protected Product product;
	
	protected Integer number;

	public final static Image defaultIMG = new Image(ProductGui.class.getResourceAsStream("defaultIMG.png"));
	
	public ProductGui(FXMLLoader fxmlLoader, Product product, Integer number) {
		
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		this.product = product;
		this.number = number;
		
		if(this.product.getPath() == null)
			ProdImg.setImage(defaultIMG);
		else
			ProdImg.setImage(new Image("file:" + this.product.getPath()));
		
		if(number == 0)
			EmptyShelf();
	}
	

	private void EmptyShelf(){
		Alert a= new Alert(Alert.AlertType.NONE,"This product is out of stock right now",ButtonType.OK);
		a.showAndWait();
		return;
		
	}
	
	public void newQuantity(Integer number) {
		this.number = number;
		if(number == 0) {
			EmptyShelf();
		}
	}
}
	
	
