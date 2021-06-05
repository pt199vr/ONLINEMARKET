package onlinemarket.actionsgui;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import onlinemarket.order.*;

public class ShowProducts extends AnchorPane{

	private Order order;
	
	
	public ShowProducts(Order order) {
		this.order = order;
	}
	
}
