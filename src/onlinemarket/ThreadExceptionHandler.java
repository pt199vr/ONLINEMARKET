package onlinemarket;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import java.util.concurrent.atomic.AtomicBoolean;


public class ThreadExceptionHandler {
	private final AtomicBoolean exception;
	
	public ThreadExceptionHandler() {
		exception = new AtomicBoolean(false);
		
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> Platform.runLater(() -> showAlert(t, e)));;
		Thread.currentThread().setUncaughtExceptionHandler(this::showAlert);
	}
	
	private void showAlert(Thread t, Throwable e) {
		if(exception.get() == true)
			return;
		
		exception.set(true);
		e.printStackTrace();
		
		Alert alert = new Alert(Alert.AlertType.NONE, "Execution Error\n" + e.toString() + "\n Do you want to close the program?", ButtonType.YES, ButtonType.NO);
		
		try {
			
			alert.initOwner(Main.loginstage);
			
			
			Main.loginstage.hide();
		}catch(NullPointerException f) {}
		
		if(alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
			Platform.exit();
			System.exit(0);
		}
		
		try {
			
			Main.loginstage.show();
		}catch(NullPointerException f) {}
		
		exception.set(false);
		
	}
}
