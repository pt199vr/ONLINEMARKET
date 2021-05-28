package onlinemarket.order;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import onlinemarket.product.Product;


public class ProductTableGui extends TableView<Product>{
	
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product,String> NameProdCol, BrandProdCol;
	@FXML
	private TableColumn<Product,Double> SingleCol,TotalCol;
	@FXML
	private TableColumn<Product,Integer> QuantityInCartCol;
	

	public ProductTableGui() {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("prodTable.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
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
