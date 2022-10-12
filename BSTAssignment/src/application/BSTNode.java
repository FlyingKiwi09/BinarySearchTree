package application;

import java.util.Comparator;

public class BSTNode<K,V> {

	private K key;
	private V value;
	private BSTNode<K, V> parent;
	private BSTNode<K, V> left;
	private BSTNode<K, V> right;
	
	public BSTNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	// recursive add method passes the newNode and the comparator down until the newNode can be added
	public void add(BSTNode<K, V> newNode, Comparator<V> comparator) {
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

	// getters and setters
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public BSTNode<K, V> getParent() {
		return parent;
	}

	public void setParent(BSTNode<K, V> parent) {
		this.parent = parent;
	}

	public BSTNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<K, V> left) {
		this.left = left;
	}

	public BSTNode<K, V> getRight() {
		return right;
	}

	public void setRight(BSTNode<K, V> right) {
		this.right = right;
	}
	
	

}
