package onlinemarket.actionsgui;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import onlinemarket.Main;
import onlinemarket.account.Role;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;
import onlinemarket.product.TypeofQuantity;

public class EditorProdModifyGui extends ProductGui {
	@FXML
	private TextField NameT,BrandT,priceT,QuantityXPieceT,StockT;
	@FXML
	private ChoiceBox<String> TypeCB;
	@FXML
	private Label quantityWL;
	@FXML
	private Button modifyB,newIMGB;
	@FXML
	private ImageView ProdImg;
	
	private File selFile;
	
	public EditorProdModifyGui(Product p) {
		
		super(new FXMLLoader(EditorProdModifyGui.class.getResource("productModify.fxml")),p);
		
		NameT.setText(p.getName());
		BrandT.setText(p.getBrand());
		priceT.setText(p.getPrice().toString());
		StockT.setText(p.getNumber().toString());
		QuantityXPieceT.setText(p.getQuantity().toString());
		modifyB.setOnAction(e -> mod(p));
		
		newIMGB.setOnAction(e->modifyIMG());
		
		String s[]= {TypeofQuantity.GRAMS.toString(),TypeofQuantity.LITERS.toString(),TypeofQuantity.PIECES.toString()};
		TypeCB.getItems().addAll(s);
		TypeCB.getSelectionModel().select(p.getType().toString());
		
	}

	private void mod(Product p) {
		
		Boolean check = true;
		
		Integer newquantityXpiece = 0,number = 0;
		Double price = 0.0;
		
		try {
			number = Integer.parseInt(StockT.getText()); 
			newquantityXpiece = Integer.parseInt(QuantityXPieceT.getText());
			price = Double.parseDouble(priceT.getText());
			
			if(number < 0 || price <= 0.0 ||newquantityXpiece < 0)
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
		if(Integer.parseInt(StockT.getText())== 0) {
			quantityWL.setText("Out of Stock");
			quantityWL.setVisible(true);
			ProdImg.setOpacity(0.1);
		}
		if(Integer.parseInt(StockT.getText()) > 0) {
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

		Product newProd = new Product(name, brand, price,newquantityXpiece, number, type, p.getDepartment(), p.getFeatures());
		ProductGui g = this;
		
		if(newProd.equals(p)) {
			newQuantity(number);
			g = this;
		}
		else {
			Main.product.remove(p);
			Main.product.add(newProd);
			g = new EditorProdModifyGui(newProd);
		}
		
	}
	
	@FXML
	private void modifyIMG() {
		
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Choose Product Image");
		filechooser.setInitialDirectory(new File(Main.mediapath));
		filechooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg"));
		
		selFile = filechooser.showOpenDialog(Main.firststage);
		
		if(selFile != null)
			ProdImg.setImage(new Image("file:" + selFile.getPath()));
	}
	
}


