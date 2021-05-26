package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main;
import onlinemarket.account.Account;
import onlinemarket.cart.Cart;
import javafx.scene.layout.BorderPane;

public class CartStage extends Stage{
	
	private BorderPane layout;
	private CartStageGui cartgui = null;

	public CartStage(Account t) {

		Main.loadingstage.show();
		System.gc();
		
		
		
		Thread tmp = new Thread(() -> {
			layout = new BorderPane();
			cartgui = new CartStageGui(t);
			layout.setCenter(cartgui);
			
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
			
		}); tmp.start();
		
	}
	
	
	public CartStageGui getCartGui(){
		return cartgui;
	}
	
	 
}