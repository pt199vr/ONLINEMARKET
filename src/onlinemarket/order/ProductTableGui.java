package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import onlinemarket.product.Product;

public class ProductTableGui extends TableView<Product>{

	public ProductTableGui() {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("prodTable.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
