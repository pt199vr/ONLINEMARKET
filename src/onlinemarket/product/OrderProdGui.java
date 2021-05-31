package onlinemarket.product;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import onlinemarket.Main;
import onlinemarket.stages.CartStage;
import onlinemarket.stages.OrderStage;

public class OrderProdGui extends ProductGui{
	
	@FXML
	private ImageView prodImg;
	@FXML
	private Label NameL,BrandL,priceL,NumberL,TotalPriceL;

	public OrderProdGui(Product product) {
		super(new FXMLLoader(OrderProdGui.class.getResource("orderProdGui.fxml")), product);
		
		NameL.setText(product.getName());
		BrandL.setText(product.getBrand());
		priceL.setText(product.getPrice().toString());
		Integer num =((CartStage)Main.cartstage).getCartGui().getCart().getProducts().get(product);
		NumberL.setText(num.toString());
		Double totl = num * product.getPrice();
		TotalPriceL.setText(totl.toString());
		
		prodImg.setImage(defaultIMG);
		
	}

}
