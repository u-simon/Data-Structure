package com.simon.datastructures.search;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/26 10:46 下午
 */
public class FibonacciSearch {
	private static int maxSize = 20;


	public static void main(String[] args) {
		int[] array = {1, 8, 10, 89, 1000, 1234};
		System.out.println(fibonacci(array, 1));
	}

	private static int[] fib() {
		int[] fib = new int[maxSize];
		fib[0] = 1;
		fib[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			fib[i] = fib[i - 1] + fib[i - 2];
		}
		return fib;
	}

	private static int fibonacci(int[] array, int find) {
		int low = 0;
		int high = array.length - 1;
		int k = 0;
		int mid = 0;
		int[] fib = fib();

		while (high > fib[k] - 1) {
			k++;
		}
		int[] temp = Arrays.copyOf(array, fib[k]);
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = array[high];
		}

		while (low <= high) {
			mid = low + fib[k - 1] - 1;
			if (find < temp[mid]) {
				// 向左查找
				high = mid - 1;
				k--;
			} else if (find > temp[mid]) {
				// 向右查找
				low = mid + 1;
				k -= 2;
			} else {
				if (mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
	}
}
