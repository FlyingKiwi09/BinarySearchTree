package application;

import java.util.ArrayList;

public interface BSTCollector<T, V> {
	
	public ArrayList<V> collect(T target, BSTNode<V> node);
	public void find(BSTNode<V> node);
	
}
