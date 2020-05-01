package com.simon.datastructures.queue;

import java.util.Scanner;

/**
 * @author simon
 * @date 2020/5/1 2:51 下午
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);

            switch (key){
                case 's':
                    queue.show();
                    break;
                case 'a':
                    try {
                        int value = scanner.nextInt();
                        queue.add(value);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println(queue.get());
                    }catch (Exception e){
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

class ArrayQueue {

    /**
     * 最大容量
     */
    private int maxSize;

    /**
     * 指向队列头的前一个
     */
    private int front;

    /**
     * 队列尾
     */
    private int rear;

    /**
     * 数组
     */
    private int[] array;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.array = new int[maxSize];
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int data) {
        if (isFull()) {
            throw new RuntimeException("队列满不能添加数据");
        }
        array[++rear] = data;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return array[++front];
    }


    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return array[front + 1];
    }

    public void show(){
        if (isEmpty()) {
            System.out.println("队列为空没有数据");
            return;
        }
        for (int i : array) {
            System.out.println(i);
        }
    }
}
