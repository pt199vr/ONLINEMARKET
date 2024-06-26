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
import onlinemarket.account.*;
import onlinemarket.product.*;
import onlinemarket.fidelitycard.*;
import onlinemarket.order.*;


public class Main extends Application{
	public static final Image logo = new Image(Main.class.getResourceAsStream("onlinemarketlogo.png"));
	public static String title = "Online Market";	
	public static Stage loadingstage, firststage, loginstage, registrationstage, startingstage, shopstage, actionstage, cartstage,orderstage;
	
	public static final String onlinemarket = System.getProperty("user.dir") + "/OnlineMarket1.0";
	public static final String path = onlinemarket + "/Accounts";
	public static final String mediapath = onlinemarket + "/Media";
	public static final String store = onlinemarket +"/Store";
	
	public static HashMap<Department, DepartmentGui> depmap = new HashMap<>();
	public static HashMap<Product, ProductGui> prodmap = new HashMap<>();
	
	public static final RnW_EditorAccount editoraccount = new RnW_EditorAccount(path + "/Editors.txt");
	public static final RnW_Account account = new RnW_Account(path + "/Customers.txt");
	public static final RnW_Department department = new RnW_Department(store + "/Departments.txt");
	public static final RnW_Product product = new RnW_Product(store + "/Products.txt");
	public static final RnW_FidelityCard fidelitycard = new RnW_FidelityCard(store + "/FC.txt");
	public static final RnW_Payment payment = new RnW_Payment(store + "/Payments.txt");
	public static final RnW_Order order = new RnW_Order(store + "/Orders.txt");
	public static final TreeSet<String> pictures = new TreeSet<>();

	@Override
	public void start(Stage primaryStage) {
		loadingstage = new LoadingStage();
		startingstage = new StartingStage();
		firststage = new FirstStage();
	}
		
	public static void login() {
		firststage.hide();
		loginstage = new LoginStage();
	}
	
	public static void registration() {
		firststage.hide();
		registrationstage = new RegistrationStage();
	}
	
	public static void shopping(Account t) {
		loadingstage.hide();
		cartstage = new CartStage(t);
		shopstage = new ShopStage(t);				
	}
	public static void EditorShopping(EditorAccount t) {
		loadingstage.hide();
		shopstage= new EditorShopStage(t);
	}
		
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void createCartstage() {
		cartstage = new CartStage(((ShopStage)shopstage).getAccount());
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
	
	public static String createId() {
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
	
	public static String getIdOrder() {
		boolean b = true;
		String s = "ORDER n. ";
		
		if(editoraccount.read()) {
			
			while(b) {
				b = false;
				Double random = (double)(Math.random() * 1000000000000L);
				s += random;
				for(Order a : order) {
					if(a.getId().equals(s)) {
						s = "ORDER n. ";
						b = true;
					}
						//
				}
			}
		}
		return s;
	}
}
