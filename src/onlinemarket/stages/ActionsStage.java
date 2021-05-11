package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main;
import onlinemarket.account.EditorAccount;
import onlinemarket.actionsgui.*;

public class ActionsStage extends Stage{
	private BorderPane layout;
	private CreateGui create;
	
	
	public ActionsStage(String s) {
					
			Main.loadingstage.show();
			System.gc();
			
			
			new Thread(() -> {
				layout = new BorderPane();
				create = new CreateGui();
				if("create".equals(s))
					layout.setCenter(create);
				
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
