package onlinemarket.employee;

import java.util.ArrayList;
import onlinemarket.person.*;

public class Employee extends Person{
	
	private final Integer id;
	private ArrayList<String> role;
	
	public Employee(Integer id, ArrayList<String> string, String name, String surname, Email email, Password password, Long phonenumber) {
		super(name, surname, email, password, phonenumber);
		
		this.id = id;
		this.role = string;
	}
	
	public Integer getId() {
		return id;
	}
	
	public ArrayList<String> getRoles(){
		return role;
	}
	
	public void set(String name, String surname, Email email, Password password, Long phonenumber, ArrayList<String> string) {
		super.set(name, surname, email, password, phonenumber);
		
		this.role = string;
	}
		
	public int hashCode() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", name, surname, email, id, role);
	}
	
	@Override
	public int compareTo(Person other) {
		return ((Employee) other).getId().compareTo(id);
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Employee && ((Employee) other).id.equals(this.id);
	}
	
	@Override
	public void setGui() {
		gui = new EmployeeGui();
	}
}
