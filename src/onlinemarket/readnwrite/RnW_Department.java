package onlinemarket.readnwrite;

import onlinemarket.departments.Department;
import onlinemarket.stages.ShopStageGui;

public class RnW_Department extends RnW<Department>{
	private static final long serialVersionUID = 13L;
	
	transient private ShopStageGui gui;
	
	public RnW_Department(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Departments :\n";
		int i = 0;
		for(Department j: this) {
			i++;
			r += i + ")" +j.getName()+"\n";
			
		}
		return r +"\n";
	}

	@Override
	public void errorReading() {
	}
	
	public void setGui() {
		gui = new ShopStageGui();
	}

	public ShopStageGui getGui() {
		return gui;
	}

	public Department get(String name) {
		for(Department dep: this)
			if(name.equals(dep.getName()))
				return dep;
		return null;
	}

}
