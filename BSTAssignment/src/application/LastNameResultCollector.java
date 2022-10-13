package application;

import java.util.ArrayList;

public class LastNameResultCollector implements BSTCollector<String, Person>{
	
	private String target;
	private ArrayList<Person> results;
	
	public LastNameResultCollector() {
		this.results = new ArrayList<Person>();
	}


	@Override
	public ArrayList<Person> collect(String target, BSTNode<Person> node) {
		this.target = target;
		this.results = new ArrayList<Person>();
		find(node); 
		return results;
	}
	

	@Override
	public void find(BSTNode<Person> node) {

		String nodeValue = node.getValue().getLastName();
		int comparisonValue = nodeValue.compareTo(this.target);
		
		// if == add target to arrayList
		if (comparisonValue == 0) {
			results.add(node.getValue());
		}
	
		// if less than the target - go right
		if (comparisonValue > 0) {
			if (node.getRight() != null) {
				find(node.getRight());
			}
		}
		
		// if greater than the target - go left
		if (comparisonValue <= 0) {
			if (node.getLeft() != null) {
				find(node.getLeft());
			}
		}
		
	}

}
