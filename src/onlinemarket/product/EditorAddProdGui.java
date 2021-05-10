package onlinemarket.product;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class EditorAddProdGui {
	
	public EditorAddProdGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addProduct.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
