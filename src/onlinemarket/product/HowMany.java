package onlinemarket.product;

public class HowMany implements Comparable<HowMany>{
	
	private final Float value;
	private final String unit;
	
	public HowMany(Float value, String unit) throws NumberFormatException{
		if(value < 0.00)
			throw new NumberFormatException("Product cost must be greater than 0!");
		
		this.value = value;
		this.unit = unit;
	}
	
	public Float getValue() {
		return value;
	}
	
	public String getUnit() {
		return unit;
	}
	
	@Override
	public String toString() {
		return String.format("%.2f %s", value, unit);
	}
	
	@Override
	public int compareTo(HowMany other) {
		return value.compareTo(other.getValue());
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof HowMany && ((HowMany) other).getUnit().equals(unit) && ((HowMany) other).getValue().equals(value);

	}
		
	
}
