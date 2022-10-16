package application;

import java.util.ArrayList;

public class IDResultCollector implements BSTCollector<Integer, Person>{
	
	private int target;
	private ArrayList<Person> results;
	
	public IDResultCollector() {
		this.results = new ArrayList<Person>();
	}

	@Override
	public ArrayList<Person> collect(Integer target, BSTNode<Person> node) {
		this.target = target;
		this.results = new ArrayList<Person>();
		find(node); 
		return results;
	}

	@Override
	public void find(BSTNode<Person> node) {
		// if == add target to arrayList
		if (node.getValue().getID() == this.target) {
			results.add(node.getValue());
		}
	
		// if less than the target - go right
		if (node.getValue().getID() > this.target) {
			if (node.getRight() != null) {
				find(node.getRight());
			}
		}
		
		// if greater than the target - go left
		if (node.getValue().getID() <= this.target) {
			if (node.getLeft() != null) {
				find(node.getLeft());
			}
		}
	}

}
