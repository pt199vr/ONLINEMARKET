package onlinemarket.shop;

import onlinemarket.readnwrite.RnW;

public class Shop extends RnW{
	//serialID
	transient private ShopGui gui;
	
	public final static String[] features = {"Gluten free","Bio","Milk Free"};
	
	public Shop(String filepath) {
		super(filepath);
	}
	
	

	@Override
	public void errorReading() {
		// TODO Auto-generated method stub
		
	}
	
	
}
