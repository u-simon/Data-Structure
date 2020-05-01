package com.simon.datastructures.queue;

import java.util.Scanner;

/**
 * @author simon
 * @date 2020/5/1 5:22 下午
 */
public class CycleQueueDemo {

    public static void main(String[] args) {
        CycleQueue queue = new CycleQueue(4);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);

            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    try {
                        int value = scanner.nextInt();
                        queue.add(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println(queue.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    System.out.println(queue.head());
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

class CycleQueue {
    /**
     *
     */
    private int maxSize;

    /**
     * front 指向数组的第一个元素
     */
    private int front;

    /**
     * rear指向队列的最后一个元素的后一个元素,因为希望空出一个位置作为约定
     * rear代表下一个空的位置
     * <p>
     * 为什么要空出一个元素做为约定
     * 在学习循环队列这一个知识点时候，我遇到了一些问题所以这也算是我的一些个人心得吧，
     * 我在学习这个知识点时候，喜欢生动形象的来解释原理，首先循环队列，我采用数组来存储，
     * 举个列子a[5]吧，一共有5个存储空间，其次，再来说一下循环队列有那些特征，首先循环循环，
     * 那必定是一个环，转呀转的，就当成一个操场一样，大家可以脑海里想象一下，其次，还有两个指针变量rear和front，
     * 刚开始，他俩都是在a[0]这个位置，因为现在没有元素，好了，此时突然有一个元素进来了，rear这变量就需要挪一下位置，
     * 移动到下一个空的位置，以方便，下次插入新的元素时候，有空的地方给新元素，好如此进行，终于rear到了a[4]这个位置，
     * a[4]这个位置首先肯定是空的，因为rear就是为了告诉系统我有空位置，有新的元素要插入，你就到我这儿来吧，系统一听好的，
     * 你空啦，那我来了，那rear又要挪动位置给新的元素，他得去下一个空的位置，哎，rear突然发现，
     * 哦，你是循环队列啊，那下一个不就又回到了a【0】这个位置吗，可是啊，里面不是有元素了吗，r
     * ear一想我的任务是去找空的位置给新元素啊，这下咋办，凉凉了，哎，我真后悔应该在a[4]这个位置的时候就提前告诉系统，
     * 你别放新元素进来了，已经满了，放不下了，好了，那我下次就这么做，当我在最后一个空的位置时候，
     * 我就告诉系统，所以这也就是为什么循环队列存的元素个数总是比他占用的存储空间少一个的原因
     */
    private int rear;

    /**
     *
     */
    private int[] array;

    public CycleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int data) {
        if (isFull()) {
            throw new RuntimeException("队列满了,不能再添加元素");
        }
        array[rear] = data;
        rear = (rear + 1) % maxSize;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int data = array[front];
        front = (front + 1) % maxSize;
        return data;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空 没有元素");
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.println(array[i % maxSize]);
        }
    }

    private int size() {
        return (rear - front + maxSize) % maxSize;
    }


    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return array[front];
    }
}
