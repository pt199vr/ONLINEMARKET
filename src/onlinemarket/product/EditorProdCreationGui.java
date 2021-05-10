package onlinemarket.product;


import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.shop.Shop;

// DA COMPLETARE!!!!

public class EditorProdCreationGui extends AnchorPane {
	@FXML
	private Label wL;
	@FXML
	private ImageView ProdImg;
	@FXML
	private TextField ProdNameT, ProdBrandT,PriceT,QuantityT,QuantityPPieceT;
	@FXML
	private Button addB,ImgB;
	@FXML
	private MenuButton featuresMB;
	
	public EditorProdCreationGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean check() { 
		if(ProdNameT.getText().equals("") || ProdBrandT.getText().equals("") || PriceT.getText().equals("") || QuantityT.getText().equals("") || QuantityPPieceT.getText().equals("")) {
			Alert a = new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			a.showAndWait();
			return true;
		}
		return false;
	}
}
	
