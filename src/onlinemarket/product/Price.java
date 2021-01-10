package onlinemarket.product;

public class Price implements Comparable<Price>{
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
