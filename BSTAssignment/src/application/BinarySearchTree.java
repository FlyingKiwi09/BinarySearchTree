package application;

import java.util.Comparator;

import javafx.collections.ObservableList;

public class BinarySearchTree<V> {
	
	private BSTNode<V> root;
	private Comparator<V> comparator; 

	// constructs an empty binary search tree
	public BinarySearchTree(Comparator<V> comparator){
		this.comparator = comparator;
	}
	
	// constructor the value that the comparator takes must be the same type as the value that is passed through to construct the new Node
	public BinarySearchTree(V value, Comparator<V> comparator) {
		this.root = new BSTNode<V>(value);
		this.comparator = comparator;
	}
	
	// adds a new node to the tree when a key & value is passed through
	public void add(V value) {
		if (this.root == null) {
			this.root = new BSTNode<V>(value); // if the root hasn't been set yet, constructs the root node
		} else {
			this.root.add(new BSTNode<V>(value), this.comparator);
		}		
	}
	
	// adds a new node to the tree when a node is passed through
	public void add(BSTNode<V> newNode) {
		if (this.root == null) {
			this.root = newNode; // if the root hasn't been set yet
		} else {
			this.root.add(newNode, this.comparator);
		}
	}
	
	
	// print methods
	
	public void printIODF(ObservableList<String> list) {
		this.root.printIODF(list);
	}

}
