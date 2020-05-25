package com.simon.datastructures.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author simon
 * @date 2020/5/25 10:12 下午
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] array = {1, 3, 3, 411, 512};
//		System.out.println(binary(array, 0, array.length, -1));

		System.out.println(binary2(array, 0, array.length, 2).toString());
	}


	private static int binary(int[] array, int left, int right, int find) {
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		if (find > array[mid]) {
			return binary(array, mid + 1, right, find);
		} else if (find < array[mid]) {
			return binary(array, left, mid - 1, find);
		} else {
			return mid;
		}
	}

	private static List<Integer> binary2(int[] array, int left, int right, int find) {
		if (left > right) {
			return new ArrayList<>();
		}
		int mid = (left + right) / 2;
		if (find > array[mid]) {
			return binary2(array, mid + 1, right, find);
		} else if (find < array[mid]) {
			return binary2(array, left, mid - 1, find);
		} else {
			List<Integer> list = new ArrayList<>();

			int temp = mid - 1;
			while (temp >= 0 && array[temp] == find) {
				list.add(temp--);
			}
			list.add(mid);
			temp = mid + 1;
			while (temp <= right && array[temp] == find) {
				list.add(temp++);
			}
			return list;
		}
	}
}
