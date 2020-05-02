package com.simon.datastructures.linkedlist;

/**
 * 单向链表
 *
 * @author simon
 * @date 2020/5/2 9:53 上午
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        NodeList list = new NodeList();
//        list.add(new Node(1, "simon", "simon"));
//        list.add(new Node(2, "lemon", "lemon"));
//        list.add(new Node(3, "amy", "amy"));
//        list.add(new Node(4, "tom", "tom"));


        list.addOrder(new Node(4, "tom", "tom"));
        list.addOrder(new Node(1, "simon", "simon"));
        list.addOrder(new Node(3, "amy", "amy"));
        list.addOrder(new Node(2, "lemon", "lemon"));

//        list.list();
//
//        list.delete(3);
//        System.out.println("==========");
        list.list();

        System.out.println(NodeList.size(list.getHead()));

        System.out.println(NodeList.getLastIndexNode(list.getHead(), 1));
        NodeList.reverse(list.getHead());
        list.list();
    }
}


class NodeList {
    private final Node head;

    public NodeList() {
        this.head = new Node(0, "", "");
    }

    public Node getHead() {
        return head;
    }

    public void add(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void addOrder(Node node) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no == temp.no) {
                throw new RuntimeException("该节点已经存在");
            }
            temp = temp.next;
        }
        node.next = temp.next;
        temp.next = node;
    }

    public void delete(int no) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public void list() {
        Node temp = head;
        while ((temp = temp.next) != null) {
            System.out.println(temp.toString());
        }
    }

    public static Node reverse(Node head) {
        if (head.next == null || head.next.next == null) {
            return head;
        }
        Node cur = head.next;
        Node reverse = new Node(0, "", "");
        Node next;
        while (cur != null) {
            // 把当前节点分离出来
            next = cur.next;
            // 把reverse的next节点放到 cur的next节点上
            cur.next = reverse.next;
            // 把cur放到reverse的next节点上
            reverse.next = cur;
            // 指针指向下一个节点
            cur = next;
        }
        head.next = reverse.next;
        return head;
    }

    /**
     * 获取倒数第index个节点
     *
     * @param head
     * @param last
     * @return
     */
    public static Node getLastIndexNode(Node head, int last) {
        int length = size(head);
        if (last <= 0 || last > length) {
            return null;
        }
        // 计算正数第几个节点
        int index = length - last;
        Node temp = head.next;
        int count = 0;
        while (temp.next != null) {
            if ((count++) == index) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 链表有效数据的个数
     *
     * @param head
     * @return
     */
    public static int size(Node head) {
        int length = 0;
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }
        return length;
    }

}

class Node {
    final int no;

    final String name;

    final String nickname;

    public Node next;

    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
