package application;

import java.util.Comparator;
import javafx.collections.ObservableList;

public class BSTNode<V> {

	private V value;
	private BSTNode<V> parent;
	private BSTNode<V> left;
	private BSTNode<V> right;
	
	public BSTNode(V value) {
		this.value = value;
	}
	
	// recursive add method passes the newNode and the comparator down until the newNode can be added
	public void add(BSTNode<V> newNode, Comparator<V> comparator) {
		if (comparator.compare(newNode.getValue(), this.value) < 1) {
			if (this.left == null) {
				this.left = newNode;
			} else {
				this.left.add(newNode, comparator);
			}
		} else {
			if (this.right == null) {
				this.right = newNode;
			} else {
				this.right.add(newNode, comparator);
			}
		}
	}
	
	
	// in order depth first print that adds the print to the list
	public void printIODF(ObservableList<String> list) {
		
		if (this.right != null) {
			this.right.printIODF(list);
		}
		list.add(this.value.toString());
		
		if (this.left != null) {
			this.left.printIODF(list);
		}
	}
	
	// pre-order depth first print
	public void printPreDF(ObservableList<String> list, String label) {
		
		list.add(label + this.value.toString());
		if (this.right != null) {
			this.right.printPreDF(list, "  |  " + label);
		}
		if (this.left != null) {
			this.left.printPreDF(list, "  |  " + label);
		}
	}
	
	// post-order depth first print
	public void printPostDF(ObservableList<String> list, String label) {
		if (this.right != null) {
			this.right.printPostDF(list, "   |   " + label);
		}
		if (this.left != null) {
			this.left.printPostDF(list, "   |   " + label);
		}
		list.add(label + this.value.toString());
	}

	// getters and setters

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public BSTNode<V> getParent() {
		return parent;
	}

	public void setParent(BSTNode<V> parent) {
		this.parent = parent;
	}

	public BSTNode<V> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<V> left) {
		this.left = left;
	}

	public BSTNode<V> getRight() {
		return right;
	}

	public void setRight(BSTNode<V> right) {
		this.right = right;
	}
	
	

}
