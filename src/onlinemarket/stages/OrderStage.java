package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main;
import onlinemarket.account.Account;

public class OrderStage extends Stage{
	
	private BorderPane layout;
	private OrderStageGui orderGui;
	
	public OrderStage(Account t) {
		Main.loadingstage.show();
		System.gc();
		
		Thread thread = new Thread(()->{
			layout= new BorderPane();
			orderGui= new OrderStageGui(t);
			layout.setCenter(orderGui);
			
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(600);
			setWidth(800);
			setResizable(true);
			Platform.runLater(()->{
				setScene(new Scene(layout));
				Main.loadingstage.show();
			});
			
			setOnCloseRequest(e -> {
				Main.shopstage.show();					
			});
			
	}); thread.start();
	}
}
