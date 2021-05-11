package onlinemarket.departments;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;

import onlinemarket.Main;

public class EditorDeleteDepGui {
	@FXML
	private ChoiceBox<String> DepChoice;
	@FXML
	private Label wL;
	
	@FXML
	private Button DepDelete;
	
	public EditorDeleteDepGui() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepDelete.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		init();
		
		DepDelete.setOnAction(e -> delete());
		DepDelete.setDisable(Main.department.size() == 1);
		DepDelete.setOnKeyPressed(keyEvent -> {
			if(keyEvent.getCode() == KeyCode.ENTER)
				delete();
		});
	}
	
	private void init() {
		DepChoice.getItems().clear();
		for(Department d: Main.department)
			DepChoice.getItems().add(d.getName());
		
		DepChoice.getSelectionModel().selectFirst();
	}
	
	@FXML
	private void delete() {

		if(new Alert(Alert.AlertType.NONE, "If you delete this repartment you will delete all the products!\n\nContinue?",ButtonType.YES,ButtonType.NO).showAndWait().orElse(ButtonType.NO) == ButtonType.NO)
			return;
		
		String delDep = DepChoice.getValue();
		/*
		if(!Main.department.remove(Main.department.get(delDep))) {
			wL.setText("Error");
			return;
		}
		*/
		init();
		
		DepDelete.setDisable(Main.department.size() == 1);
		wL.setText("Finished!");
		new Alert(Alert.AlertType.NONE, String.format("Department %s has been deleted", delDep),ButtonType.OK).showAndWait().orElse(ButtonType.OK);
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				Main.department.write();
			}
		}).start();
	}

}
