package application;

import java.util.ArrayList;

public class AgeResultCollector implements BSTCollector<Integer, Person> {

	private int target;
	private ArrayList<Person> results;
	
	public AgeResultCollector() {
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
		if (node.getValue().getAge() == this.target) {
			results.add(node.getValue());
		}
	
		// if less than the target - go right
		if (node.getValue().getAge() > this.target) {
			if (node.getRight() != null) {
				find(node.getRight());
			}
		}
		
		// if greater than the target - go left
		if (node.getValue().getAge() <= this.target) {
			if (node.getLeft() != null) {
				find(node.getLeft());
			}
		}
	}

}
