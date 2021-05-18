package onlinemarket.fidelitycard;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import onlinemarket.Main;
import onlinemarket.stages.ShopStageGui;

public class FDCCreationGui extends AnchorPane {
	
	@FXML
	private Button getCardB;
	
	public FDCCreationGui(ShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FidelityCardCreation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		getCardB.setOnMouseClicked(e -> fidelityCard(f));
	}

	private void fidelityCard(ShopStageGui f) {
		
		for(FidelityCard x : Main.fidelitycard) {
			if(x.getAccount().equals(f.getAccount().toString())) {
				return;
			}
		}
		Main.fidelitycard.add(new FidelityCard(f.getAccount()));
		System.out.println(Main.fidelitycard.toString());
		Main.fidelitycard.write();
		
	}

}
