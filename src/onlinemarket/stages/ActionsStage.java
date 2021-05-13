package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main;
import onlinemarket.actionsgui.*;
import onlinemarket.fidelitycard.FDCViewGui;
import onlinemarket.payment.PaymentMethodGui;

public class ActionsStage extends Stage{
	private BorderPane layout;
	private CreateDepGui create;
	private ModifyDepGui modify;
	private DeleteDepGui delete;
	private FDCViewGui fdc;
	private PaymentMethodGui payment;
	
	
	public ActionsStage(String s, EditorShopStageGui f) {
			
			Main.loadingstage.show();
			System.gc();
			
			create = new CreateDepGui(f);
			modify = new ModifyDepGui(f);
			delete = new DeleteDepGui(f);
			new Thread(() -> {
				
				layout = new BorderPane();
				
				if("create".equals(s)) {					
					layout.setCenter(create);
				}
				if("modify".equals(s)) {
					layout.setCenter(modify);
				}
				if("delete".equals(s)) {
					layout.setCenter(delete);
				}
				if("showaccount".equals(s)) {					
					layout.setCenter(modify);
				}
				if("showeditors".equals(s)) {					
					layout.setCenter(modify);
				}
				if("showcustomers".equals(s)) {					
					layout.setCenter(modify);
				}
				if("showorders".equals(s)) {					
					layout.setCenter(modify);
				}
				
				
				setTitle(Main.title);
				getIcons().add(Main.logo);
				setHeight(400);
				setWidth(600);
				setResizable(true);
				Platform.runLater(()->{
					setScene(new Scene(layout));
					show();
					Main.loadingstage.hide();
				});
				
				setOnCloseRequest(e -> {
					Main.shopstage.show();					
				});
				
			}).start();
		

	}
	
	public ActionsStage(String s, ShopStageGui f) {
		
		Main.loadingstage.show();
		System.gc();
		fdc = new FDCViewGui(f);
		payment = new PaymentMethodGui(f);
		
		new Thread(()->{
			layout = new BorderPane();
			if("fidelity".equals(s))
				layout.setCenter(fdc);
			if("payment".equals(s))
				layout.setCenter(payment);
		
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(400);
			setWidth(600);
			setResizable(true);
			Platform.runLater(()->{
				setScene(new Scene(layout));
				show();
				Main.loadingstage.hide();
			});
			
			setOnCloseRequest(e -> {
				Main.shopstage.show();					
			});
		
		}).start();
	
	}
}
