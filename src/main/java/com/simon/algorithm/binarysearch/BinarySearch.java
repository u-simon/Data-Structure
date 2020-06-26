package com.simon.algorithm.binarysearch;

/**
 * 非递归二分查找
 * 
 * @author simon
 * @date 2020/6/26 8:47 上午
 */
public class BinarySearch {
	public static void main(String[] args) {
		int[] array = {1, 2, 4, 6, 19, 35, 100};
		System.out.println(binarySearch(array, 6));
	}

	private static int binarySearch(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}
}
