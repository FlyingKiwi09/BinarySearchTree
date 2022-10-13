package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TreeModel {

	private static TreeModel instance;
	private BinarySearchTree<Person> ageTree; 
	private BinarySearchTree<Person> firstNameTree;
	private BinarySearchTree<Person> lastNameTree;
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
		ageTree = new BinarySearchTree<Person>(newPerson, ageComparator);
		Person p2 = new Person("Sue", "Bags", 88);
		ageTree.add(p2);
		Person p3 = new Person("Bob", "Bobby", 14);
		ageTree.add(p3);
		Person p4 = new Person("Sam", "Bobby", 14);
		ageTree.add(p4);
		Person p5 = new Person("Sam", "Bobby", 77);
		ageTree.add(p5);
		Person p6 = new Person("Anne", "Bobby", 77);
		ageTree.add(p6);
		ageTree.output(output);
	}



// getters and setters
	public BinarySearchTree<Person> getAgeTree() {
		return ageTree;
	}

	public void setAgeTree(BinarySearchTree<Person> ageTree) {
		this.ageTree = ageTree;
	}

	public BinarySearchTree<Person> getFirstNameTree() {
		return firstNameTree;
	}

	public void setFirstNameTree(BinarySearchTree<Person> firstNameTree) {
		this.firstNameTree = firstNameTree;
	}

	public BinarySearchTree<Person> getLastNameTree() {
		return lastNameTree;
	}

	public void setLastNameTree(BinarySearchTree<Person> lastNameTree) {
		this.lastNameTree = lastNameTree;
	}

	public ObservableList<String> getOutput() {
		return output;
	}

	public void setOutput(ObservableList<String> output) {
		this.output = output;
	}
	
	

}
