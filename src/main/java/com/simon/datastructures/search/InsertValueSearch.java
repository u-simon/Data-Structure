package com.simon.datastructures.search;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/26 9:50 下午
 */
public class InsertValueSearch {

	public static void main(String[] args) {
		int[] array = new int[100];
		for (int i = 0; i < 100; i++) {
			array[i] = i + 1;
		}
//		System.out.println(Arrays.toString(array));array
		System.out.println(insertValue(array, 0, array.length - 1, 100));
	}
	private static int insertValue(int[] array, int left, int right, int find) {
		if (left > right || find < array[0] || find > array[array.length - 1]) {
			return -1;
		}

		int mid = left + (right - left) * (find - array[left]) / (array[right] - array[left]);
		if (array[mid] < find) {
			return insertValue(array, mid + 1, right, find);
		} else if (array[mid] > find) {
			return insertValue(array, left, mid - 1, find);
		} else {
			return mid;
		}
	}
}
