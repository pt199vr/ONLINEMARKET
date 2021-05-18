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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.stages.ShopStageGui;
import onlinemarket.account.*;

public class PaymentMethodGui extends AnchorPane {
	
	@FXML
	private Tab PayPalTab, CreditCardTab, CashTab;
	@FXML
	private TextField PayPalMailT, PayPalPassT, CreditHolderT, CardIdT, CVVT;
	@FXML
	private Button PayPalConfirmB, CCConfirmB, ConfirmCashB;
	@FXML
	private ChoiceBox MonthChoiceB, YearChoiceB;
	
	
	public PaymentMethodGui(ShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paymentMethod.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Thread buttons = new Thread( () -> {
			PayPalConfirmB.setOnAction(e ->paypal(f));
			CCConfirmB.setOnAction(e -> CreditCard(f));
			ConfirmCashB.setOnAction(e -> cash(f));
		});buttons.start();
		
	}
	
	private void cash(ShopStageGui f) {
		Payment tmp = new Payment(f.getAccount());
		Main.payment.add(tmp);		
		Main.payment.write();
		Main.actionstage.hide();
		Main.shopstage.show();
	}
	
	private void CreditCard(ShopStageGui f) {
		
		if(CVVT.getText().length() != 3 || CardIdT.getText().length() != 16 ||
					java.time.LocalDateTime.now().getYear() < YearChoiceB.getSelectionModel().getSelectedIndex() || 
					(java.time.LocalDateTime.now().getYear() == YearChoiceB.getSelectionModel().getSelectedIndex() 
					&& java.time.LocalDateTime.now().getMonth().getValue() < MonthChoiceB.getSelectionModel().getSelectedIndex())) {
			Main.actionstage.hide();
			Main.loadingstage.show();
			Alert a = new Alert(Alert.AlertType.NONE, "CVV must be 3 characters\nCardNumber 16\nyou have to type a valide date", ButtonType.OK);
			Main.loadingstage.hide();
			a.showAndWait();
			Main.actionstage.show();
		}
		
		Payment tmp = new Payment( CardIdT.getText(), CreditHolderT.getText(), YearChoiceB.getSelectionModel().getSelectedIndex(), MonthChoiceB.getSelectionModel().getSelectedIndex(), CVVT.getText(),f.getAccount());
		Main.payment.add(tmp);
		Main.payment.write();
	}
	
	private void paypal(ShopStageGui f) {
		
		try {
			Payment tmp = new Payment(new Email(PayPalMailT.getText()), new Password(PayPalPassT.getText()),f.getAccount());
			Main.payment.add(tmp);
			Main.payment.write();
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
