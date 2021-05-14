package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.account.Address;
import onlinemarket.account.EditorAccount;
import onlinemarket.account.Email;
import onlinemarket.account.Password;
import onlinemarket.stages.EditorShopStageGui;

public class EditorAccGui extends AnchorPane{
	@FXML
	private TextField NameT,SurnameT,MailT,CelT;
	@FXML
	private PasswordField PasswordT;
	@FXML
	private Label IDL;
	@FXML
	private Button ConfirmB;
	@FXML
	private ChoiceBox RoleCB;
	
	public EditorAccGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditorAccountView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		ConfirmB.setOnAction(e -> mod());
		ConfirmB.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER)
				mod();
		});
		
	}
	
	@FXML
	private void mod() {
		String name = NameT.getText(), surname = SurnameT.getText();
		
		if(name.isBlank() || surname.isBlank()) {
			Alert a= new Alert(Alert.AlertType.NONE,"Fill all the fields",ButtonType.OK);
			a.showAndWait();
			return;
		}
	}
}

