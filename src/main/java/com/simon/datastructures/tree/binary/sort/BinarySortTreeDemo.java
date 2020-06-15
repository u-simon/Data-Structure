package com.simon.datastructures.tree.binary.sort;

/**
 * @author simon
 * @date 2020/6/15 9:43 下午
 */
public class BinarySortTreeDemo {

	public static void main(String[] args) {
		int[] nodes = {7, 3, 10, 12, 5, 1, 9, 0};
		BinarySortTree bst = new BinarySortTree();
		for (int node : nodes) {
			bst.add(new Node(node));
		}
		bst.infixOrder();

		System.out.println();
		// bst.delete(2);
		bst.delete(10);
		bst.infixOrder();
	}

}


class BinarySortTree {
	Node root;

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
		// 如果当前二叉树只有一个节点
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
				return;
			}
			this.left.add(node);
			return;
		}

		if (this.right == null) {
			this.right = node;
			return;
		}
		this.right.add(node);
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

