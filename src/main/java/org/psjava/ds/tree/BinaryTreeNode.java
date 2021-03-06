package org.psjava.ds.tree;

public interface BinaryTreeNode<T> {

	T getData();

	void setData(T data);

	boolean hasLeft();

	boolean hasRight();

	BinaryTreeNode<T> getLeft();

	BinaryTreeNode<T> getRight();

	void putLeft(BinaryTreeNode<T> child);

	void putRight(BinaryTreeNode<T> child);

	void removeLeft();

	void removeRight();
}
