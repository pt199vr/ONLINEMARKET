package onlinemarket.RWS;

public class RNW_USER extends RNW_PERSON{
	private static final long serialVersionUID = 19L;
	
	public RNW_USER(String filepath) {
		super(filepath);
	}
	
	@Override
	protected void errorReading() {
		
	}
}
