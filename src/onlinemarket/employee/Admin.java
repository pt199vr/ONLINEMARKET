package onlinemarket.employee;

import java.util.ArrayList;
import java.util.Arrays;

import onlinemarket.RWS.RNW_PERSON;
import onlinemarket.employee.Employee;
import onlinemarket.person.Email;
import onlinemarket.person.Password;

public class Admin extends RNW_PERSON{
	private static final long serialVersionUID = 17L;
	
	private final Employee admin;
	
	public Admin(String filepath) {
		super(filepath);
		admin = new Employee( 0, new ArrayList<String>(Arrays.asList("market")) ,"admin", "admin", new Email("admin@onlinemarket.com"), new Password("password"), 123456789L);
	}	
	
	
	@Override
	protected void errorReading() {
		add(admin);
	}
	
}
