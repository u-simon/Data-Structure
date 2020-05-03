package com.simon.datastructures.stack;

import java.util.Scanner;

/**
 * @author simon
 * @date 2020/5/3 9:40 上午
 */
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(push):添加数据到队列");
            System.out.println("g(pop):从队列取出数据");
            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    try {
                        stack.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        int value = scanner.nextInt();
                        stack.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println(stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

class LinkedStack {
    private Node head;

    private Node top;

    public LinkedStack() {
        this.head = new Node(-1);
        top = this.head;
    }

    public boolean isEmpty() {
        return top == head;
    }

    public void push(int no) {
        Node node = new Node(no);
        top.next = node;
        node.prev = top;
        top = node;
    }

    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node temp = top;
        top = top.prev;
        return temp;
    }

    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node temp = top;
        while (temp != head) {
            System.out.println(temp.toString());
            temp = temp.prev;
        }
    }

    public int peek(){
        return top.no;
    }
}

class Node {
    final int no;

    Node next;

    Node prev;

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}
