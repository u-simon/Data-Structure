package com.simon.datastructures.sort;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/22 10:31 下午
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] array = {8, 4, 5, 7, 1, 3, 6, 2, 9, 0};
		part(array, 0, array.length - 1, new int[array.length]);
		System.out.println(Arrays.toString(array));
	}

	// 分 先把数组拆分
	private static void part(int[] array, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			// 左递归拆分
			part(array, left, mid, temp);
			// 右递归拆分
			part(array, mid + 1, right, temp);
			// 合 把拆分的数据在合起来
			merge(array, left, mid, right, temp);

		}
	}

	private static void merge(int[] array, int left, int mid, int right, int[] temp) {
		int i = left;// 数组左边的初始索引
		int j = mid  + 1; // 数组右边的初始索引
		int t = 0;// 临时数组的索引

		// 两边比较把小的数放到临时数组中
		while (i <= mid && j <= right) {
			if (array[i] < array[j]) {
				temp[t++] = array[i++];
				continue;
			}
			temp[t++] = array[j++];
		}

		// 把剩余的数也放入到数组中
		while (i <= mid) {
			temp[t++] = array[i++];
		}

		while (j <= right) {
			temp[t++] = array[j++];
		}

		// 把临时数组中的数拷贝到原数组中
		t = 0;
		i = left;
		while (i <= right) {
			array[i++] = temp[t++];
		}
	}
}
