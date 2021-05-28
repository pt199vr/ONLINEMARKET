package onlinemarket.order;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.account.Email;
import onlinemarket.datentime.Date;
import onlinemarket.fidelitycard.FidelityCard;
import onlinemarket.payment.Payment;



public class OrderTableGui extends TableView<Order>{
	
	private Order selOrder;
	@FXML
	private TableView<Order> ordersTable;
	
	@FXML
	private TableColumn<Order,String> OrderID,OrderStatus;
	@FXML
	private TableColumn<Account,Email> OrderMail;
	@FXML
	private TableColumn<FidelityCard,Integer> OrderPoints;
	@FXML
	private TableColumn<Order,Payment> OrderPayment;
	@FXML
	private TableColumn<Order,Date> OrderDate;
	@FXML
	private TableColumn<Order,Double> OrderPrice;

	
	
	public OrderTableGui() {
		
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("OrdersTable.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		/*Main.order.read();
		
		OrderID.setCellValueFactory(new PropertyValueFactory<>("Id"));
		OrderStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
		OrderMail.setCellValueFactory(new PropertyValueFactory<>("Email"));
		OrderPoints.setCellValueFactory(new PropertyValueFactory<>("Points"));
		OrderPayment.setCellValueFactory(new PropertyValueFactory<>("Type"));
		OrderDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		OrderPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
		ObservableList<Order> data= FXCollections.observableArrayList();
		
		for(Order o: Main.order) {
			data.add(o);
		}
		
		ordersTable.setItems(data);
		
		if(selOrder!=null)
			ordersTable.getSelectionModel().select(selOrder);*/
	}

}
