package onlinemarket;



import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.HashMap;

import onlinemarket.stages.*;
import onlinemarket.person.*;



public class Main extends Application{
	public static final Image logo = new Image(Main.class.getResourceAsStream("onlinemarketlogo.png"));
	public static String title = "Online Market";	
	public static Stage mainstage, loadingstage, firststage, loginstage, registrationstage;
	
	public static final String path = System.getProperty("user.dir") + "Accounts";
	public static final String mediapath = System.getProperty("user.dir") + "Media";
	
	@Override
	public void start(Stage primaryStage) {
		loadingstage = new LoadingStage();
		loadingstage.show();
		first();
	}
	
	public static void first() {
		firststage = new FirstStage();
	}
	
	public static void login() {
		firststage.close();
		loginstage = new LoginStage();
	}
	
	public static void registration() {
		firststage.close();
		registrationstage = new RegistrationStage();
	}
	
	public static void shop(Person person) {
		mainstage = new MainStage();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
		

}
