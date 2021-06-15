package onlinemarket.fidelitycard;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.stages.ShopStageGui;

public class FDCViewGui extends AnchorPane{
	@FXML
	private Label IDL,DateL,pointsL,HolderL
	;
	public FDCViewGui(ShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FidelityCardView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		String acc = f.getAccount().toString();
		for(FidelityCard x: Main.fidelitycard)
			if(acc.equals(x.getAccount())) {
				IDL.setText(x.getCardId());
				HolderL.setText(f.getAccount().getName() +" "+f.getAccount().getSurname() );
				DateL.setText(x.getDate().toString());
				pointsL.setText("" + x.getPoints());
			}
		
		
	}
	
}
