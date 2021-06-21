package onlinemarket.product;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

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
	}

	
	protected void newQuantity(Integer number) {
		this.number = number;
	}
	

	public static Image getImage() {
		return defaultIMG;
	}
}
	
	
