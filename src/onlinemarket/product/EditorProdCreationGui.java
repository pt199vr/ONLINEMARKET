package onlinemarket.product;


import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import onlinemarket.Main;
import onlinemarket.departments.Department;

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
	
	private File selFile;
	
	private Department dep;
	
	public EditorProdCreationGui(Department dep) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		this.dep = dep;
		ProdImg.setImage((new File(getIMG()).exists())? new Image(getIMG()) : ProductGui.defaultIMG);
		selFile = null;
	}
	
	private boolean check() { 
		if(ProdNameT.getText().equals("") || ProdBrandT.getText().equals("") || PriceT.getText().equals("") || QuantityT.getText().equals("") || QuantityPPieceT.getText().equals("")) {
			return true;
		}
		return false;
	}
	
	private String getIMG() {
		return String.format("%s/%s_%s.png",Main.mediapath, ProdNameT.getText(),ProdBrandT.getText());
	}
	
	@FXML
	private void modifyIMG() {
		FileChooser filechooser= new FileChooser();
		filechooser.setTitle("Open Resource File");
		filechooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png"));
		
		selFile = filechooser.showOpenDialog(Main.firststage);
		
		if(selFile == null)
			return;
		
		ProdImg.setImage(new Image("file" + selFile.toPath()));
	}
	
	@FXML
	private void createProd() {
		
		if(check()) {
			Alert a = new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		Double price;
		try {
			price = Double.parseDouble(PriceT.getText());
		}catch(NumberFormatException e) {
			Alert a = new Alert(Alert.AlertType.NONE,"Please insert a valid price",ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		Double quantity;
		try {
			quantity = Double.parseDouble(QuantityT.getText());
		}catch(NumberFormatException e) {
			Alert a = new Alert(Alert.AlertType.NONE,"Please insert a valid quantity",ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		TreeSet<String> feat = new TreeSet<>();
		for(MenuItem mi: featuresMB.getItems()) {
			RadioMenuItem featRMI = (RadioMenuItem) mi;
			if(featRMI.isSelected())
				feat.add(featRMI.getText());
		}
	
		
		Product product = new Product(ProdNameT.getText(), ProdBrandT.getText(), price, quantity, null, feat);
		
		addB.setDisable(true);
		wL.setText("Product added");
		
		
		product.setGui();
		
		Main.product.write();
		
		
	}
}
	
