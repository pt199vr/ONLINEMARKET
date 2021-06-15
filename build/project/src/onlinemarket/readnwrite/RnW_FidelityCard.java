package onlinemarket.readnwrite;


import onlinemarket.fidelitycard.FidelityCard;

public class RnW_FidelityCard extends RnW<FidelityCard>{
	private static final long serialVersionUID = 15L;
	
	public RnW_FidelityCard(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "FidelityCards :\n";
		int i = 0;
		for(FidelityCard j: this) {
			i++;
			r += i + ")" +j.toString()+"\n";
			
		}
		return r +"\n";
	}
	
	public void Object() {
		System.out.println("FidelityCard");
	}
}

