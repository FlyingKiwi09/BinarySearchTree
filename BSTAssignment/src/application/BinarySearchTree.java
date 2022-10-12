package application;

import java.util.Comparator;

public class BinarySearchTree<K, V> {
	
	private BSTNode<K, V> root;
	private Comparator<V> comparator; 

	public BinarySearchTree(BSTNode<K, V> root, Comparator<V> comparator) {
		this.root = root;
		this.comparator = comparator;
	}
	
	public void add(BSTNode<K, V> newNode) {
		this.root.add(newNode, comparator);
	}

}
