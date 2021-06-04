package onlinemarket.product;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import onlinemarket.Main;
import onlinemarket.stages.ShopStage;

public class UserProdGui extends ProductGui{
	
	@FXML
	private Button AddCartB;
	@FXML
	private Label nameL,brandL,quantityWL,quantityL,priceQuantityL;
	@FXML
	private ImageView ProdImg;
	
	private Product product;
	

	public UserProdGui(Product p) {
		super(new FXMLLoader(UserProdGui.class.getResource("productUser.fxml")),p);
	
		product = p;
		
		ProdImg.setImage(defaultIMG);
		nameL.setText(p.getName());
		brandL.setText(p.getBrand());
		quantityL.setText("Reserves:  " + p.getNumber().toString());
		priceQuantityL.setText(p.getPrice().toString()+ " €");
		
		AddCartB.setOnAction(e->{
			((ShopStage) Main.shopstage).getGui().getCart().add(p);
		});
		
		
		if(p.getNumber() == 0) { 
			AddCartB.setDisable(true);
		}
		
	}

	/*public Button getAdd() {
		return AddCartB;
	}*/
	
	public Product getProduct() {
		return product;
	}
	
}