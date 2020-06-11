package com.simon.datastructures.tree.huffman;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author simon
 * @date 2020/6/9 10:08 下午
 */
public class HuffmanTree {

	public static void main(String[] args) {
		int[] array = {13, 7, 8, 3, 29, 6, 1};
		huffman(array).preOrder();
	}


	private static Node huffman(int[] array) {
		List<Node> nodes = Arrays.stream(array).mapToObj(Node::new).collect(Collectors.toList());

		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node left = nodes.remove(0);
			Node right = nodes.remove(0);
			Node parent = new Node(left.value + right.value);
			parent.left = left;
			parent.right = right;
			nodes.add(parent);
		}
		return nodes.get(0);
	}
}

class Node implements Comparable<Node> {
	public int value;

	public Node left;

	public Node right;

	public void preOrder(){
		System.out.println(this);

		if (this.left != null){
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}
	@Override
	public String toString() {
		return "Node{" + "value=" + value + '}';
	}

	public Node(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Node o) {
		return this.value - o.value;
	}
}
