package onlinemarket.stages;
import java.io.File;
import java.nio.file.Files;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import onlinemarket.Main;

import onlinemarket.account.*;
import onlinemarket.departments.*;

import onlinemarket.Main;

public class ShopStage extends Stage {
	
	private BorderPane layout;
	private ShopStageGui shopgui;
	private Account t;
	
	public ShopStage(Account t) {
		
		for(Department s : Main.department) {
			Main.depmap.put(s, new DepartmentGui(s));
		}
			
		Main.loadingstage.show();
		System.gc();
		this.t = t;
		
		new Thread(() -> {
			layout = new BorderPane();
			shopgui = new ShopStageGui();
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
	

	

}
