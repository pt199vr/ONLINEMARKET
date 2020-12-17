package onlinemarket.login;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import onlineMarket.Main;
import onlineMarket.persons.person.account.CreateAccount;
import onlineMarket.persons.users.user.account.UserCreateAccount;

public class LoginStage extends Stage{
	private BorderPane layout;
	
	private CreateAccount createAccount;
	public LoginGui loginGui;
	
	public void showLogin(){
		layout.setCenter(loginGui);
	}
	
	public void showRegistration(){
		layout.setCenter(createAccount);
	}
	
	public LoginStage() {
		Main.loadingStage.show();
		if(Main.mainStage != null) {
			Main.mainStage.close();
			Main.mainStage = null;
			System.gc();
		}
		
		new Thread(() -> {
		    layout = new BorderPane();
		    loginGui = new LoginGui();
		    createAccount = new UserCreateAccount();
		    	
			showLogin();
		    	
		    setTitle(Main.title);
		    getIcons().add(Main.icon);
			setHeight(420);
			setWidth(600);
			setResizable(false);
			setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
				
			Platform.runLater(() -> {
		        setScene(new Scene(layout));
		        show();		
		        Main.loadingStage.hide();
			}); 
		}).start();
	}
}
