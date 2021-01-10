package onlinemarket;



import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.HashMap;

import onlinemarket.stages.*;
import onlinemarket.person.*;



public class Main extends Application{
	public static Stage loadingstage, loginstage, mainstage;
	public static String title = "Online Market";
	
	public static final Image icon = new Image(Main.class.getResourceAsStream("onlinemarketlogo.png"));
	
	public static Person person;
	//public static final Person user = new User();
	
	public static final HashMap<Object, Thread> threads = new HashMap<>(1);
	
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
