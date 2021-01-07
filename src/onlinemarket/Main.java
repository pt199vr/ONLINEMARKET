package onlinemarket;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import onlinemarket.stages.*;
import onlinemarket.person.*;



public class Main extends Application{
	public static Stage loadingstage, loginstage, mainstage;
	public static String title = "Online-Market";
	
	public static Person person;
	
	
	@Override
	public void start(Stage primaryStage) {
		loadingstage = new LoadingStage();
		login();
	}
	
	public static void login() {
		loginstage = new LoginStage();
	}
	
	public static void shop(Person person) {
		Main.person = person;
		mainstage = new MainStage();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
