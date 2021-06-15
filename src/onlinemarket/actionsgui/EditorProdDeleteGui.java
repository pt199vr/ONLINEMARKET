package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.product.Product;
import onlinemarket.stages.EditorShopStageGui;

public class EditorProdDeleteGui extends AnchorPane {
	@FXML
	private ChoiceBox<Product> prodCB;
	@FXML
	private Button deleteB;
	public EditorProdDeleteGui(EditorShopStageGui f) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deleteProduct.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		
		for(Product p : Main.product) {
			prodCB.getItems().add(p);
		}
		
		deleteB.setOnAction(e -> delete(f));
		
	}
	private void delete(EditorShopStageGui f) {
		Product p = prodCB.getSelectionModel().getSelectedItem();
		if (p==null) {
			Alert b= new Alert(Alert.AlertType.NONE,"You have to choose a product first!",ButtonType.OK);
			b.showAndWait();
			return;
		}
		if(new Alert(Alert.AlertType.NONE, "You are deleting this product\n\nContinue?",ButtonType.YES,ButtonType.NO).showAndWait().orElse(ButtonType.NO) == ButtonType.NO)
			return;
		Main.product.remove(p);
		Main.prodmap.remove(p);
		
		Main.product.write();
		f.checking();
		Main.actionstage.hide();
		Main.shopstage.show();
	}

}
