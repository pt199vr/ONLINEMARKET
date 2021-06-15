package onlinemarket.account;
import java.io.Serializable;

public class EditorAccount extends Account{
	private static final long serialVersionUID = 21L;
	private String id;
	private Role role;
	
	public EditorAccount(String id, String name, String surname, Email email, Password password, Long phonenumber, Address address, Role role) {
		super(name,  surname,  email,  password, phonenumber, address);
		this.id = id;	
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
	
	public String getId() {
		return id;
	}
	
	public String toString() {
		String s = role.toString() + " " + getId() + "||" + getName()+ "||" + getSurname() + "||" + getEmail().toString() + "||" + getPassword().toString() + "||" + getPhoneNumber() + "||" + address.toString();
		return s; 
	}
	

}
