package onlinemarket.account;

public class EditorAccount extends Account{
	
	private String id;
	
	public EditorAccount(String id, String name, String surname, Email email, Password password, Long phonenumber, Address address) {
		super(name,  surname,  email,  password, phonenumber, address);
		this.id = id;		
	}
	
	public String getId() {
		return id;
	}
	
	public String toString() {
		String s = getId() + "||" + getName()+ "||" + getSurname() + "||" + getEmail().toString() + "||" + getPassword().toString() + "||" + getPhoneNumber() + "||" + address.toString();
		return s; 
	}
	

}
