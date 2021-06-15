package onlinemarket;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import java.util.concurrent.atomic.AtomicBoolean;


public class ThreadExceptionHandler {
	
	
	public ThreadExceptionHandler() {		
		Thread.setDefaultUncaughtExceptionHandler((t, e) -> Platform.runLater(() -> Handler(t, e)));;
		Thread.currentThread().setUncaughtExceptionHandler(this::Handler);
	}
	
	private void Handler(Thread t, Throwable e) {
			
		e.printStackTrace();
		
		Alert alert = new Alert(Alert.AlertType.NONE, "Error in program execution:\n" + e.toString(), ButtonType.CLOSE);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();
		Platform.exit();
		System.exit(0);
		
		
	}
}
