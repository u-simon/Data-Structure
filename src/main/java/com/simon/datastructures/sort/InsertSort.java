package com.simon.datastructures.sort;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/18 10:42 下午
 */
public class InsertSort {


	public static void main(String[] args) {
		int[] array = {101, 34, 119, 1};
		insert(array);
		System.out.println(Arrays.toString(array));
	}

	/**
	 * 从i+1开始开始 查看当前位置的元素和i之前的所有元素进行比较 找到合适的位置插入当前数据
	 */
	public static void insert(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int value = array[i];
			int beginIndex = i - 1;
			while (beginIndex >= 0 && array[beginIndex] > value) {
				array[beginIndex + 1] = array[beginIndex];
				beginIndex--;
			}
			if (beginIndex + 1 != i) {
				array[beginIndex + 1] = value;
			}
			System.out.println("第" +i +"轮排序 ： "+ Arrays.toString(array));
		}
	}
}
