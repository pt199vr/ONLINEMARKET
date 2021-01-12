package onlinemarket.RWS;

import onlinemarket.readnwrite.RnW;
import onlinemarket.person.Person;

public abstract class RNW_PERSON extends RnW<Person>{
	private static final long serialVersionUID = 15L;
	
	protected abstract void errorReading();
	
	public RNW_PERSON(String filepath) {
		super(filepath);
	}
	
	public Person login(Person person) {
		Person[] array = toArray(new Person[size()]);
		int h, i = 0, l = array.length -1;
		
		while(i < l) {
			h = (i + l) / 2;
			if(person.login(array[h]))
				return (Person) array[h];
			if(person.compareTo(array[h]) > 0)
				i = h + 1;
			else
				l = h - 1;
		}
		return null;
		
	}
	
	public String toString() {
		String ret = "\n";
		for(Person t : this)
			ret += t;
			
		return ret + "\n";
	}
	
}
