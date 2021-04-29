package onlinemarket.product;


import java.io.File;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
	private Label warningL;
	@FXML
	private ImageView ProdImg;
	@FXML
	private TextField ProdNameT, ProdBrandT,PriceT,QuantityT,QuantityPPieceT;
	@FXML
	private Button addB,ImgB;
	@FXML
	private MenuButton featuresMB;
	
	private File SelFile;
}
	
