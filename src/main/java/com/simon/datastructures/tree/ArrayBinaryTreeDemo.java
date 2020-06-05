package com.simon.datastructures.tree;

/**
 * @author simon
 * @date 2020/6/2 9:58 下午
 */
public class ArrayBinaryTreeDemo {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6, 7};
		ArrayBinaryTree tree = new ArrayBinaryTree(arr);

		tree.preOrder(0);
	}
}


class ArrayBinaryTree {

	private int[] arr;

	public ArrayBinaryTree(int[] arr) {
		this.arr = arr;
	}

	public void preOrder(int index) {
		if (arr == null || arr.length == 0) {
			return;
		}

		System.out.println(arr[index]);

		// 左子树
		if ((2 * index + 1) < arr.length) {
			preOrder(2 * index + 1);
		}

		if ((2 * index + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}
}
