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
import javafx.stage.Modality;
import onlinemarket.Main;
import onlinemarket.account.Address;
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
	private TableColumn<EditorAccount,Address> AddressCol;
	@FXML
	private Menu CreateEditor, ModifyEditor,DeleteEditor;
	@FXML
	private Label createL,modifyL,deleteL;
	
	public EditorsTableGui(EditorShopStageGui f) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditorsAccounts.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		Main.editoraccount.read();
		
		mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		CelCol.setCellValueFactory(new PropertyValueFactory<EditorAccount, Long>("PhoneNumber"));
		IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
		AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		ObservableList<EditorAccount> data = FXCollections.observableArrayList();	
		
		for(EditorAccount t : Main.editoraccount) {
			data.add(t);
		}
		editors.setItems(data);
		
		EditorShopStageGui t = new EditorShopStageGui(SelEditor);
		createL.setOnMouseClicked(e-> openCreation(f));
		deleteL.setOnMouseClicked(e -> delete(f));
		modifyL.setOnMouseClicked(e -> mod(t));
		editors.getSelectionModel().getSelectedItems().addListener(
				(ListChangeListener.Change<? extends EditorAccount> change) -> select());
		
		if(SelEditor != null) {
			editors.getSelectionModel().select(SelEditor);
		}
	}

	private void delete(EditorShopStageGui f) {
		if(Main.editoraccount.size() > 1) {
			SelEditor = editors.getSelectionModel().getSelectedItem();
			editors.getItems().remove(SelEditor);
			Main.editoraccount.remove(SelEditor);
			Main.editoraccount.write();
			if(SelEditor.equals(f.getAccount())) {
				Main.loadingstage.show();
				Main.actionstage.hide();
				Main.loginstage.show();
				Main.loadingstage.hide();
				}
		}
		else {
			Alert a = new Alert(Alert.AlertType.NONE, "There must be at least 1 Editor" , ButtonType.CLOSE);
			a.initModality(Modality.APPLICATION_MODAL);
			a.showAndWait();
		}
		
	}
	private void mod(EditorShopStageGui f) {
		Main.loadingstage.show();
		Main.actionstage.hide();
		Main.actionstage = new ActionsStage("showaccount",f);
		Main.loadingstage.hide();
		
	}
	
	private void select() {		
		ModifyEditor.setDisable(false);
		DeleteEditor.setDisable(false);
	}

	private void openCreation(EditorShopStageGui f) {
		Main.loadingstage.show();
		Main.actionstage.hide();
		Main.actionstage = new ActionsStage("EditorCreate",f);
		Main.loadingstage.hide();
	}
}