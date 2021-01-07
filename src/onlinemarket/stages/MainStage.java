package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;


import onlinemarket.Main;



public class MainStage extends Stage{
	
	public MainStage() {
		Main.loadingstage.show();
		Main.loginstage.show();
		
		Thread loadingthread = new Thread(() -> {
			Main.loginstage = null;
			System.gc();
			
			
			
			
			
			
		});
		loadingthread.start();
		
		
		new Thread(() ->{
			
			setMaximized(true);
			setResizable(true);
			setTitle(Main.title);
			
			setMinHeight(768);
			setMinWidth(1024);
			
			
			
			
			
		}).start();
		
		
		
		
	}
	
}
