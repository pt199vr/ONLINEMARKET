package onlinemarket.actionsgui;


import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import onlinemarket.Main;
import onlinemarket.departments.Department;
import onlinemarket.product.Product;
import onlinemarket.product.ProductGui;
import onlinemarket.product.TypeofQuantity;
import onlinemarket.readnwrite.RnW_Product;
import onlinemarket.stages.EditorShopStageGui;

public class EditorProdCreationGui extends AnchorPane {
	@FXML
	private VBox featVB;
	@FXML
	private ImageView ProdImg;
	@FXML
	private TextField ProdNameT, ProdBrandT,PriceT,QuantityXPieceT,StockT,TypeofQuantityT;
	@FXML
	private Button addB,ImgB;
	@FXML
	private MenuButton featuresMB;
	@FXML
	private ChoiceBox<Department> DepCB;
	@FXML
	private ChoiceBox<String> TypeCB;
	
	private File selFile = null;
	
	public EditorProdCreationGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		ProdImg.setImage((new File(getIMG()).exists())? new Image(getIMG()) : ProductGui.getImage());
		
		ImgB.setOnAction(e -> modifyIMG());
		
		for(Department d: Main.department)
			DepCB.getItems().add(d);
		
		for(String feat: RnW_Product.features) {
			RadioMenuItem I = new RadioMenuItem(feat);
			I.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent e) {
					showFeatures();
					
				}});
			featuresMB.getItems().add(I);
		}
		String types[] = {TypeofQuantity.GRAMS.toString(),TypeofQuantity.LITERS.toString(),TypeofQuantity.PIECES.toString()};
		for(String s: types) {
			TypeCB.getItems().add(s);
		}
		addB.setOnAction(e -> addProd(f));
	}
	
	private void addProd(EditorShopStageGui f) {
		if(ProdNameT.getText().equals("") || ProdBrandT.getText().equals("")|| StockT.getText().equals("") || QuantityXPieceT.getText().equals("")||PriceT.getText().equals("")) {
			Alert a = new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		Double price;
		try {
			price = Double.parseDouble(PriceT.getText());
		}catch(NumberFormatException e) {
			Alert b= new Alert(Alert.AlertType.NONE,"Invalid price",ButtonType.OK);
			b.showAndWait();
			return;
		}
		
		Integer quantity = 0, number = 0;
		try {
			number = Integer.parseInt(StockT.getText());
			quantity = Integer.parseInt(QuantityXPieceT.getText());
		}catch(NumberFormatException e) {
			Alert c = new Alert(Alert.AlertType.NONE,"Invalid quantity or number of products",ButtonType.OK);
			c.showAndWait();
			return;
		}
		TreeSet<String> features = new TreeSet<>();
		for(MenuItem mi :featuresMB.getItems()) {
			RadioMenuItem rmi = (RadioMenuItem) mi;
			if(rmi.isSelected()) {
				features.add(rmi.getText());
			}
		}
		
		String t = TypeCB.getSelectionModel().getSelectedItem();
		TypeofQuantity type;
		
		if(t.equals(TypeofQuantity.GRAMS.toString()))
			type = TypeofQuantity.GRAMS;
		else if(t.equals(TypeofQuantity.LITERS.toString()))
			type = TypeofQuantity.LITERS;
		else
			type = TypeofQuantity.PIECES;
		
		Product p = new Product(ProdNameT.getText(), ProdBrandT.getText(), price, quantity, number, type, DepCB.getSelectionModel().getSelectedItem(), features);
		if(selFile!=null) p.setPath(selFile.getPath());
		ProductGui gui = new EditorProdModifyGui(p); 
		Main.product.add(p);
		Main.prodmap.put(p, gui);
		
		Main.product.write();
		
		Main.loadingstage.show();
		f.checking();
		Main.actionstage.hide();
		Main.shopstage.show(); 
		Main.loadingstage.hide();
	}

	private String getIMG() {
		return String.format("%s/%s.jpg",Main.mediapath, ProdNameT.getText());
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
	
	@FXML
	private void showFeatures() {
		String selFeat=null;
		featVB.getChildren().clear();
		for(MenuItem mi:featuresMB.getItems()) {
			RadioMenuItem frmi=(RadioMenuItem) mi;
			
			if(frmi.isSelected()) {
				selFeat = frmi.getText();
				featVB.getChildren().add(new Label(selFeat));
			}
		}
	
	}

}