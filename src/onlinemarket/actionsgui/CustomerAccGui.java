package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import onlinemarket.stages.ShopStageGui;

public class CustomerAccGui extends AnchorPane{
	@FXML
	private TextField NameT, SurnameT,MailT,CityT,CAPT,CelT;
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
		
		
		
	}

}
