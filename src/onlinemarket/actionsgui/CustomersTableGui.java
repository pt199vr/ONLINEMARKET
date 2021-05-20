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
import onlinemarket.account.Address;
import onlinemarket.account.Email;
import onlinemarket.datentime.Date;
import onlinemarket.fidelitycard.FidelityCard;
import onlinemarket.actionsgui.AccountFidelity;


public class CustomersTableGui extends AnchorPane{

	private AccountFidelity SelCustomer;
	
	@FXML
	private TableView<AccountFidelity> customers;
	@FXML
	private TableColumn<AccountFidelity,String> nameCol,surnameCol;
	
	@FXML
	private TableColumn<AccountFidelity,Email> mailCol;
	@FXML
	private TableColumn<AccountFidelity,Date> DateCol;
	@FXML
	private TableColumn<AccountFidelity,Integer> pointsCol;
	@FXML
	private TableColumn<AccountFidelity,String> IDCardCol;
	@FXML
	private TableColumn<AccountFidelity,Long> CelCol;
	@FXML
	private TableColumn<AccountFidelity,Address> AddressCol;
	
	@FXML
	private Menu DeleteACC;
	@FXML
	private Label DeleteL;
	
	public CustomersTableGui() {
		
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CustomersAccounts.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Main.account.read();
		Main.fidelitycard.read();
	
		mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));		
		AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));		
		pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));		
		CelCol.setCellValueFactory(new PropertyValueFactory<AccountFidelity, Long>("PhoneNumber"));
		IDCardCol.setCellValueFactory(new PropertyValueFactory<AccountFidelity, String>("CardID"));
		DateCol.setCellValueFactory(new PropertyValueFactory<AccountFidelity, Date>("CardDate"));
		
		
		
		ObservableList<AccountFidelity> data = FXCollections.observableArrayList();	
		boolean check = true;
		for(Account a : Main.account) {
			for(FidelityCard b : Main.fidelitycard) {
				if(a.toString().equals(b.getAccount())) {
					check = false;
					data.add(new AccountFidelity(a.getName(), a.getSurname(), a.getEmail(), a.getPassword(), a.getPhoneNumber(), a.getAddress(), b));
				}
			}
			if(check)
				data.add(new AccountFidelity(a.getName(), a.getSurname(), a.getEmail(), a.getPassword(), a.getPhoneNumber(), a.getAddress(), new FidelityCard("MissingCard")));
			check = true;
		}
			
		customers.setItems(data);
				
		if(SelCustomer != null)
			customers.getSelectionModel().select(SelCustomer);
		
		Thread t = new Thread(() -> {
			DeleteL.setOnMouseClicked(e -> delete());
			
		});t.start();
	}

	private void delete() {
		if(Main.account.size() > 0) {
		SelCustomer = customers.getSelectionModel().getSelectedItem();
		customers.getItems().remove(SelCustomer);
		Main.account.remove(SelCustomer);
		Main.account.write();
		}
		if(Main.account== null || Main.account.size() == 0){
			Alert a = new Alert(Alert.AlertType.NONE,"There's no account to delete",ButtonType.OK);
			a.showAndWait();
			return;
		}
				
	}
}