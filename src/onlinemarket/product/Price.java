package onlinemarket.product;

import java.io.Serializable;


public class Price implements Comparable<Price>, Serializable{
	private static final long serialVersionUID = 12L;
	private final Float price;
	
	public Price(Float price) throws NumberFormatException{
		if(price < 0.00)
			throw new NumberFormatException("Product price must be greater than 0!");
		
		this.price = price;
	}
	
	
	
	public Float getPrice() {
		return price;
	}
	
	
	public Price(Integer price) throws NumberFormatException{
		this(price.floatValue());
	}
	
	public Price(String price) throws NumberFormatException{
		this(Float.parseFloat(price));
	}
	
	@Override
	public String toString() {
		return String.format("%.2f€", price);
	}
	
	@Override
	public int compareTo(Price other) {
		return price.compareTo(other.getPrice());
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Price && ((Price) other).getPrice().equals(price);
	}
	
	
}
