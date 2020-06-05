package com.simon.datastructures.tree;

import com.sun.codemodel.internal.JWhileLoop;

/**
 * @author simon
 * @date 2020/5/31 3:27 下午
 */
public class BinaryTree {

	public static void main(String[] args) {

		Node root = new Node(1);
		Node root2 = new Node(2);
		Node root3 = new Node(3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		root.setLeft(root2);
		root.setRight(root3);
		root3.setRight(root4);
		root3.setLeft(root5);

		root.preOrder(); // 12354

		System.out.println();

		root.infixOrder(); // 2 1 5 3 4

		System.out.println();

		root.postOrder(); // 2 5 4 3 1

//		System.out.println(root.preOrderSearch(5));
//		System.out.println(root.infixOrderSearch(5));

		System.out.println(root.postOrderSearch(5));
	}
}


class Node {
	private int no;

	private Node left;

	private Node right;

	public Node(int no) {
		this.no = no;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node{" + "no=" + no + '}';
	}

	/**
	 * 先序遍历 中左右 (父节点 左节点 右节点)
	 */
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	/**
	 * 中序遍历 左中右(左节点 父节点 右节点)
	 */
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	/**
	 * 后序遍历 左右中(左节点 右节点 父节点)
	 */
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		if (this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}

	/**
	 * 先序查找
	 * 
	 * @param no
	 * @return
	 */
	public Node preOrderSearch(int no) {
		System.out.println("先序查找");
		if (this.no == no) {
			return this;
		}
		Node node = null;
		if (this.left != null) {
			node = this.left.preOrderSearch(no);
		}
		if (node != null) {
			return node;
		}

		if (this.right != null) {
			node = this.right.preOrderSearch(no);
		}
		return node;
	}

	/**
	 * 中序查找
	 * 
	 * @param no
	 * @return
	 */
	public Node infixOrderSearch(int no) {
		Node node = null;
		if (this.left != null) {
			node = this.left.infixOrderSearch(no);
		}
		if (node != null) {
			return node;
		}
		System.out.println("中序查找~");
		if (this.no == no) {
			return this;
		}
		if (this.right != null) {
			node = this.right.infixOrderSearch(no);
		}
		return node;
	}

	/**
	 *
	 * @param no
	 * @return
	 */
	public Node postOrderSearch(int no){
		Node node = null;
		if (this.left != null){
			node	= this.left.postOrderSearch(no);
		}
		if (node != null){
			return node	;
		}
		if (this.right != null){
			node = this.right.postOrderSearch(no);
		}
		if (node != null){
			return node;
		}
		System.out.println("后序查找~");
		if (this.no == no){
			return this;
		}
		return node;
	}
}
