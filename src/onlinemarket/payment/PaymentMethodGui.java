package onlinemarket.payment;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.stages.ShopStageGui;
import onlinemarket.account.*;

public class PaymentMethodGui extends AnchorPane {
	
	@FXML
	private Tab PayPalTab, CreditCardTab, CashTab;
	@FXML
	private TextField PayPalMailT, PayPalPassT, CreditHolderT, CardIDT, CVVT;
	@FXML
	private Button PayPalConfirmB, CCConfirmB, ConfirmCashB;
	@FXML
	private ChoiceBox<String> MonthChoiceB, YearChoiceB;
	
	
	public PaymentMethodGui(ShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paymentMethod.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		String months[]= {"1","2","3","4","5","6","7","8","9","10","11","12"};
		MonthChoiceB.getItems().addAll(months);	
		Integer year = java.time.LocalDateTime.now().getYear();
		Integer years[] = {year, year + 1, year + 2, year + 3, year + 4};
		
		for(int i=0; i< years.length; i++) {
			YearChoiceB.getItems().add(years[i].toString());
		} 
		
		Thread buttons = new Thread( () -> {
			PayPalConfirmB.setOnAction(e ->paypal(f));
			CCConfirmB.setOnAction(e -> CreditCard(f));
			ConfirmCashB.setOnAction(e -> cash(f));
		});buttons.start();
		
	}
	
	private void cash(ShopStageGui f) {
		Payment tmp = new Payment(f.getAccount());
		Payment x=null;
		for(Payment t: Main.payment) {
			if(tmp.getAccount().equals(t.getAccount()))
				x = t;
		}
		if(x!=null)
			Main.payment.remove(x);
		Main.payment.add(tmp);		
		Main.payment.write();
		Alert a= new Alert(Alert.AlertType.NONE,"Payment Method has been recorded",ButtonType.OK);
		a.showAndWait();
		Main.actionstage.hide();
		Main.shopstage.show();
	}
	
	private void CreditCard(ShopStageGui f) {
		String cvv = CVVT.getText(), cardId = CardIDT.getText();
		if(cvv==null|| cardId==null) {
			Alert a= new Alert(Alert.AlertType.NONE,"Please fill all the fields",ButtonType.OK);
			a.showAndWait();
			return;
			}
		if(cvv.length() != 3 || cardId.length() != 16 ||
					java.time.LocalDateTime.now().getYear() < YearChoiceB.getSelectionModel().getSelectedIndex() || 
					(java.time.LocalDateTime.now().getYear() == YearChoiceB.getSelectionModel().getSelectedIndex() 
					&& java.time.LocalDateTime.now().getMonth().getValue() < MonthChoiceB.getSelectionModel().getSelectedIndex())) {
			Main.actionstage.hide();
			Main.loadingstage.show();
			Alert a = new Alert(Alert.AlertType.NONE, "CVV must be 3 characters\nCardNumber 16\nYou have to type a valide date", ButtonType.OK);
			Main.loadingstage.hide();
			a.showAndWait();
			Main.actionstage.show();
		}
		
		Payment tmp = new Payment( CardIDT.getText(), CreditHolderT.getText(), YearChoiceB.getSelectionModel().getSelectedIndex(), MonthChoiceB.getSelectionModel().getSelectedIndex(), CVVT.getText(),f.getAccount());
		Payment x=null;
		for(Payment t: Main.payment) {
			if(tmp.getAccount().equals(t.getAccount()))
				x = t;
		}
		if(x!=null)
			Main.payment.remove(x);
		Main.payment.add(tmp);
		Main.payment.write();
		Alert a= new Alert(Alert.AlertType.NONE,"Payment Method has been recorded",ButtonType.OK);
		a.showAndWait();
		Main.actionstage.hide();
		Main.shopstage.show();
	}
	
	private void paypal(ShopStageGui f) {
		
		try {
			Payment tmp = new Payment(new Email(PayPalMailT.getText()), new Password(PayPalPassT.getText()),f.getAccount());
			Payment x=null;
			for(Payment t: Main.payment) {
				if(tmp.getAccount().equals(t.getAccount()))
					x = t;
			}
			if(x!=null)
				Main.payment.remove(x);
			Main.payment.add(tmp);
			Main.payment.write();
			Alert a= new Alert(Alert.AlertType.NONE,"Payment Method has been recorded",ButtonType.OK);
			a.showAndWait();
			Main.actionstage.hide();
			Main.shopstage.show();
		}catch(IllegalArgumentException e) {
			Main.actionstage.hide();
			Main.loadingstage.show();
			Alert a = new Alert(Alert.AlertType.NONE, "Wrongs email or password", ButtonType.OK);
			Main.loadingstage.hide();
			a.showAndWait();
			Main.actionstage.show();
		}
	}
}
