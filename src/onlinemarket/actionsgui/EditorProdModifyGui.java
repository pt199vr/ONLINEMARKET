package onlinemarket.actionsgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import onlinemarket.Main;
import onlinemarket.account.Role;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;
import onlinemarket.product.TypeofQuantity;

public class EditorProdModifyGui extends ProductGui {
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
	
	public EditorProdModifyGui(Product p,Double quantity) {
		
		super(new FXMLLoader(EditorProdModifyGui.class.getResource("productModify.fxml")), p, quantity);

		NameT.setText(p.getName());
		BrandT.setText(p.getBrand());
		priceT.setText(p.getPrice().toString());
		QuantityT.setText(quantity.toString());
		modifyB.setOnAction(e -> mod(p));
		
		String s[]= {TypeofQuantity.GRAMS.toString(),TypeofQuantity.LITERS.toString(),TypeofQuantity.PIECES.toString()};
		TypeCB.getItems().addAll(s);
		TypeCB.getSelectionModel().select(p.getType().toString());
		
	}

	private void mod(Product p) {
		
		Boolean check = true;
		
		Double newquantity = 0.0, price = 0.0;
		
		try {
			newquantity = Double.parseDouble(QuantityT.getText());
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
		
		Product newProd = new Product(name, brand, price,newquantity, type, p.getDepartment(), p.getFeatures());
		EditorProdModifyGui gui;
		if(newProd.equals(p)) {
			newQuantity(newquantity);
			gui = this;
		}
		else {
			Main.product.remove(p);
			Main.product.add(newProd);
			
			newProd.setGui();
			gui = (EditorProdModifyGui)newProd.getGui();
		}
		
	}
}


