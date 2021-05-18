package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.account.Email;
import onlinemarket.stages.EditorShopStageGui;


public class CustomersTableGui extends AnchorPane{

	private Account SelCustomer;
	
	@FXML
	private TableView<Account> customers;
	@FXML
	private TableColumn<Account,String> nameCol,surnameCol;
	@FXML
	private TableColumn<Account,Email> mailCol;
	@FXML
	private TableColumn<Account,Long> CelCol;
	
	@FXML
	private MenuItem DeleteEditor;
	
	public CustomersTableGui() {
		
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomersAccounts.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		customers = new TableView<Account>();
		Main.account.read();		
	
		mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		CelCol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
		
		ObservableList<Account> data = FXCollections.observableArrayList();	
		for(Account a : Main.account)
			data.add(a);
		customers.setItems(data);
		
		
		
		customers.getSelectionModel().getSelectedItems().addListener(
				(ListChangeListener.Change<? extends Account> change)-> select(change.getList()));
		if(SelCustomer != null)
			customers.getSelectionModel().select(SelCustomer);
	}

	private void select(ObservableList<? extends Account> list) {
		
	}

	private void init() {
		
		
	}
}
