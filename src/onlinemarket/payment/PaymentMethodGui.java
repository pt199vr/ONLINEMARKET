package onlinemarket.payment;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.stages.ShopStageGui;

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
		
		if(PayPalTab.isSelected()) {
			PayPalConfirmB.setOnAction(e ->paypal());
			PayPalConfirmB.setOnKeyPressed(keyEvent ->{
				if(keyEvent.getCode() == KeyCode.ENTER)
					paypal();
			});
		}
		if(CreditCardTab.isSelected()) {
			CCConfirmB.setOnAction(e -> CreditCard());
			CCConfirmB.setOnKeyPressed(keyEvent -> {
				if(keyEvent.getCode()== KeyCode.ENTER)
					CreditCard();
			});
		}
		if(CashTab.isSelected()) {
			ConfirmCashB.setOnAction(e -> cash());
			ConfirmCashB.setOnKeyPressed(keyEvent -> {
				if(keyEvent.getCode()==KeyCode.ENTER)
					cash();
			});
			
		}
		
		
	}
	
	private void paypal() {
		
	}
	
	private void CreditCard() {
		
	}
	
	private void cash() {
		
	}

}
