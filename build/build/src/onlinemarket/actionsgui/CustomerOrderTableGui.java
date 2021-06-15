package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import java.util.TreeSet;
import javafx.stage.Stage;

import onlinemarket.Main;
import onlinemarket.account.*;
import onlinemarket.order.*;
import onlinemarket.stages.*;
import onlinemarket.datentime.*;
import onlinemarket.payment.*;

public class CustomerOrderTableGui extends AnchorPane{
	
	@FXML
	private TableView<Order> ordersTable;
	@FXML
	private Label showProds;
	@FXML
	private TableColumn<Order, String> OrderID;
	@FXML
	private TableColumn<Order, Date> OrderDate;
	@FXML
	private TableColumn<Order, String> Time;
	@FXML
	private TableColumn<Order, OrderStatus> OrderStatus;
	@FXML
	private TableColumn<Order, Payment> OrderPayment;
	@FXML
	private TableColumn<Order, Integer> OrderPoints;
	@FXML
	private TableColumn<Order, String> OrderPrice;
	

	public CustomerOrderTableGui(ShopStageGui shop) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomerOrders.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Main.order.read();
		
		OrderID.setCellValueFactory(new PropertyValueFactory<>("Id"));
		OrderDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("Date"));
		Time.setCellValueFactory(new PropertyValueFactory<Order, String>("TimeInterval"));
		OrderStatus.setCellValueFactory(new PropertyValueFactory<Order, OrderStatus>("Status"));
		OrderPayment.setCellValueFactory(new PropertyValueFactory<Order, Payment>("Payment"));
		OrderPoints.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Points"));
		OrderPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
		ObservableList<Order> data = FXCollections.observableArrayList();
		for(Order tmp : Main.order) {
			if(shop.getAccount().toString().equals(tmp.getAccount().toString())) {
				data.add(tmp);
			}
		}
		ordersTable.setItems(data);
		
		new Thread(() -> {
			showProds.setOnMouseClicked(e -> showProducts());
		}).start();
		
	}
	
	private void showProducts() {
		if(!ordersTable.getSelectionModel().isEmpty()) {
			Order tmp = ordersTable.getSelectionModel().getSelectedItem();
			Stage showing = new ActionsStage(tmp);
		}
	}
}
