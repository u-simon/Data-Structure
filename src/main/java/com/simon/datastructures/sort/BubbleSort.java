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

	/**
	 * 两两进行比较依次把最大值放到数据的尾部 即 每次都把最大值放到后面
	 */
	public static void bubble(int[] array) {
		// 时间复杂度 O(n²)
		boolean swap = false;
		// 外层循环比较次数
		for (int i = 0; i < array.length - 1; i++) {
			// 内层循环比较交换 length的目的是因为下面有个j+1
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
