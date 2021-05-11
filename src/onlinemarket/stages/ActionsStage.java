package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main;
import onlinemarket.actionsgui.*;

public class ActionsStage extends Stage{
	private BorderPane layout;
	private CreateDepGui create;
	private ModifyDepGui modify;
	private DeleteDepGui delete;
	
	
	public ActionsStage(String s) {
					
			Main.loadingstage.show();
			System.gc();
			
			create = new CreateDepGui();
			modify = new ModifyDepGui();
			delete = new DeleteDepGui();
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
				setHeight(768);
				setWidth(1024);
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
