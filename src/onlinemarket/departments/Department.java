package onlinemarket.departments;

import java.io.Serializable;
import java.util.Comparator;

import onlinemarket.Main;
import onlinemarket.product.ProdComp;
public class Department implements Comparable<Department>, Comparator<Department>, Serializable{
	private static final long serialVersionUID = 7L;
	
	transient private DepartmentGui gui = new DepartmentGui();
	
	private String name;
	public final ProdComp prod;
	
	public Department(String name) {
		this(name,new ProdComp());
	}
	
	public Department(String name, ProdComp prod) {
		this.name=name;
		this.prod=prod;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}

	public DepartmentGui getGui() {
		return gui;
	}
	
	@Override
	public int compare(Department o1, Department o2) {
		return o1.compareTo(o2);
	}

	@Override
	public int compareTo(Department o) {
		return name.compareToIgnoreCase(o.name);
	}
	@Override
	public boolean equals(Object other) {
		return other instanceof Department && ((Department)other).name.equalsIgnoreCase(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return String.format("%s : %s",name,prod);
	}
}
