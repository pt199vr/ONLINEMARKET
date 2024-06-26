package onlinemarket.order;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.account.*;
import onlinemarket.payment.Payment;
import onlinemarket.payment.PaymentType;
import onlinemarket.stages.ShopStage;

public class OrderPaymentGui extends AnchorPane{
	
	@FXML
	private TextField PayPalMailT,CreditHolderT,CardIDT,CVVT;
	@FXML
	private PasswordField PayPalPassT;
	@FXML
	private Button PayPalConfirmB,CCConfirmB;
	@FXML
	private CheckBox cashB;
	@FXML
	private ChoiceBox<String> MonthChoiceB, YearChoiceB;
	@FXML
	private Tab PayPalTab,CreditCardTab,CashTab;
	
	private Payment tmp = null;
	
	public OrderPaymentGui(Account account) {
		
		for(Payment p : Main.payment) {
			if(p.getAccount().equals(account)) {
				tmp = p;
			}
		}
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderPayment.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		if(tmp!=null) {
			if(tmp.getType().equals(PaymentType.CREDITCARD)){
				
				CardIDT.setText(tmp.getNumber());	
			}
			else if(tmp.getType().equals(PaymentType.PAYPAL)) {
				PayPalMailT.setText(tmp.getEmail().toString());
			}
			else {
				cashB.setSelected(true);
			}
		}	
			
		
		Integer year = java.time.LocalDateTime.now().getYear();
		Integer temp = year;
		ArrayList<String> ys = new ArrayList<>();
		for(int i= 0;i<6;i++,temp++) {
			ys.add(temp.toString());
		}
		YearChoiceB.getItems().addAll(ys);
		YearChoiceB.getSelectionModel().selectedItemProperty().addListener(
			(ChangeListener<String>)(o,ov,nv)->{
				MonthChoiceB.getItems().clear();
				ArrayList<String> months= new ArrayList<>();
				if(YearChoiceB.getValue().equals(year.toString())) {
					Integer month = 1 + java.time.LocalDateTime.now().getMonthValue();
					for(Integer i = month; i<13; i++) {
						months.add(i.toString());
					}
					MonthChoiceB.getItems().addAll(months);
				}
				else {
					months.clear();
					for(Integer i = 1 ; i < 13 ;i++)
						months.add(i.toString());
					MonthChoiceB.getItems().addAll(months);
				}
				
				MonthChoiceB.getSelectionModel().selectFirst();
			}
		);
		YearChoiceB.getSelectionModel().selectFirst();
	}
	
	public Payment getPM() {
		return tmp;
	}
	
	public boolean check() {
		if(CreditCardTab.isSelected()) {
			String holder = CreditHolderT.getText(), month = MonthChoiceB.getSelectionModel().getSelectedItem(),
					year = YearChoiceB.getSelectionModel().getSelectedItem(), id = CardIDT.getText(),cvv= CVVT.getText();
			if(holder.equals("") || month.equals("") || year.equals("") || id.equals("") || cvv.equals("")) {
				Alert a= new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
				a.showAndWait();
				return false;
			}
			Integer CVV,m,y;
			try {
				if(cvv.length() != 3)
					throw new NumberFormatException();
				CVV = Integer.parseInt(cvv); 
			}catch(NumberFormatException e) {
				Alert b = new Alert(Alert.AlertType.NONE,"The cvv you inserted is invalid",ButtonType.OK);
				b.showAndWait();
				return false;
			}
			
			try {
				m = Integer.parseInt(month);
				y = Integer.parseInt(year);
			}catch(NumberFormatException e) {
				Alert c= new Alert(Alert.AlertType.NONE,"Invalid Date",ButtonType.OK);
				c.showAndWait();
				return false;
			}
			if(id.length() != 16) {
				Alert d= new Alert(Alert.AlertType.NONE,"Invalid id",ButtonType.OK);
				d.showAndWait();
				return false;
			}
			tmp = new Payment(id,holder, y, m, cvv, ((ShopStage)Main.shopstage).getAccount());
		}
		else if(PayPalTab.isSelected()) {
			String mail = PayPalMailT.getText(),pass = PayPalPassT.getText();
			if(mail.equals("")|| pass.equals("")) {
				Alert a = new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
				a.showAndWait();
				return false;
			}
			
			Email email;
			try {
				email= new Email(mail);
			}catch(IllegalArgumentException e) {
				Alert b = new Alert(Alert.AlertType.NONE,"Invalid emails",ButtonType.OK);
				b.showAndWait();
				return false;
			}
			
			Password password;
			try {
				password= new Password(pass);
			}catch(IllegalArgumentException e) {
				Alert c = new Alert(Alert.AlertType.NONE,"Invalid Password",ButtonType.OK);
				c.showAndWait();
				return false;
			}
			tmp = new Payment(email, password,((ShopStage)Main.shopstage).getAccount() );
		}
		else if(CashTab.isSelected()) {
			cashB.setSelected(true);
			tmp = new Payment(((ShopStage)Main.shopstage).getAccount());
		}
		else {
			Alert d= new Alert(Alert.AlertType.NONE,"Choose a payment method",ButtonType.OK);
			d.showAndWait();
			return false;
		}
		return true;
	}
	
	public boolean checkPNull() {
		if(tmp == null)
			return true;
		else 
			return false;
	}
	
}
