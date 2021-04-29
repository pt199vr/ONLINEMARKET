package onlinemarket.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


public abstract class MainGui extends VBox {
	@FXML
	protected ScrollPane scrollP;
	@FXML
	protected Region scrollR;
	@FXML
	private SplitPane splitP;
	
	@FXML
	private GridPane searchGridPane;
	@FXML
	protected TextField searchBar;
	@FXML 
	private Button searchButton, cancelButton;
	
	@FXML
	protected VBox mainVB,filterVB;
	
	@FXML
	private RadioButton BrandAscendingRB, BrandDescendingRB, AscendingPriceRB,DescendingPriceRB;
	

} 
