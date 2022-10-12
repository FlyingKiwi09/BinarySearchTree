package application;

public class Test {

	public BinarySearchTree<Integer, Person> ageTree; 
	
	public Test() {
		// TODO Auto-generated constructor stub
		PersonAgeComparator ageComparator = new PersonAgeComparator();
		ageTree = new BinarySearchTree<Integer, Person>(13, new Person("Alina", "Hookway", 13), ageComparator);
	}

}
