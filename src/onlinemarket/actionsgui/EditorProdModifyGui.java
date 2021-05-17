package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import onlinemarket.Main;
import onlinemarket.product.Product;
import onlinemarket.product.TypeofQuantity;
import onlinemarket.stages.EditorShopStageGui;

public class EditorProdModifyGui {
	@FXML
	private TextField NameT,BrandT,priceT,QuantityT,quantityPerPieceT;
	@FXML
	private ChoiceBox<String> TypeCB;
	@FXML
	private Label quantityWL;
	@FXML
	private Button modifyB,newIMGB;
	@FXML
	private ImageView ProdImg;
	
	public EditorProdModifyGui(EditorShopStageGui f,Product p) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productModify.fxml")); 
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		NameT.setText(p.getName());
		BrandT.setText(p.getBrand());
		priceT.setText(p.getPrice().toString());
		QuantityT.setText(p.getQuantity().toString());
		
		modifyB.setOnAction(e -> mod(f,p));
		
	}

	private void mod(EditorShopStageGui f,Product p) {
		
		Boolean check = true;
		
		Double quantity = 0.0, price = 0.0;
		
		try {
			quantity = Double.parseDouble(QuantityT.getText());
			price = Double.parseDouble(priceT.getText());
			
			if(quantity < 0.0 || price <= 0.0)
				throw new NumberFormatException();
		}catch(NumberFormatException e) {
			check = false;
		}
		
		String name= NameT.getText(), brand= BrandT.getText();
		
		if(!check || name.equals("") || brand.equals("")) {
			Alert b = new Alert(Alert.AlertType.NONE,"Error",ButtonType.OK);
			b.showAndWait();
			return;
		}
		if(Double.parseDouble(QuantityT.getText())== 0.0) {
			quantityWL.setText("Out of Stock");
			quantityWL.setVisible(true);
			ProdImg.setOpacity(0.1);
		}
		if(Double.parseDouble(QuantityT.getText()) > 0.0) {
			quantityWL.setText("");
			quantityWL.setVisible(false);
			ProdImg.setOpacity(1);
		}
		String t = TypeCB.getSelectionModel().getSelectedItem();
		TypeofQuantity type;
		if(t.equals(TypeofQuantity.GRAMS.toString()))
			type = TypeofQuantity.GRAMS;
		else if(t.equals(TypeofQuantity.LITERS.toString()))
			type = TypeofQuantity.LITERS;
		else
			type = TypeofQuantity.PIECES;
		
		Product newProd = new Product(name, brand, price, quantity, type,p.getFeatures());
		EditorProdModifyGui gui = this;
		if(newProd.equals(p)) {
		}
		else {
			Main.product.remove(p);
			Main.product.add(newProd);
			
			newProd.setGui();
			gui = new EditorProdModifyGui(f, newProd);
		}
		
	}
}


