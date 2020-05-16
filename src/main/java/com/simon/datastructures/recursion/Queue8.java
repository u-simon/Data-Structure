package com.simon.datastructures.recursion;

/**
 * 8 皇后问题
 * 
 * @author simon
 * @date 2020/5/16 3:15 下午
 */
public class Queue8 {

	private static int size = 8;
	/**
	 * index表示棋盘的行 value表示棋盘的列
	 */
	static int[] array = new int[size];

	private static int count = 0;

	public static void main(String[] args) {
		check(0);
		System.out.println(count);
	}

	/**
	 * 
	 * @param n 放置的第n个皇后 n为0 - 7
	 */
	public static void check(int n) {
		if (n == size) {
			// 放置的为最后一个
			print();
			return;
		}
		// 依次放入size个皇后
		for (int i = 0; i < size; i++) {
			// 把第n行设置上列值
			array[n] = i;
			// 判断放置的第n个皇后时候合法
			if (judge(n)) {
				check(n + 1);
			}
		}
	}

	private static boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			// array[i] == array[n] 则表示在同一列
			// Math.abs(array[n] - array[i]) == Math.abs(n - i) 表示在同一斜线
			if (array[i] == array[n] || Math.abs(array[n] - array[i]) == Math.abs(n - i)) {
				return false;
			}
		}
		return true;
	}

	private static void print() {
		count++;
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
