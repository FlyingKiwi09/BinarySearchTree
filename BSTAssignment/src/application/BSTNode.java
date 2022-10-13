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
		if (comparator.compare(this.value, newNode.getValue()) < 1) {
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
	
	
	public void printIODF(ObservableList<String> list) {
		
		if (this.right != null) {
			this.right.printIODF(list);
		}
		list.add(this.value.toString());
		
		if (this.left != null) {
			this.left.printIODF(list);
		}
		
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
