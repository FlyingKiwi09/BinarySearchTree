package application;

import java.util.Comparator;

public class PersonIDComparator implements Comparator<Person>{

	@Override
	public int compare(Person p1, Person p2) {
		// TODO Auto-generated method stub
		return p2.getID() - p1.getID();
	}

}
