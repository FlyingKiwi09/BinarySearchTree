package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Test {

	public static BinarySearchTree<Integer, Person> ageTree; 
	public static ObservableList<String> testOutput = FXCollections.observableArrayList();
	
	public Test() {
		
	}
	
	public static void ageTreeTest() {
		PersonAgeComparator ageComparator = new PersonAgeComparator();
		Person newPerson = new Person("Alina", "Hookway", 33);
		ageTree = new BinarySearchTree<Integer, Person>(newPerson.getAge(), newPerson, ageComparator);
		Person p2 = new Person("Sue", "Bags", 88);
		ageTree.add(p2.getAge(), p2);
		Person p3 = new Person("Bob", "Bobby", 14);
		ageTree.add(p3.getAge(), p3);
		Person p4 = new Person("Sam", "Bobby", 14);
		ageTree.add(p4.getAge(), p4);
		Person p5 = new Person("Sam", "Bobby", 77);
		ageTree.add(p5.getAge(), p5);
		Person p6 = new Person("Anne", "Bobby", 77);
		ageTree.add(p6.getAge(), p6);
		ageTree.output(testOutput);
	}


	public ObservableList<String> getTestOutput() {
		return testOutput;
	}

	public void setTestOutput(ObservableList<String> testOutput) {
		this.testOutput = testOutput;
	}
	
	

}
