package onlinemarket.product;

import java.io.IOException;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import onlinemarket.Main;
import onlinemarket.actionsgui.EditorProdModifyGui;
import onlinemarket.stages.EditorShopStage;
import onlinemarket.stages.ShopStage;


public class ProductGui extends GridPane {
	
	protected Product product;
	
	protected Integer number;

	public final static Image defaultIMG = new Image(ProductGui.class.getResourceAsStream("defaultIMG.png"));
	
	public ProductGui(FXMLLoader fxmlLoader,Product product) {
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		this.product = product;

		if(product.getNumber() == 0)
			EmptyShelf();
	}

	private void EmptyShelf(){
		
	}
	
	protected void newQuantity(Integer number) {
		this.number = number;
		if(number == 0)
			EmptyShelf();
	}
	

	public static Image getImage() {
		return defaultIMG;
	}
}
	
	
