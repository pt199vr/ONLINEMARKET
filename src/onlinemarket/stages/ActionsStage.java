package onlinemarket.stages;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import onlinemarket.Main;

import onlinemarket.actionsgui.*;
import onlinemarket.fidelitycard.FDCCreationGui;
import onlinemarket.fidelitycard.FDCViewGui;
import onlinemarket.order.CustomerOrderTable;
import onlinemarket.order.EditorOrderTable;
import onlinemarket.payment.PaymentMethodGui;

public class ActionsStage extends Stage{
	
	private BorderPane layout;
	
	private CreateDepGui create;
	private ModifyDepGui modify;
	private DeleteDepGui delete;
	private EditorProdCreationGui createProd;
	private EditorProdDeleteGui deleteProd;
	private EditorAccGui editor;
	private CreateEditorGui EdCreation;
	private EditorsTableGui editorsAcc;
	private CustomersTableGui customersAcc;
	private EditorOrderTable EditorOrders;
	
	
	private CustomerAccGui customer;
	private FDCCreationGui fdcc;
	private FDCViewGui fdcv;
	private PaymentMethodGui payment;
	private CustomerOrderTable CustomerOrders;
	
	private OrderRecGui orderRec;
	
	
	public ActionsStage(String s, EditorShopStageGui f) {
			
			Main.loadingstage.show();
			System.gc();
			
			create = new CreateDepGui(f);
			modify = new ModifyDepGui(f);
			delete = new DeleteDepGui(f);
			createProd = new EditorProdCreationGui(f);
			deleteProd = new EditorProdDeleteGui(f); 
			editor = new EditorAccGui(f);
			EdCreation = new CreateEditorGui(f);
			editorsAcc = new EditorsTableGui(f);
			customersAcc = new CustomersTableGui();
			
			EditorOrders = new EditorOrderTable(f);
			
			
			
			new Thread(() -> {
				
				layout = new BorderPane();
				
				
				
				if("create".equals(s)) {					
					layout.setCenter(create);
				}
				if("modify".equals(s)) {
					layout.setCenter(modify);
				}
				if("delete".equals(s)) {
					layout.setCenter(delete);
				}
				if("prodCreation".equals(s)) {
					layout.setCenter(createProd);
				}
				if("prodElimination".equals(s)) {
					layout.setCenter(deleteProd);
				}
				if("showaccount".equals(s)) {					
					layout.setCenter(editor);
				}
				if("EditorCreate".equals(s)) {
					layout.setCenter(EdCreation);
				}
				if("showeditors".equals(s)) {					
					layout.setCenter(editorsAcc);
				}
				if("showcustomers".equals(s)) {					
					layout.setCenter(customersAcc);
				}
				if("showorders".equals(s)) {					
					layout.setCenter(EditorOrders);
				}
				
				setTitle(Main.title);
				getIcons().add(Main.logo);
				setHeight(600);
				setWidth(800);
				setResizable(true);
				Platform.runLater(()->{
					setScene(new Scene(layout));
					show();
					Main.loadingstage.hide();
				});
				
				setOnCloseRequest(e -> {
					Main.shopstage.show();					
				});
				
			}).start();
			

	}
	
	public ActionsStage(String s, ShopStageGui f) {
		
		Main.loadingstage.show();
		System.gc();
		
		fdcc = new FDCCreationGui(f);
		fdcv = new FDCViewGui(f);
		payment = new PaymentMethodGui(f);
		customer = new CustomerAccGui(f);
		CustomerOrders = new CustomerOrderTable(f);
		
		new Thread(()->{
			layout = new BorderPane();
			
			if("payment".equals(s))
				layout.setCenter(payment);
			if("showaccount".equals(s))
				layout.setCenter(customer);
			if("fidelity".equals(s))
				layout.setCenter(fdcv);
			if("newFidelity".equals(s)) 
				layout.setCenter(fdcc);
			if("showOrders".equals(s)) 
				layout.setCenter(CustomerOrders);
			
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(600);
			setWidth(800);
			setResizable(true);
			Platform.runLater(()->{
				setScene(new Scene(layout));
				show();
				Main.loadingstage.hide();
			});
			
			setOnCloseRequest(e -> {
				Main.shopstage.show();					
			});
		
		}).start();
	
	}
	
	public ActionsStage(String s,CartStageGui t) {
		Main.loadingstage.show();
		System.gc();
		
		orderRec = new OrderRecGui(t);
		
		new Thread(() -> {
			layout = new BorderPane();
			if("orderRecap".equals(s))
				layout.setCenter(orderRec);
			
			setTitle(Main.title);
			getIcons().add(Main.logo);
			setHeight(600);
			setWidth(800);
			setResizable(true);
			Platform.runLater(()->{
				setScene(new Scene(layout));
				show();
				Main.loadingstage.hide();
			});
		
			setOnCloseRequest(e -> {
				Main.cartstage.show();					
			});
			
		}).start();
	}
}
