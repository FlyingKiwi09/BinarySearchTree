package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TreeModel {

	private static TreeModel instance;
	private BinarySearchTree<Integer, Person> ageTree; 
	private BinarySearchTree<String, Person> firstNameTree;
	private BinarySearchTree<String, Person> lastNameTree;
	private ObservableList<String> output;
	
	// singleton pattern
	private TreeModel() {
		 output = FXCollections.observableArrayList();
	}
	
	public static TreeModel getInstance() {
		if (instance == null) {
			instance = new TreeModel();
		}
		return instance;
	}
	
	public void ageTreeTest() {
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
		ageTree.output(output);
	}



// getters and setters
	public BinarySearchTree<Integer, Person> getAgeTree() {
		return ageTree;
	}

	public void setAgeTree(BinarySearchTree<Integer, Person> ageTree) {
		this.ageTree = ageTree;
	}

	public BinarySearchTree<String, Person> getFirstNameTree() {
		return firstNameTree;
	}

	public void setFirstNameTree(BinarySearchTree<String, Person> firstNameTree) {
		this.firstNameTree = firstNameTree;
	}

	public BinarySearchTree<String, Person> getLastNameTree() {
		return lastNameTree;
	}

	public void setLastNameTree(BinarySearchTree<String, Person> lastNameTree) {
		this.lastNameTree = lastNameTree;
	}

	public ObservableList<String> getOutput() {
		return output;
	}

	public void setOutput(ObservableList<String> output) {
		this.output = output;
	}
	
	

}
