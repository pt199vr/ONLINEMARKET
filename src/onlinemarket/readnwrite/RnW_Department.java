package onlinemarket.readnwrite;

import onlinemarket.departments.Department;


public class RnW_Department extends RnW<Department>{
	private static final long serialVersionUID = 13L;
	
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
	
	public void Object() {
	}
}
