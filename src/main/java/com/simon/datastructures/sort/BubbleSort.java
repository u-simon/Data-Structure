package com.simon.datastructures.sort;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/17 3:25 下午
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] array = new int[] {30, 9, -1, 20, 10};
		bubble(array);
		System.out.println("final ->  " + Arrays.toString(array));
	}

	public static void bubble(int[] array) {

		boolean swap = false;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					swap = true;
					array[j] = array[j] ^ array[j + 1];
					array[j + 1] = array[j] ^ array[j + 1];
					array[j] = array[j] ^ array[j + 1];
				}
			}
			if (!swap) {
				break;
			}
			// 重置
			swap = false;
			System.out.println("第" + (i + 1) + " 趟排序 ——> " + Arrays.toString(array));
		}
	}
}
