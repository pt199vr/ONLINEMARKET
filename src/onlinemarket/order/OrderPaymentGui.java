package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.account.Email;
import onlinemarket.account.Password;
import onlinemarket.payment.Payment;
import onlinemarket.stages.ShopStage;

public class OrderPaymentGui extends AnchorPane{
	
	@FXML
	private TextField PayPalMailT,CreditHolderT,CardIDT,CVVT;
	@FXML
	private PasswordField PayPalPassT;
	@FXML
	private Button PayPalConfirmB,CCConfirmB,ConfirmCashB;
	@FXML
	private ChoiceBox<String> MonthChoiceB, YearChoiceB;
	@FXML
	private Tab PayPalTab,CreditCardTab,CashTab;
	
	private Payment tmp;
	
	public OrderPaymentGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderPayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		PayPalConfirmB.setOnAction(e -> {
			if(checkPayPal()) {
				Email m = new Email(PayPalMailT.getText());
				Password p = new Password(PayPalPassT.getText());
				tmp = new Payment(m, p, ((ShopStage)Main.shopstage).getAccount());
			}
		});
		
		CCConfirmB.setOnAction(e -> {
			if(checkCC())
				tmp = new Payment(CardIDT.getText(), CreditHolderT.getText(), Integer.parseInt(MonthChoiceB.getSelectionModel().getSelectedItem()),Integer.parseInt(YearChoiceB.getSelectionModel().getSelectedItem()), CVVT.getText(),((ShopStage)Main.shopstage).getAccount());
		});
		ConfirmCashB.setOnAction(e -> {
			tmp = new Payment(((ShopStage)Main.shopstage).getAccount());
			
		});
	}
	
	private boolean checkCC(){
		
		String holder = CreditHolderT.getText(), ID = CardIDT.getText();
		if(holder.equals("") || ID.equals("")) {
			Alert b= new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			b.showAndWait();
			return false;
		}
		Integer cvv ;
		try{
			cvv = Integer.parseInt(CVVT.getText());
			if(cvv.toString().length()>3)
				throw new NumberFormatException();
		}catch(NumberFormatException e) {
			Alert b = new Alert(Alert.AlertType.NONE,"CVV must be 3 digits long",ButtonType.OK);
			b.showAndWait();
			return false;
		}
		return true;
	}
	
	private boolean checkPayPal() {
		String m = PayPalMailT.getText(), p = PayPalPassT.getText();
		if(m.equals("") || p.equals("")) {
			Alert a = new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			a.showAndWait();
			return false;
		}
		
		return true;
	}
	
	private Payment getChoosenPayment() {
		return tmp;
	}
	

}
