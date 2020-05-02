package com.simon.datastructures.linkedlist;

/**
 * @author simon
 * @date 2020/5/2 4:48 下午
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(new DoubleNode(1, "simon"));
        doubleLinkedList.add(new DoubleNode(2, "lemon"));
        doubleLinkedList.add(new DoubleNode(3, "jerry"));
        doubleLinkedList.add(new DoubleNode(4, "smith"));
        doubleLinkedList.add(new DoubleNode(5, "tom"));

        doubleLinkedList.list();

        System.out.println();
        doubleLinkedList.update(new DoubleNode(3, "bob"));

        doubleLinkedList.list();

        System.out.println();
        doubleLinkedList.delete(3);

        doubleLinkedList.list();
    }
}


class DoubleLinkedList {
    private DoubleNode head;

    public DoubleLinkedList() {
        this.head = new DoubleNode(0, "");
    }

    public void add(DoubleNode node) {
        DoubleNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.prev = temp;
    }

    public void list() {
        DoubleNode temp = head;
        while ((temp = temp.next) != null) {
            System.out.println(temp.toString());
        }
    }

    public void update(DoubleNode node) {
        DoubleNode temp = head;
        while ((temp = temp.next) != null) {
            if (temp.no == node.no) {
                temp.name = node.name;
                break;
            }
        }
    }

    public void delete(int no) {
        DoubleNode temp = head;
        while ((temp = temp.next) != null){
            if (temp.no == no){
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
            }
        }
    }


}

class DoubleNode {
    final int no;

    String name;

    DoubleNode prev;

    DoubleNode next;

    public DoubleNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
