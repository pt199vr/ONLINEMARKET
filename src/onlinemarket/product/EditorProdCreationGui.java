package onlinemarket.product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import onlinemarket.Main;

public class EditorProdCreationGui extends AnchorPane {
	@FXML
	private Label warningL;
	@FXML
	private ImageView ProdImg;
	@FXML
	private TextField ProdNameT, ProdBrandT,PriceT,QuantityT,QuantityPPieceT;
	@FXML
	private Button addB,ImgB;
	@FXML
	private ChoiceBox FeatureChoiceB;
	
	private File SelFile;
	
	public EditorProdCreationGui(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
		ProdImg.setImage((new File(getImagePath()).exists())? new Image("file:" + getImagePath()) : ProductGui.defaultImage);
		SelFile = null;
	//da completare	
			
		}
	
	private String getImagePath() {
		return String.format("%s/%s_%s_%s.png", Main.mediapath,ProdNameT.getText(),ProdBrandT.getText(),QuantityT.getText());
	}
	
	@FXML
	private void ImgChange() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add( new ExtensionFilter("Image Files","*.png"));
		
		SelFile =fileChooser.showOpenDialog(Main.//);
		
		if(SelFile == null)
			return;
		
		ProdImg.setImage(new Image("file:" + SelFile.toPath()));
		
		try {
			Files.copy(SelFile.toPath(), new File(getImagePath()).toPath(), StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException e) {
			warningL.setText("Errore");
			return;
		}
	}
	
	private boolean check() {
		if(ProdNameT.getText().equals("") || ProdBrandT.getText().equals("") || QuantityT.getText().equals("") || QuantityPPieceT.getText().equals("") || PriceT.getText().equals("")) {
			warningL.setText("Fill all the fields");
			return true;
		}
		return false;
	}
	
	@FXML
	private void add() {
		if(check()) return;
		
		
	}
	
	
}
	
