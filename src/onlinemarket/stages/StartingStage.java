package onlinemarket.stages;

import onlinemarket.*;
import onlinemarket.readnwrite.RnW_Account;
import onlinemarket.readnwrite.RnW_Department;
import onlinemarket.readnwrite.RnW_EditorAccount;
import onlinemarket.readnwrite.RnW_FidelityCard;
import onlinemarket.readnwrite.RnW_Payment;
import onlinemarket.readnwrite.RnW_Product;
import onlinemarket.departments.*;
import onlinemarket.product.*;

import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import onlinemarket.fidelitycard.FidelityCard;
import java.util.TreeSet;


public class StartingStage extends Stage{

	public StartingStage() {
		checkPath();
	     Main.account.read();
		 Main.editoraccount.read();
		 Main.department.read();
		 Main.product.read();
		 Main.fidelitycard.read();
		 Main.payment.read();
		 Main.order.read();
		 if(Main.department.isEmpty())
			 createDepartments();
		 if(Main.product.isEmpty())
		 	createProducts();
		 Main.loadingstage.show();		 
	}
	
	private void createProducts() {
		
		for(Department tmp : Main.department) {
			if(tmp.getName().equals("Fruit")) {
				TreeSet<String> t = new TreeSet<String>();
				t.add(Main.product.getFeatures(0));
				t.add(Main.product.getFeatures(2));
				t.add(Main.product.getFeatures(3));
				Main.product.add(new Product("Ananas", "FruitMania", 3.55, 1500, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Apples", "FruitMania", 2.99, 8, 50,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Apricots", "FruitMania", 4.00, 12, 50,TypeofQuantity.PIECES, tmp, t));
				t.add(Main.product.getFeatures(1));
				Main.product.add(new Product("Bananas", "FruitMania", 2.00, 6, 50,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Cherries", "Old Farmer", 4.00, 300, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Lemons", "Old Farmer", 2.00, 8, 50,TypeofQuantity.PIECES, tmp, t));
			}
			if(tmp.getName().equals("Fish")) {
				TreeSet<String> t = new TreeSet<String>();
				t.add(Main.product.getFeatures(0));
				t.add(Main.product.getFeatures(2));
				Main.product.add(new Product("Cod Filet", "CAPTAIN'S FISH", 4.00, 300, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Crab", "SeaFood Lovers", 15.00, 3000, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Cuttle Fish", "SeaFood Lovers", 8.00, 200, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Lobster", "SeaFood Lovers", 12.00, 2500, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Mackerel", "CAPTAIN'S FISH", 5.00, 500, 50,TypeofQuantity.GRAMS, tmp, t));
				
			}			
			if(tmp.getName().equals("Meat")) {
				TreeSet<String> t = new TreeSet<String>();
				t.add(Main.product.getFeatures(0));
				t.add(Main.product.getFeatures(1));
				Main.product.add(new Product("Bacon", "TEXASGRILL", 3.55, 1500, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Ham", "Ferrarini", 4.00, 200, 150,TypeofQuantity.PIECES, tmp, t));
				t.clear();
				t.add(Main.product.getFeatures(0));
				Main.product.add(new Product("Beef Fillet", "1900 Buthcer", 7.99, 300, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Beef Steak", "1900 Buthcer", 4.99, 250, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Beef Stew", "1900 Buthcer", 3.99, 500, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Chicken Breast", "TEXASGRILL", 2.99, 500, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Chicken Thigh", "TEXASGRILL", 3.99, 500, 50,TypeofQuantity.GRAMS, tmp, t));
				
			}
			if(tmp.getName().equals("Cheese and Milk")) {
				TreeSet<String> t = new TreeSet<String>();
				t.clear();
				Main.product.add(new Product("Cream Cheese", "DnDairy", 2.50, 300, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Milk bottle", "DnDairy", 1.10, 1, 50,TypeofQuantity.LITERS, tmp, t));
				
			}
			if(tmp.getName().equals("Sweets")) {
				TreeSet<String> t = new TreeSet<String>();
				t.clear();
				Main.product.add(new Product("Cannoli", "Terre Siciliane", 4.99, 3, 150,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Chocolate Biscuits", "Gold Oven", 2.99, 400, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Chocolate Cornetto", "Gold Oven", 2.99, 4, 150,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Chocolate Egg", "Saints Sweets", 3.39, 300, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Classic Cornetto", "Gold Oven", 2.50, 5, 150,TypeofQuantity.PIECES, tmp, t));
			}		
			
			if(tmp.getName().equals("Various Food")) {
				TreeSet<String> t = new TreeSet<String>();
				t.add(Main.product.getFeatures(2));
				Main.product.add(new Product("Eggs", "Old Farmer", 2.00, 6, 50,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Mayonnaise", "MNM", 4.00, 250, 50,TypeofQuantity.GRAMS, tmp, t));
				t.add(Main.product.getFeatures(3));
				Main.product.add(new Product("Ketchup", "TEXASGRILL", 2.55, 150, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Baguette", "FrenchSoul", 3.55, 300, 50,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Bread", "Gold Oven", 2.00, 350, 350,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Chips", "TEXASGRILL", 2.00, 150, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Flour", "FrenchSoul", 3.56, 450, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Noodles", "Shaika", 2.56, 500, 150,TypeofQuantity.GRAMS, tmp, t));
				t.add(Main.product.getFeatures(0));				
				Main.product.add(new Product("Beans", "TEXASGRILL", 2.00, 500, 150,TypeofQuantity.GRAMS, tmp, t));
				t.add(Main.product.getFeatures(1));
				Main.product.add(new Product("Black Olives", "Terre Sicialiane", 4.00, 500, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Boleto Mushrooms", "MNM",5.00, 250, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Chilli Peppers", "MNM", 4.00, 100, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Coffe", "Arabica 2000", 2.00, 350, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Green Olives", "Terre Sicialiane", 3.50, 500, 150,TypeofQuantity.GRAMS, tmp, t));
				
			}
			if(tmp.getName().equals("Frozen Food")) {
				TreeSet<String> t = new TreeSet<String>();
				t.clear();
				Main.product.add(new Product("Ice Cream Cone", "Saints Sweets", 2.99, 4, 150,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Chocolate Ice Cream", "Saints Sweets", 2.99, 4, 150,TypeofQuantity.PIECES, tmp, t));
			}			
			if(tmp.getName().equals("Selfcare")) {
				TreeSet<String> t = new TreeSet<String>();
				t.clear();
				Main.product.add(new Product("Beard Foam", "MNM", 3.14, 1, 150,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Liquid Soap", "Prada", 2.67, 1, 150,TypeofQuantity.PIECES, tmp, t));
			}
			if(tmp.getName().equals("Alcohol")) {
				TreeSet<String> t = new TreeSet<String>();
				t.add(Main.product.getFeatures(2));
				Main.product.add(new Product("Beer bottles", "Bayern B", 4.44, 6, 150,TypeofQuantity.PIECES, tmp, t));
			}
			if(tmp.getName().equals("Vegetables")) {
				TreeSet<String> t = new TreeSet<String>();
				t.add(Main.product.getFeatures(0));
				t.add(Main.product.getFeatures(1));
				t.add(Main.product.getFeatures(2));
				t.add(Main.product.getFeatures(3));
				Main.product.add(new Product("Carrots", "Old Farmer", 2.99, 250, 150,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Eggplant", "Old Farmer", 3.56, 450, 150,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Fennels", "Old Farmer", 3.00, 250, 150,TypeofQuantity.PIECES, tmp, t));
				Main.product.add(new Product("Garlic", "Old Farmer", 1.99, 50, 150,TypeofQuantity.GRAMS, tmp, t));
				Main.product.add(new Product("Lettuce", "Old Farmer", 1.99, 300, 150,TypeofQuantity.GRAMS, tmp, t));
			}
			if(tmp.getName().equals("Drinks")) {
				TreeSet<String> t = new TreeSet<String>();
				t.add(Main.product.getFeatures(0));
				t.add(Main.product.getFeatures(2));
				t.add(Main.product.getFeatures(3));
				Main.product.add(new Product("Coke Cans", "C&C", 2.99, 6, 150,TypeofQuantity.PIECES, tmp, t));
				
				
			}
			
			
		}
		
		Main.department.write();
		Main.product.write();		
	}
	
	private void createDepartments() {
		Main.department.add(new Department("Fruit"));
		Main.department.add(new Department("Vegetables"));
		Main.department.add(new Department("Sweets"));
		Main.department.add(new Department("Meat"));
		Main.department.add(new Department("Fish"));
		Main.department.add(new Department("Alcohol"));
		Main.department.add(new Department("Drinks"));
		Main.department.add(new Department("Self Care"));
		Main.department.add(new Department("Various Food"));
		Main.department.add(new Department("Frozen Food"));
		Main.department.add(new Department("Cheese and Milk"));
		
		for(Department f : Main.department)
			Main.depmap.put(f, new DepartmentGui(f));
	}
	
	private void checkPath() {
		File onlinemarket = new File(Main.onlinemarket),path = new File(Main.path), media = new File(Main.mediapath), store = new File(Main.store), editors = new File(Main.path + "/Editors.txt"),
				customers = new File(Main.path + "/Customers.txt"), departments = new File(Main.store + "/Departments.txt"),
				products = new File(Main.store + "/Products.txt"), FC = new File(Main.store + "/FC.txt"),
				payments = new File(Main.store + "/Payments.txt"), order = new File(Main.store + "/Orders.txt");
		try {
			if((!onlinemarket.exists() && !onlinemarket.mkdir()) || (!path.exists() && !path.mkdir()) || (!media.mkdir() && !media.exists()) || 
					(!store.exists() && !store.mkdir()) || (!editors.exists() && !editors.createNewFile()) ||
					(!customers.exists() && !customers.createNewFile()) || (!departments.exists() && !departments.createNewFile()) ||
					(!products.exists() && !products.createNewFile()) || (!FC.exists() && !FC.createNewFile()) ||
					(!payments.exists() && !payments.createNewFile()) || (!order.exists() && !order.createNewFile())) {
				Alert a = new Alert(Alert.AlertType.NONE, "Path wrong" , ButtonType.CLOSE);
				a.initModality(Modality.APPLICATION_MODAL);
				a.initOwner(this);
				a.showAndWait();
				Platform.exit();
				System.exit(0);
			}
		}	catch(IOException e) {
			Alert a = new Alert(Alert.AlertType.NONE, "Path wrong" , ButtonType.CLOSE);
			a.initModality(Modality.APPLICATION_MODAL);
			a.initOwner(this);
			a.showAndWait();
			Platform.exit();
			System.exit(0);
		}
		
	}
	
	
}
