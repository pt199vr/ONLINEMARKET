package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.order.*;
import onlinemarket.product.Product;



public class ShowProducts extends AnchorPane{
	
	@FXML
	private Label MenuL;
	
	@FXML
	private TableView<Product> prodTB;
	@FXML
	private TableColumn<Product,String> NameCol, BrandCol;
	@FXML
	private TableColumn<Product,Double> SingleCol;
	@FXML
	private TableColumn<Order,Double> TotalCol;
	@FXML
	private TableColumn<Product,Integer> QuantityCol;

	private Order order;
	
	
	public ShowProducts(Order order) {
		this.order = order;
		
		FXMLLoader fxmlLoader= new FXMLLoader(Order.class.getResource("prodTable.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		NameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		BrandCol.setCellValueFactory(new PropertyValueFactory<>("Brand"));
		SingleCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
		TotalCol.setCellValueFactory(new PropertyValueFactory<Order,Double>("Price"));
		QuantityCol.setCellValueFactory(new PropertyValueFactory<>("Number"));
		
		
		ObservableList<Product> data= FXCollections.observableArrayList();
		order.getProducts().forEach(p ->data.add(p));
		prodTB.setItems(data);
		
		MenuL.setOnMouseClicked(e ->{
			Main.loadingstage.show();
			Main.actionstage.hide();
			Main.shopstage.show();
			Main.loadingstage.hide();
		});
		
		
		
		
	}
	
}
