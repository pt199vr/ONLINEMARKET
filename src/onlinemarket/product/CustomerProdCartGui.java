package onlinemarket.product;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import onlinemarket.Main;
import onlinemarket.cart.Cart;
import onlinemarket.stages.CartStage;
import onlinemarket.stages.CartStageGui;
import onlinemarket.stages.ShopStage;

public class CustomerProdCartGui extends ProductGui{
	
	@FXML
	private ImageView prodIMG;
	@FXML
	private Label NameL,BrandL,quantityL,NumberL,singlePriceL,totalPriceL;
	@FXML
	private Button removeB;

	
	private Cart cart;
	private CartStageGui cartgui;
	
	private String getBetterPath() {
		return String.format("%s/%s_%s.jpg", Main.mediapath, product.getName(), product.getBrand());
	}

	private String getPathImg() {
		return String.format("%s/%s.jpg", Main.mediapath, product.getName());
	}
	
	public CustomerProdCartGui( Product product) {
		
		super(new FXMLLoader(CustomerProdCartGui.class.getResource("prodCartView.fxml")), product);
		
		cart = ((CartStage)Main.cartstage).getCartGui().getCart();
		cartgui = ((CartStage)Main.cartstage).getCartGui();
		
		if((new File(getBetterPath()).exists())) {
			prodIMG.setImage(new Image("file:" + getBetterPath()));
		}
		else if((new File(getPathImg()).exists())) {
			prodIMG.setImage(new Image("file:" + getPathImg()));
		}
		else 
			prodIMG.setImage(ProductGui.defaultIMG);
		
		NameL.setText(product.getName());
		BrandL.setText(product.getBrand());
		quantityL.setText(product.getQuantity().toString());
		NumberL.setText(cart.getProducts().get(product).toString());
		singlePriceL.setText(product.getPrice().toString());
		
		
		Float stock = Integer.parseInt(NumberL.getText()) * Float.parseFloat(singlePriceL.getText());
		totalPriceL.setText(stock.toString());
		
		
		removeB.setOnAction(e -> {
			cart.remove(product);
			cartgui.refresh();
		});
		
		
	}

}