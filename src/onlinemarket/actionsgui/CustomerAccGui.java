package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.stages.ShopStageGui;

public class CustomerAccGui extends AnchorPane{
	
	@FXML
	private TextField NameT, SurnameT,MailT,CityT,CAPT,CelT;
	@FXML
	private PasswordField PasswordT;
	@FXML
	private Button DeleteB,BackB;
	
	public CustomerAccGui(ShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserAccountView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		

		DeleteB.setOnAction(e -> delete());
		DeleteB.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
				delete();
		});
		
		BackB.setOnAction(e -> mod());
		BackB.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER)
				mod();
		});
		
	}
	@FXML
	private void delete() {
		
	}
	@FXML
	private void mod() {
		
	}

}
