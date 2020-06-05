package com.simon.datastructures.tree.threaded;

/**
 * 线索化二叉树
 *
 * @author simon
 * @date 2020/6/5 10:18 下午
 */
public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {
		Node node = new Node(1);
		Node node3 = new Node(3);
		Node node6 = new Node(6);
		Node node8 = new Node(8);
		Node node10 = new Node(10);
		Node node14 = new Node(14);
		node.setLeft(node3);
		node.setRight(node6);

		node3.setLeft(node8);
		node3.setRight(node10);

		node6.setLeft(node14);

		node.threaded(node);

		System.out.println(node10.getLeft());
		System.out.println(node10.getRight());
	}
}


class Node {
	private int no;

	private Node left;

	private Node right;

	private int leftType;

	private int rightType;

	private Node prev = null;

	public Node(int no) {
		this.no = no;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
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
	 * 中序线索化
	 * 
	 * @param node
	 */
	public void threaded(Node node) {
		if (node == null) {
			return;
		}
		// 中序 左根右

		threaded(node.left);
		// 8节点的prev = null 后继结点为1
		if (node.left == null) {
			node.left = prev;
			// 设置先驱节点的类型为 前驱节点
			node.leftType = 1;
		}

		if (prev != null && prev.right == null) {
			prev.right = node;
			prev.rightType = 1;
		}


		prev = node;

		threaded(node.right);

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
	public Node postOrderSearch(int no) {
		Node node = null;
		if (this.left != null) {
			node = this.left.postOrderSearch(no);
		}
		if (node != null) {
			return node;
		}
		if (this.right != null) {
			node = this.right.postOrderSearch(no);
		}
		if (node != null) {
			return node;
		}
		System.out.println("后序查找~");
		if (this.no == no) {
			return this;
		}
		return node;
	}
}

