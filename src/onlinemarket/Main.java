package onlinemarket;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.TreeSet;

import onlinemarket.stages.*;
import onlinemarket.departments.Department;
import onlinemarket.departments.DepartmentGui;
import onlinemarket.readnwrite.*;
import onlinemarket.readnwrite.*;
import onlinemarket.account.*;
import onlinemarket.product.*;
import onlinemarket.fidelitycard.*;;


public class Main extends Application{
	public static final Image logo = new Image(Main.class.getResourceAsStream("onlinemarketlogo.png"));
	public static String title = "Online Market";	
	public static Stage loadingstage, firststage, loginstage, registrationstage, startingstage, shopstage, actionstage;
	
	public static final String path = System.getProperty("user.dir") + "/Accounts";
	public static final String mediapath = System.getProperty("user.dir") + "/Media";
	public static final String store = System.getProperty("user.dir")+"/Store";
	
	public static HashMap<Department, DepartmentGui> depmap = new HashMap<>();
	public static HashMap<Product, ProductGui> prodmap = new HashMap<>();
	public static HashMap<Product, Department> prodepmap = new HashMap<>();
	
	public static final RnW_EditorAccount editoraccount = new RnW_EditorAccount(path + "/Editors.txt");
	public static final RnW_Account account = new RnW_Account(path + "/Customers.txt");
	public static final RnW_Department department = new RnW_Department(store + "/Departments.txt");
	public static final RnW_Product product = new RnW_Product(store + "/Products.txt");
	public static final RnW_FidelityCard fidelitycard = new RnW_FidelityCard(store + "/FC.txt");
	public static final TreeSet<String> pictures = new TreeSet<>();

	@Override
	public void start(Stage primaryStage) {
		loadingstage = new LoadingStage();
		startingstage = new StartingStage();
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
	
	public static String createFidelityId() {
		boolean b = true;
		String s ="";
		
		
		if(fidelitycard.read()) {
			
			while(b) {
				b = false;
				s += (int)(Math.random() * 100000000);
				for(FidelityCard a : fidelitycard) {
					if(a.getCardId().equals(s)) {
						s = "";
						b = true;
					}
				}
			}
		}
		return s;
	}
	
	public String createId() {
		boolean b = true;
		String s = "OM";
		
		if(editoraccount.read()) {
			
			while(b) {
				b = false;
				int random = (int)(Math.random() * 1000000);
				s += random;
				for(EditorAccount a : editoraccount) {
					if(a.getId().equals(s)) {
						s = "OM";
						b = true;
					}
						
				}
			}
		}
		return s;
	}
}
