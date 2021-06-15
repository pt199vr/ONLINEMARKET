package onlinemarket.order;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import onlinemarket.product.Product;


public class ProductTableGui extends AnchorPane{
	
	@FXML
	private Label MenuL,OrdersL;
	
	@FXML
	private TableView<Product> prodTB;
	@FXML
	private TableColumn<Product,String> NameCol, BrandCol;
	@FXML
	private TableColumn<Product,Double> SingleCol,TotalCol;
	@FXML
	private TableColumn<Product,Integer> QuantityCol;
	

	public ProductTableGui() {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("prodTable.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		MenuL.setOnMouseClicked(e ->{
			
		});
		
		OrdersL.setOnMouseClicked(e->{
			
		});
		/*
		NameProdCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		BrandProdCol.setCellValueFactory(new PropertyValueFactory<>("Brand"));
		SingleCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
		//TotalCol
		//QuantityInCartCol
		
		ObservableList<Product> data= FXCollections.observableArrayList();
		//cico per selezionare i prodotti for(Product product : ) {}
		
		prods.setItems(data);*/
	}

}
