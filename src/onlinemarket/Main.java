package onlinemarket;

import onlinemarket.login.*;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import onlinemarket.login.*;


public class Main extends Application{
	public static Stage loadingstage;
	
	
	@Override
	public void start(Stage primaryStage) {
		loadingstage = new LoadingStage();

	}
	public static void main(String[] args) {
		launch(args);
	}

}
