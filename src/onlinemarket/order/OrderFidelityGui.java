package onlinemarket.order;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import onlinemarket.Main;
import onlinemarket.fidelitycard.FidelityCard;
import onlinemarket.stages.ShopStage;


public class OrderFidelityGui extends AnchorPane{
	
	@FXML 
	private Label noFidelityL,currentFidelityPointsL,fidelitypoints,PointsToGet,pointsToAddL;
	@FXML
	private Button GetCardB;
	private Integer actualPoints, addPoints;
	private FidelityCard tmp;
	
	public OrderFidelityGui() {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FidelityRecap.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		GetCardB.setOnAction(e -> {
			FidelityCard f = new FidelityCard(((ShopStage)Main.shopstage).getAccount());
			
			Main.fidelitycard.add(f);
			Main.fidelitycard.write();
			
			noFidelityL.setVisible(false);
			GetCardB.setVisible(false);
			GetCardB.setDisable(true);
			
			currentFidelityPointsL.setVisible(true);
			PointsToGet.setVisible(true);
			
			actualPoints = f.getPoints();
			fidelitypoints.setText(actualPoints.toString());
			addPoints = (int) Math.round(((ShopStage)Main.shopstage).getGui().getCart().getPrice());
			pointsToAddL.setText(addPoints.toString());
		});
		
		for(FidelityCard f: Main.fidelitycard) {
			if(f.getAccount().equals(((ShopStage)Main.shopstage).getAccount().toString())){
				tmp = f;
				currentFidelityPointsL.setVisible(true);
				PointsToGet.setVisible(true);
				
				actualPoints = tmp.getPoints();
				fidelitypoints.setText(actualPoints.toString());
				addPoints = (int) Math.round(((ShopStage)Main.shopstage).getGui().getCart().getPrice());
				if(addPoints > ((ShopStage)Main.shopstage).getGui().getCart().getPrice().intValue())
					addPoints--;
				pointsToAddL.setText(addPoints.toString());
			}
		}
		if(tmp == null) {
			
			noFidelityL.setVisible(true);
			GetCardB.setVisible(true);
			GetCardB.setDisable(false);
			
			currentFidelityPointsL.setVisible(false);
			PointsToGet.setVisible(false);
		}
		
	}

}
