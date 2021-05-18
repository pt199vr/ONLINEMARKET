
package onlinemarket.actionsgui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import onlinemarket.Main;
import onlinemarket.account.EditorAccount;
import onlinemarket.account.Email;
import onlinemarket.stages.ActionsStage;
import onlinemarket.stages.EditorShopStageGui;

public class EditorsTableGui extends AnchorPane{
	
	private EditorAccount SelEditor;
	
	@FXML
	private TableView<EditorAccount> editors;
	@FXML
	private TableColumn<EditorAccount,String> nameCol,surnameCol,IDCol,RoleCol;
	@FXML
	private TableColumn<EditorAccount,Email> mailCol;
	@FXML
	private TableColumn<EditorAccount,Long> CelCol;
	
	@FXML
	private MenuItem CreateEditor, ModifyEditor,DeleteEditor;
	
	public EditorsTableGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditorsAccounts.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		
		editors = new TableView<EditorAccount>();
		Main.editoraccount.read();
		
		mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		CelCol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
		IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		
		CreateEditor.setOnMenuValidation(e-> openCreation(f));
		
		
		editors.setItems(FXCollections.observableArrayList(Main.editoraccount));
		editors.getColumns().forEach(c ->{
			c.setEditable(false);
		});
		editors.getSelectionModel().getSelectedItems().addListener(
				(ListChangeListener.Change<? extends EditorAccount> change)-> select(change.getList()));
		if(SelEditor != null)
			editors.getSelectionModel().select(SelEditor);
	}

	private void select(ObservableList<? extends EditorAccount> list) {
		SelEditor = list.get(0);
		ModifyEditor.setDisable(false);
		if(Main.editoraccount.size() > 1)
			DeleteEditor.setDisable(false);
	}

	private void openCreation(EditorShopStageGui f) {
		Main.loadingstage.show();
		Main.actionstage = new ActionsStage("EditorCreate",f);
		Main.loadingstage.hide();
	}
}
