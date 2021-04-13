package onlinemarket.stages;

import java.io.IOException;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;



public class RegistrationGui extends AnchorPane{
	
	@FXML 
	private TextField NameT, MailT, CelT, CAPT, CityT, SurnameT, AddrT;
	@FXML
	private PasswordField PasswordF;
	@FXML
	private Button BackB, RegB;
	@FXML
	private Label NameL, SurnameL, CityL, MailL, CAPL, CelL, PasswordL, AddrL;
	@FXML
	private RadioButton EditorRB,CustomerRB;
	
	public RegistrationGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registration.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Thread back = new Thread(() -> {
			
			BackB.setOnAction(e -> backFunction());
			
			RegB.setOnAction(e -> registerFunction());
			
		});back.start();
		
	}
	
	private void registerFunction() {
		
		String name = NameT.getText(), surname = SurnameT.getText(), mail = MailT.getText(), city = CityT.getText(),  address = AddrT.getText();
		int phonenumber = Integer.parseInt(CelT.getText()), cap = Integer.parseInt(CAPT.getText());
	}
	
	private void backFunction() {
		Main.registrationstage.hide();
		Main.firststage.show();
	}
	
}
