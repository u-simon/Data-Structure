package com.simon.datastructures.tree.avl;

/**
 * @author simon
 * @date 2020/6/16 9:36 下午
 */
public class AVLTreeDemo {
	public static void main(String[] args) {
		// int[] array = {4, 3, 6, 5, 7, 8};
//		int[] array = {10, 12, 8, 9, 7, 6};
		int[] array = {10, 11, 7, 6, 8, 9};
		AVLTree avlTree = new AVLTree();
		for (int arr : array) {
			avlTree.add(new Node(arr));
		}
		avlTree.infixOrder();

		System.out.println(avlTree.height());
		System.out.println(avlTree.root.leftHeight());
		System.out.println(avlTree.root.rightHeight());
		System.out.println(avlTree.root.left);
	}
}


class AVLTree {
	Node root;

	public int height() {
		return root == null ? 0 : root.height();
	}

	public void add(Node node) {
		if (root == null) {
			root = node;
			return;
		}
		root.add(node);
	}

	public void infixOrder() {
		if (root != null) {
			this.root.infixOrder();
		}
	}

	public Node search(int value) {
		return root.search(value);
	}

	public Node searchParent(int value) {
		return root.searchParent(value);
	}

	public int deleteRightMin(Node node) {
		Node target = node;
		while (target.left != null) {
			target = target.left;
		}
		delete(target.value);
		return target.value;
	}

	public void delete(int value) {
		Node target = search(value);
		// 没有找到要删除的节点
		if (target == null) {
			return;
		}
		// 如果当前二叉树只有一个节点ƒ
		if (root.left == null && root.right == null) {
			this.root = null;
			return;
		}
		// 查找要删除节点的父节点
		Node parent = searchParent(value);

		// 如果当前节点为叶子节点
		if (target.left == null && target.right == null) {
			// 判断当前结点为父节点的左还是右子节点
			if (parent.left != null && parent.left.value == value) {
				parent.left = null;
			} else if (parent.right != null && parent.right.value == value) {
				parent.right = null;
			}
		} else if (target.left != null && target.right != null) {
			// 目标节点有两个孩子节点
			// 查找并删除目标节点右子节点的最小值
			int min = deleteRightMin(target.right);
			target.value = min;
		} else {
			// 删除的目标节点只有一个孩子节点
			// 为目标节点的左子节点
			if (target.left != null) {
				// 判断要删除的节点是父节点的 左还是右子节点
				if (parent != null) {
					if (parent.left.value == value) {
						parent.left = target.left;
					} else {
						parent.right = target.left;
					}
				} else {
					root = target.left;
				}

			} else {
				if (parent != null) {
					if (parent.left.value == value) {
						parent.left = target.right;
					} else {
						parent.right = target.right;
					}
				} else {
					root = target.left;
				}
			}
		}
	}
}


class Node {
	int value;

	Node left;

	Node right;

	public Node(int value) {
		this.value = value;
	}

	/**
	 * 右旋
	 */
	private void rightRotate() {
		// 创建一个新的节点以当前根节点的值
		Node newNode = new Node(value);
		// 把新节点的右子树设置成当前节点的右子树
		newNode.right = right;
		// 把新节点的左子树设置成当前节点的左子树的右子树
		newNode.left = left.right;
		// 把当前节点的值换为左子节点的值
		value = left.value;
		// 把当前节点的左子树设置成左子树的左子树
		left = left.left;
		// 把当前节点的右子树设置为新节点
		right = newNode;
	}

	/**
	 * 左旋
	 */
	public void leftRotate() {
		// 创建新的节点，以当前根节点的值
		Node newNode = new Node(value);
		// 把新节点的左子树设置成当前节点的左子树
		newNode.left = left;
		// 把新节点的右子树设置成当前节点的右子树的左子树
		newNode.right = right.left;
		// 把当前节点的值换为右子节点的值
		value = right.value;
		// 把当前节点的右子树设置成右子树的右子树
		right = right.right;
		// 把当前节点的左子树设置为新节点
		left = newNode;
	}

	public int rightHeight() {
		return right == null ? 0 : right.height();
	}

	public int leftHeight() {
		return left == null ? 0 : left.height();
	}

	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}

	/**
	 * 查找要删除的节点
	 *
	 * @param value
	 * @return
	 */
	public Node search(int value) {
		if (value == this.value) {
			return this;
		}

		if (this.left != null && this.value > value) {
			return this.left.search(value);
		}
		if (this.right != null && this.value <= value) {
			return this.right.search(value);
		}
		return null;
	}

	/**
	 * 查找要删除节点的父节点
	 *
	 * @param value
	 * @return
	 */
	public Node searchParent(int value) {
		if ((this.left != null && this.left.value == value)
				|| (this.right != null && this.right.value == value)) {
			return this;
		}
		if (this.value > value && this.left != null) {
			return this.left.searchParent(value);
		}
		if (this.value < value && this.right != null) {
			return this.right.searchParent(value);
		}
		return null;
	}

	public void add(Node node) {
		if (node == null) {
			return;
		}
		if (node.value < this.value) {
			if (this.left == null) {
				this.left = node;
			} else {
				this.left.add(node);
			}
		} else {
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}
		if (rightHeight() - leftHeight() > 1) {
			// 如果右子树的左子数高度大于右子树的右子树高度
			if (right != null && right.leftHeight() > right.rightHeight()){
				// 先进行右旋
				right.rightRotate();
			}
			leftRotate();
			return;
		}
		if (leftHeight() - rightHeight() > 1) {
			// 如果左子树的右子树高度大于左子树的左子树高度
			if (left != null && left.rightHeight() > left.leftHeight()){
				// 先进行左旋
				left.leftRotate();
			}
			rightRotate();
		}
	}


	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}


	@Override
	public String toString() {
		return "Node{" + "value=" + value + '}';
	}
}
