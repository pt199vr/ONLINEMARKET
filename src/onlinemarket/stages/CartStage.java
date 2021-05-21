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
	private CartStageGui gui = null;
	
	public CartStage(Account t,ShopStageGui f,Cart c) {
		Main.loadingstage.show();
		System.gc();
		
		layout = new BorderPane();
		
		gui = new CartStageGui(t,f,c);
		
		Thread tmp = new Thread(() -> {
			
			layout.setCenter(gui);
			
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(600);
			setWidth(800);
			setResizable(true);
			Platform.runLater(()->{
				setScene(new Scene(layout));
				show();
				Main.loadingstage.hide();
			});
			
			setOnCloseRequest(e -> {
				Main.shopstage.show();					
			});
			
		}); tmp.start();
		
	}
	
	
	public CartStageGui getCartGui(){
		return gui;
	}
	 
}