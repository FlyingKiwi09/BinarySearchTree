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
				this.left.setParent(this);
			}
		} else {
			if (this.right == null) {
				this.right = newNode;
			} else {
				this.right.add(newNode, comparator);
				this.right.setParent(this);
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
		
		list.add(label + this.value.toString() );
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
	
	// delete node
	// from video  https://www.youtube.com/watch?v=JsgqnTLjfps
	public BSTNode<V> delete(BSTNode<V> node, V toDelete, Comparator<V> comparator){
		if (node == null) {
			return null;
		} 
		
		int compareValue = comparator.compare(toDelete, node.value);
		if (compareValue < 0 ) {
			node.left = delete(node.left, toDelete, comparator);
		} else if (compareValue > 0) {
			node.right = delete(node.right, toDelete, comparator);
		} else {
			if (node.left == null || node.right == null) {
				BSTNode<V> temp = null;
				temp = node.left == null ? node.right : node.left;
				
				if (temp == null) {
					return null;
				} else {
					return temp;
				}
				
			} else {
				BSTNode<V> successor = getSuccessor(node);
				node.value = successor.value;
				delete(node.right, successor.value, comparator);
			}
		}
		return node;
		
	}
	
	 
	public BSTNode<V> getSuccessor (BSTNode<V> node){
		if (node == null) {
			return null;
		}
		
		BSTNode<V> temp = node.right;
		
		while (temp.left != null) {
			temp = temp.left;
		}
		
		return temp;
	}
	
//	// My attempt at delete node 
//	public BSTNode<V> delete(V toDelete, Comparator<V> comparator) {
//		if (this.value.equals(toDelete)) { // if this is the node to delete
//			
//			if (this.left == null && this.right == null) { // if this is a leaf
//				this.parent.deleteLeafChild(this.value); // delete me
//			} else if (this.left == null) { // if this has no left children
//				this.parent.deleteChildRightOnly(this.value);// delete me and replace me with my right child
//			} else if (this.right == null) { // if this has no right children
//				this.parent.deleteChildLeftOnly(this.value);// delete me and replace me with my left child
//			} else { // if this has two children
//				// find minimum in right-sub tree
//				// copy the value the targeted node
//				// delete duplicate from right-subtree
//				this.value = findMinOnRight(this.right);
//			
//			return 
//			
//			
//		} else { // search the tree for the node to delete
//			int compareValue = comparator.compare(toDelete, this.value);
//			if (compareValue < 1 && this.left != null) {
//				this.left.delete(toDelete, comparator);
//			}
//			if (compareValue > 0 && this.right != null ) {
//				this.right.delete(toDelete, comparator);
//			}
//		}
//		
//		
//	}
//	
//	private void deleteLeafChild(V toDelete) {
//		if (this.left.equals(toDelete)) {
//			this.left = null;
//		}
//		
//		if (this.right.equals(toDelete)) {
//			this.right = null;
//		}
//	}
//	
//	
//	private void deleteChildRightOnly(V toDelete) {
//		if (this.left.equals(toDelete)) {
//			this.left = this.left.right;
//		}
//		
//		if (this.right.equals(toDelete)) {
//			this.right = this.right.right;
//		}
//	}
//	
//	private void deleteChildLeftOnly(V toDelete) {
//		if (this.left.equals(toDelete)) {
//			this.left = this.left.left;
//		}
//		
//		if (this.right.equals(toDelete)) {
//			this.right = this.right.left;
//		}
//	}
//	
//	private V findMinOnRight(BSTNode<V> node) {
//		if (node.getLeft() == null) {
//			return node.getValue();
//		} else {
//			return findMinOnRight(this.getLeft());
//		}
//	}

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
