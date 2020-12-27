package onlinemarket.person.user;

public class Address {
	
	private final String street, city;
	private final Integer cap, addressNumber;
	
	public Address(String street, String city, Integer addressNumber, Integer cap) {
		this.street = street;
		this.city = city;
		this.addressNumber = addressNumber;
		this.cap = cap;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	public Integer getCap() {
		return cap;
	}
	
	public Integer getAddressNumber() {
		return addressNumber;
	}
	
	
	public String toString() {
		return String.format("%s %s %s %s", street, addressNumber, cap, city);
	}
	
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Address) {
			Address tmp = (Address) other;
			
			if(this.street.equals(tmp.getStreet()) && this.city.equals(tmp.getCity()) && this.addressNumber.equals(tmp.getAddressNumber()) && this.cap.equals(getCap()))
				return true;
		}
		
		return false;
			
		
	}
	
}
