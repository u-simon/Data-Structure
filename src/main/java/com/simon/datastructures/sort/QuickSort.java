package com.simon.datastructures.sort;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/20 10:46 下午
 */
public class QuickSort {


	public static void main(String[] args) {
		int[] array = {-9, 78, 23, 0, -567, 70, -1111, 0, 143, -543, 222};
		// int[] array = {-9, -567};
		quick(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}



	public static void quick(int[] array, int left, int right) {
		int medium = (left + right) / 2;
		int pivot = array[medium];
		int l = left;
		int r = right;
		while (l < r) {
			// 从左边寻找大于 pivot的数 等于就停止寻找
			while (array[l] < pivot) {
				l++;
			}
			// 从右边找小于 pivot的数 等于就停止寻找
			while (array[r] > pivot) {
				r--;
			}
			System.out.println(l + ", " + r);
			// 左边没找到大于pivot的数 右边没找到小于pivot的数
			if (l >= r) {
				// medium 左变都是比pivot小的值 右边都是比pivot大的值
				break;
			}
			// 交换l、r位置的值
			array[l] = array[l] ^ array[r];
			array[r] = array[l] ^ array[r];
			array[l] = array[l] ^ array[r];

			// 存在相同的值的时候
			if (array[l] == pivot) {
				r--;
			}

			if (array[r] == pivot) {
				l++;
			}
		}
		// l==r 说明 他俩都找到了 l == medium == r的位置
		// 进行左边的遍历则为 left ~ r-1 右边的遍历 则是 l + 1 ~ right
		if (l == r) {
			l++;
			r--;
		}
		// 向左递归
		if (left < r) {
			quick(array, left, r);
		}
		// 向右递归
		if (right > l) {
			quick(array, l, right);
		}
	}
}
