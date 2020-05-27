package com.simon.datastructures.hash;

/**
 * @author simon
 * @date 2020/5/27 10:28 下午
 */
public class HashTabDemo {

	public static void main(String[] args) {
		HashTab hashTab = new HashTab(7);

		hashTab.add(new Node(1,"simon"));
		hashTab.add(new Node(2,"simon"));
		hashTab.add(new Node(3,"simon"));
		hashTab.add(new Node(4,"simon"));
		hashTab.add(new Node(5,"simon"));
		hashTab.add(new Node(6,"simon"));
		hashTab.add(new Node(7,"simon"));
		hashTab.add(new Node(8,"simon"));
		hashTab.add(new Node(15,"simon"));
		System.out.println("34");
		hashTab.list();
		System.out.println(hashTab.get(4).toString());
		hashTab.remove(8);
		System.out.println("56");
		hashTab.list();
		hashTab.remove(1);
		System.out.println("78");
		hashTab.list();
	}
}


class HashTab {
	int size;
	NodeList[] nodeLists;

	public HashTab(int size) {
		this.size = size;
		this.nodeLists = new NodeList[size];

		for (int i = 0; i < size; i++) {
			nodeLists[i] = new NodeList();
		}
	}

	public void  add(Node node){
		nodeLists[node.id % size].add(node);
	}

	public void list(){
		for (int i = 0; i < size; i++) {
			nodeLists[i].list();
		}
	}

	public Node get(int id){
		return nodeLists[id % size].get(id);
	}

	public void remove(int id){
		nodeLists[id % size].remove(id);
	}
}


class NodeList {

	Node head;
	public void add(Node node) {
		if (head == null) {
			head = node;
			return;
		}
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = node;
	}

	public void list() {
		if (head == null) {
			System.out.println("链表为空");
		}
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.id + " , " + temp.name + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public Node get(int id) {
		if (head == null) {
			return null;
		}
		Node temp = head;
		while (temp != null) {
			if (temp.id == id) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	public void remove(int id) {
		Node temp = head;
		if (temp == null) {
			return;
		}
		if (temp.id == id){
			head = temp.next;
		}
		while (temp.next != null) {
			if (temp.next.id == id) {
				temp.next = temp.next.next;
				return;
			}
			temp = temp.next;
		}
	}
}


class Node {
	int id;

	String name;

	Node next;

	public Node(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Node{" +
				"id=" + id +
				", name='" + name +
				'}';
	}
}
