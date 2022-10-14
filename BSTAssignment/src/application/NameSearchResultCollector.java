package application;

import java.util.ArrayList;

public class NameSearchResultCollector implements BSTCollector<Integer, Person>{
	
	private int targetLength;
	private ArrayList<Person> results;
	private ComparisonType comparisonType;
	private String nameType;

	public NameSearchResultCollector(String nameType, ComparisonType CT) {
		this.comparisonType = CT;
		this.nameType = nameType;
	}

	@Override
	public ArrayList<Person> collect(Integer target, BSTNode<Person> node) {
		this.targetLength = target;
		this.results = new ArrayList<Person>();
		find(node); 
		return results;
	}

	@Override
	public void find(BSTNode<Person> node) {
		
		// get the value of this node based on what name the user wants to compare lengths
		int nodeValue;
		
		if (nameType.equals("First Name")) {
			nodeValue = node.getValue().getFirstName().length(); // first name length
		} else if (nameType.equals("Last Name")) {
			nodeValue = node.getValue().getLastName().length(); // last name length
		} else {
			nodeValue = node.getValue().getFirstName().length() + node.getValue().getLastName().length(); // first name length + last name length
		}
		
		// check this node according to the desired comparison type
		if (comparisonType.equals(ComparisonType.EqualTo)) {
			if (nodeValue == this.targetLength) {
				results.add(node.getValue());
			}
		} else if (comparisonType.equals(ComparisonType.GreaterThan)) {
			if (nodeValue > targetLength) {
				results.add(node.getValue());
			}
		} else if (comparisonType.equals(ComparisonType.LessThan)) {
			if (nodeValue < targetLength) {
				results.add(node.getValue());
			}
		}
		
		
		// traverse the tree calling find on all nodes
		if (node.getRight() != null) {
			find(node.getRight());
		}

		if (node.getLeft() != null) {
			find(node.getLeft());
		}
		
	}
	
	

}
