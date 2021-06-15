package onlinemarket.readnwrite;


import onlinemarket.account.*;

public class RnW_EditorAccount extends RnW<EditorAccount>{
	private static final long serialVersionUID = 12L;

	public RnW_EditorAccount(String filepath) {
		super(filepath);
	}
	
	public String toString() {
		String r = "Editors Accounts:\n";
		int j = 0;
		for(EditorAccount i : this) {
			j++;
			r += j + ") " + i.toString() + "\n";
		}
		
		return r + "\n";
	}
	
	public void Object() {
		System.out.println("Editor");
	}
}
