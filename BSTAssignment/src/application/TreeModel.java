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
	

	public void print (String treeType, String printType) {
		output.clear();
		if (treeType.equals("Age")) {
			if (printType.equals("In-Order Depth First")) {
				ageTree.printIODF(output);
			} else if (printType.equals("Breath First")) {
//				ageTree.printBF();
			} else if (printType.equals("Pre-Order Depth First")){
//				ageTree.printPreDF();
			} else if (printType.equals("Pre-Order Depth First")){
//				ageTree.printPostDF();
			}
		}
		
		if (treeType.equals("First Name")) {
			
		}
		
		if (treeType.equals("Last Name")) {
			
		}
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
