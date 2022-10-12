package application;

import java.util.Comparator;

import javafx.collections.ObservableList;

public class BinarySearchTree<K, V> {
	
	private BSTNode<K, V> root;
	private Comparator<V> comparator; 

	// constructor the value that the comparator takes must be the same type as the value that is passed through to construct the new Node
	public BinarySearchTree(K key, V value, Comparator<V> comparator) {
		this.root = new BSTNode<K, V>(key, value);
		this.comparator = comparator;
	}
	
	// adds a new node to the tree when a key & value is passed through
	public void add(K key, V value) {
		this.root.add(new BSTNode<K, V>(key, value), this.comparator);
	}
	
	// adds a new node to the tree when a node is passed through
	public void add(BSTNode<K, V> newNode) {
		this.root.add(newNode, this.comparator);
	}
	
	// outputs all to the list
	public void output(ObservableList<String> list) {
		this.root.output(list);
	}
	

}
