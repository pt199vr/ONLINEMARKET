package onlinemarket.stages;

import java.io.File;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main; 
import onlinemarket.account.EditorAccount;

public class EditorShopStage extends Stage {
	private BorderPane layout;
	private EditorShopStageGui shopgui;
	private EditorAccount t;
	
	public EditorShopStage(EditorAccount t) {
		
		Main.loadingstage.show();
		System.gc();
		this.t = t;
		
		listFiles(new File(Main.mediapath));
				
		new Thread(() -> {
			layout = new BorderPane();
			shopgui = new EditorShopStageGui();
			layout.setCenter(shopgui);
			
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
				Platform.exit();
				System.exit(0);
			});
			
		}).start();
	}

	public void listFiles(final File folder) {
		for(File fileEntry : folder.listFiles()) {
			Main.pictures.add(fileEntry.getName());
		}
	}
//
}
