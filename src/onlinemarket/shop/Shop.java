package onlinemarket.shop;

import onlinemarket.departments.Department;
import onlinemarket.product.ProdComp;
import onlinemarket.readnwrite.RnW_Department;
import onlinemarket.stages.ShopStageGui;


public class Shop extends RnW_Department{
	
	private static final long serialVersionUID = 8L;
	
	transient private ShopStageGui gui;
	
	public final static String[] features = {"Gluten Free","Bio","Milk Free","Vegan"};
	
	public Shop(String filepath) {
		super(filepath);
	}	
	
	@Override
	public void errorReading() {
		create();
	}
	
	public Department get(String name) {
		for(Department dep : this)
			if(name.equals(dep.getName())) return dep;
		return null;
	}
	
	public void setGui() {
		gui = new ShopStageGui();
	}
	
	public ShopStageGui getGui() {
		return gui;
	}
	public void create() {
		Department dep = new Department("Fruits",new ProdComp());
		add(dep);
		dep = new Department("Meat",new ProdComp());
		add(dep);
		dep = new Department("Vegetables",new ProdComp());
		add(dep);
	}

	
	
	
}
