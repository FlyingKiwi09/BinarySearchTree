package application;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person>{

	public PersonAgeComparator() {
		
	}

	@Override
	public int compare(Person p1, Person p2) {
		
		return p2.getAge() - p1.getAge();
		
//		// check for age difference
//		if (p1.getAge() < p2.getAge()) {
//			return -1;
//		} else if (p1.getAge() > p2.getAge()) {
//			return 1;
//		}
		
//		// check for firstName difference
//		if (p1.getFirstName().compareTo(p2.getFirstName()) < 0) {
//			return -1;
//		} else if (p1.getFirstName().compareTo(p2.getFirstName()) > 0) {
//			return 1;
//		}
//		
//		// check for lastName difference
//		if (p1.getLastName().compareTo(p2.getLastName()) < 0) {
//			return -1;
//		} else if (p1.getLastName().compareTo(p2.getLastName()) > 0) {
//			return 1;
//		}
//		
//		// if age and names are ==, compare by unique ID
//		if (p1.getID() < p2.getID()) {
//			return -1;
//		} else {
//			return 1;
//		}
	}

}
