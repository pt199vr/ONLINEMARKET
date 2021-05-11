package onlinemarket;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.HashMap;

import onlinemarket.stages.*;

<<<<<<< HEAD
import onlinemarket.departments.Department;

=======
>>>>>>> branch 'main' of https://github.com/pt199vr/ONLINEMARKET
import onlinemarket.departments.Department;
<<<<<<< HEAD

import onlinemarket.readnwrite.*;

=======
>>>>>>> branch 'main' of https://github.com/pt199vr/ONLINEMARKET
import onlinemarket.readnwrite.*;
<<<<<<< HEAD

=======
>>>>>>> branch 'main' of https://github.com/pt199vr/ONLINEMARKET
import onlinemarket.account.*;


public class Main extends Application{
	public static final Image logo = new Image(Main.class.getResourceAsStream("onlinemarketlogo.png"));
	public static String title = "Online Market";	
	public static Stage loadingstage, firststage, loginstage, registrationstage, startingstage, shopstage;
	
	public static final String path = System.getProperty("user.dir") + "/Accounts";
	public static final String mediapath = System.getProperty("user.dir") + "/Media";
	public static final String store = System.getProperty("user.dir")+"/Store";
	
	public static final HashMap<Object, Thread> threads = new HashMap<>(1);
	
	
	public static final RnW_EditorAccount editoraccount = new RnW_EditorAccount(path + "/Editors.txt");
	public static final RnW_Account account = new RnW_Account(path + "/Customers.txt");
	public static final RnW_Department department = new RnW_Department(store + "/Departments.txt");
	public static final RnW_Product product = new RnW_Product(store + "/Products.txt");

	@Override
	public void start(Stage primaryStage) {
		loadingstage = new LoadingStage();
		startingstage = new StartingStage();
		startingstage = null;
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
	
	public static void shopping(Account t) {
		loadingstage.hide();
		shopstage = new ShopStage(t);
	}
	public static void EditorShopping(EditorAccount t) {
		loadingstage.hide();
		shopstage= new EditorShopStage(t);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public String createId() {
		boolean b = true;
		String s = "OM";
		
		if(editoraccount.read()) {
			
			while(b) {
				b = false;
				double random = (int)(Math.random() * 1000000);
				s += random;
				for(EditorAccount a : editoraccount) {
					if(a.getId().equals(s))
						b = true;
				}
			}
		}
		
		return s;
	}
}
