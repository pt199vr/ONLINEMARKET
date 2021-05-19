package onlinemarket.stages;

import onlinemarket.*;
import onlinemarket.readnwrite.RnW_Account;
import onlinemarket.readnwrite.RnW_Department;
import onlinemarket.readnwrite.RnW_EditorAccount;
import onlinemarket.readnwrite.RnW_FidelityCard;
import onlinemarket.readnwrite.RnW_Payment;
import onlinemarket.readnwrite.RnW_Product;

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


public class StartingStage extends Stage{

	public StartingStage() {
	     Main.account.read();
		 Main.editoraccount.read();
		 Main.department.read();
		 Main.product.read();
		 Main.fidelitycard.read();
		 Main.payment.read();
		 
		 checkPath();
		 Main.loadingstage.show();
		 
	}
	
	private void checkPath() {
		File onlinemarket = new File(Main.onlinemarket),path = new File(Main.path), media = new File(Main.mediapath), store = new File(Main.store), editors = new File(Main.path + "/Editors.txt"),
				customers = new File(Main.path + "/Customers.txt"), departments = new File(Main.store + "/Departments.txt"),
				products = new File(Main.store + "/Products.txt"), FC = new File(Main.store + "/FC.txt"),
				payments = new File(Main.store + "/Payments.txt");
		try {
			if((!onlinemarket.exists() && !onlinemarket.mkdir()) || (!path.exists() && !path.mkdir()) || (!media.mkdir() && !media.exists()) || 
					(!store.exists() && !store.mkdir()) || (!editors.exists() && !editors.createNewFile()) ||
					(!customers.exists() && !customers.createNewFile()) || (!departments.exists() && !departments.createNewFile()) ||
					(!products.exists() && !products.createNewFile()) || (!FC.exists() && !FC.createNewFile()) ||
					(!payments.exists() && !payments.createNewFile())) {
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
