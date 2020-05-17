package com.simon.datastructures.sort;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/17 5:16 下午
 */
public class SelectSort {

	public static void main(String[] args) {
		int[] array = {101, 34, 119, 1};
		select(array);
		System.out.println("final -> " + Arrays.toString(array));
	}


	/**
	 * 从当前位置向后寻找到最小值然后进行交换 即：查找数组最小值然后放到数组的前面
	 */
	public static void select(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			// j < length的目的因为min位置个j位置 j位置从0-length-1
			for (int j = i + 1; j < array.length; j++) {
				if (array[min] > array[j]){
					min	 = j;
				}
			}
			if (min != i){
				// 进行交换 i位置和min位置
				array[i] = array[i] ^ array[min];
				array[min] = array[i] ^ array[min];
				array[i] = array[i] ^ array[min];
			}
			System.out.println("第"+(i+1)+"次循环 -> " + Arrays.toString(array));
		}
	}
}
