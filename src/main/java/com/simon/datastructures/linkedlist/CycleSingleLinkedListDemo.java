package com.simon.datastructures.linkedlist;

/**
 * @author simon
 * @date 2020/5/2 5:06 下午
 */
public class CycleSingleLinkedListDemo {

    public static void main(String[] args) {
        CycleSingleLinkedList cycleSingleLinkedList = new CycleSingleLinkedList();

        cycleSingleLinkedList.add(new SingleNode(1));
        cycleSingleLinkedList.add(new SingleNode(2));
        cycleSingleLinkedList.add(new SingleNode(3));
        cycleSingleLinkedList.add(new SingleNode(4));
        cycleSingleLinkedList.add(new SingleNode(5));

//        cycleSingleLinkedList.list();
//
//        System.out.println();
//        System.out.println(cycleSingleLinkedList.getPrev(cycleSingleLinkedList.getFirst()));
//
//        cycleSingleLinkedList.delete(2);
//
//        System.out.println();
//        cycleSingleLinkedList.list();

        cycleSingleLinkedList.pop(1, 2, 5);
    }
}


class CycleSingleLinkedList {

    private SingleNode first;

    public SingleNode getFirst() {
        return first;
    }

    public void add(SingleNode node) {
        SingleNode temp = first;
        if (temp == null) {
            first = node;
            first.next = first;
            return;
        }
        while (temp.next != first) {
            temp = temp.next;
        }
        temp.next = node;
        node.next = first;
    }

    public void list() {
        SingleNode temp = first;
        do {
            System.out.println(temp);
        } while ((temp = temp.next) != first);
    }

    public void delete(int no) {
        SingleNode temp = first;
        if (first.no == no) {
            SingleNode prev = getPrev(temp);
            first = first.next;
            prev.next = first;
            return;
        }
        do {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
            }
        } while ((temp = temp.next) != first);
    }

    public SingleNode getPrev(SingleNode node) {
        SingleNode temp = node;
        while (temp.next != node) {
            temp = temp.next;
        }
        return temp;
    }

    public void pop(int start, int step, int count) {
        if (start < 1 || step < 1 || start > count) {
            System.out.println("参数有误");
            return;
        }
        // 先找到开始节点
        SingleNode temp = first;
        while (temp.next != first){
            if (temp.no == start) {
                break;
            }
            temp = temp.next;
        }
        // temp 为开始节点
        // 找到他的前一个节点
        SingleNode helper = getPrev(temp);
        while (true){
            if (helper == temp){
                // 只剩下一个节点
                System.out.println(temp);
                // help gc
                temp = null;
                helper = null;
                break;
            }
            // 同时想next方向移动step-1个位置
            for (int i = 0; i < step - 1; i++) {
                temp = temp.next;
                helper = helper.next;
            }
            System.out.println(temp);
            temp = temp.next;
            helper.next = temp ;
        }
    }
}


class SingleNode {
    int no;
    SingleNode next;

    public SingleNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "SingleNode{" +
                "no=" + no +
                '}';
    }
}
