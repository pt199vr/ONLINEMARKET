package onlinemarket.actionsgui;

import java.io.IOException;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import onlinemarket.Main;
import onlinemarket.datentime.Date;
import onlinemarket.order.Order;
import onlinemarket.order.OrderStatus;
import onlinemarket.payment.*;
import onlinemarket.stages.ActionsStage;
import onlinemarket.stages.EditorShopStageGui;

public class EditorOrderTable extends AnchorPane{
	
	@FXML
	private Label showProds;
	@FXML
	private MenuItem setDelivering,setDelivered;
	@FXML
	private TableView<Order> ordersTable;
	@FXML
	private TableColumn<Order, String> OrderID;
	@FXML
	private TableColumn<Order, Date> OrderDate;
	@FXML
	private TableColumn<Order, String> Time;
	@FXML
	private TableColumn<Order, OrderStatus> Status;
	@FXML
	private TableColumn<Order, Payment> OrderPayment;
	@FXML
	private TableColumn<Order, Integer> OrderPoints;
	@FXML
	private TableColumn<Order, String> OrderPrice;
	private ObservableList<Order> data = FXCollections.observableArrayList();
	
	public EditorOrderTable(EditorShopStageGui shop) {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("EditorOrders.fxml"));
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
		Status.setCellValueFactory(new PropertyValueFactory<Order, OrderStatus>("Status"));
		OrderPayment.setCellValueFactory(new PropertyValueFactory<Order, Payment>("Payment"));
		OrderPoints.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Points"));
		OrderPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
		
		for(Order tmp : Main.order) {
			data.add(tmp);
		}
		ordersTable.setItems(data);
		
		
		new Thread(() -> {
			showProds.setOnMouseClicked(e -> showProducts());
		}).start();
		
		setDelivering.setOnAction(e ->{
			changeStatus(OrderStatus.DELIVERING);
			refresh();
		});
		setDelivered.setOnAction(e->{
			changeStatus(OrderStatus.DELIVERED);
			refresh();
		});
	}
	
	public void refresh() {
		Main.actionstage.hide();
		Main.loadingstage.show();
		data.clear();
		
		for(Order tmp : Main.order) {
			data.add(tmp);
		}
		ordersTable.setItems(data);
		
		Main.actionstage.show();
		Main.loadingstage.hide();
	
	}
	
	private void changeStatus(OrderStatus status) {
		if(!ordersTable.getSelectionModel().isEmpty()) {
			Order tmp = ordersTable.getSelectionModel().getSelectedItem();
			if(status.equals(OrderStatus.DELIVERED))
				tmp.setDELIVERED();
			else
				tmp.setDELIVERING();
			
			Order delete = null;
			for(Order o : Main.order) {
				if(o.getId().equals(tmp.getId())) {
					delete = o;
					break;
				}
			}
			Main.order.remove(delete);
			Main.order.add(tmp);
			Main.order.write();
		}
	}
	
	private void showProducts() {
		if(!ordersTable.getSelectionModel().isEmpty()) {
			Order tmp = ordersTable.getSelectionModel().getSelectedItem();
			Stage showing = new ActionsStage(tmp);
		}
	}
}
