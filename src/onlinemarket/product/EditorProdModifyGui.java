package onlinemarket.product;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class EditorProdModifyGui {
	
	public EditorProdModifyGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productModify.fxml")); 
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

}
