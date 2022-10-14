package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TreeModel {

	private static TreeModel instance;
	private BinarySearchTree<Person> ageTree; 
	private BinarySearchTree<Person> firstNameTree;
	private BinarySearchTree<Person> lastNameTree;
	private BinarySearchTree<Person> idTree;
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
	
	// print method filters user input and calls appropriate print method on approriate tree
	public void print (String treeType, String printType) {
		output.clear();
		// print calls on ageTree
		if (treeType.equals("Age")) {
			if (printType.equals("In-Order Depth First")) {
				ageTree.printIODF(output);
			} else if (printType.equals("Breath First")) {
				ageTree.printBF(output);
			} else if (printType.equals("Pre-Order Depth First")){
				ageTree.printPreDF(output);
			} else if (printType.equals("Post-Order Depth First")){
				ageTree.printPostDF(output);
			}
		}
		
		// print calls on firstNameTree
		if (treeType.equals("First Name")) {
			if (printType.equals("In-Order Depth First")) {
				firstNameTree.printIODF(output);
			} else if (printType.equals("Breath First")) {
				firstNameTree.printBF(output);
			} else if (printType.equals("Pre-Order Depth First")){
				firstNameTree.printPreDF(output);
			} else if (printType.equals("Post-Order Depth First")){
				firstNameTree.printPostDF(output);
			}
		}
		
		// print calls on lastNameTree
		if (treeType.equals("Last Name")) {
			if (printType.equals("In-Order Depth First")) {
				lastNameTree.printIODF(output);
			} else if (printType.equals("Breath First")) {
				lastNameTree.printBF(output);
			} else if (printType.equals("Pre-Order Depth First")){
				lastNameTree.printPreDF(output);
			} else if (printType.equals("Post-Order Depth First")){
				lastNameTree.printPostDF(output);
			}
		}
	}
	
	// search method filters user input and calls search on appropriate tree
	public void search(String searchType, String searchString) {
		output.clear();
		ArrayList<Person> results = new ArrayList<Person>();
		
		if (searchType.equals("Age")) {
			AgeResultCollector ARG = new AgeResultCollector();
			results = ARG.collect(Integer.parseInt(searchString), ageTree.getRoot());
		}
		
		if (searchType.equals("First Name")) {
			FirstNameResultCollector FNRC = new FirstNameResultCollector();
			results = FNRC.collect(searchString, firstNameTree.getRoot());
		}
		
		if (searchType.equals("Last Name")) {
			LastNameResultCollector LNRC = new LastNameResultCollector();
			results = LNRC.collect(searchString, lastNameTree.getRoot());
		}
		
		for (Person p : results) {
			output.add(p.toString());
		}
	}
	
	// length search filters user input and calls appropriate lengthSearch on appropriate tree
	public void lengthSearch(String nameType, String comparisonType, String length) {
		output.clear();
		
		ComparisonType CT;
		if (comparisonType.equals("Greater Than")) {
			CT = ComparisonType.GreaterThan;
		} else if (comparisonType.equals("Less Than")) {
			CT = ComparisonType.LessThan;
		} else {
			CT = ComparisonType.EqualTo;
		}
		
		ArrayList<Person> results = new ArrayList<Person>();
		NameSearchResultCollector NSRC = new NameSearchResultCollector(nameType, CT);
		results = NSRC.collect(Integer.parseInt(length), firstNameTree.getRoot());
		
		for (Person p : results) {
			output.add(p.toString());
		}
	}
	
	// delete node by ID
	public void deleteNode(String nodeID) {
		
		int ID = Integer.parseInt(nodeID);
		IDResultCollector IDRC = new IDResultCollector();
		
		ArrayList<Person> person = IDRC.collect(ID, idTree.getRoot());
		
		Person toDelete = null;
		
		if (!person.isEmpty()) {
			toDelete = person.get(0);
		}
		
		if (toDelete != null) {
			ageTree.delete(toDelete);
			firstNameTree.delete(toDelete);
			lastNameTree.delete(toDelete);
			idTree.delete(toDelete);
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

	public BinarySearchTree<Person> getIdTree() {
		return idTree;
	}

	public void setIdTree(BinarySearchTree<Person> idTree) {
		this.idTree = idTree;
	}
	
	
	

}
