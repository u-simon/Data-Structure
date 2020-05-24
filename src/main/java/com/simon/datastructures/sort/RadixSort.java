package com.simon.datastructures.sort;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/24 9:31 上午
 */
public class RadixSort {


	public static void main(String[] args) {
		int[] array = {53, 3, 748, 14, 214};
		radix(array);
	}

	public static void radix(int[] array) {

		// 获取最大数
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (max < array[i]) {
				max = array[i];
			}
		}
		// 获取最大数的长度
		int length = String.valueOf(max).length();

		// 创建桶
		int[][] bucket = new int[10][array.length];
		// 记录每个桶中元素的个数
		int[] bucketIndex = new int[10];

		for (int i = 0, n = 1; i < length; i++, n *= 10) {
			for (int j = 0; j < array.length; j++) {
				// 根据位数来计算放到第几个桶中个十百千万的顺序
				int index = array[j] / n % 10;
				// 放入到桶中
				bucket[index][bucketIndex[index]++] = array[j];
			}

			// 取出bucket中的元素放回到原数组中
			int index = 0;
			for (int k = 0; k < bucket.length; k++) {
				if (bucketIndex[k] > 0) {
					for (int j = 0; j < bucketIndex[k]; j++) {
						array[index++] = bucket[k][j];
					}
					bucketIndex[k] = 0;
				}
			}
			System.out.println("第" + (i + 1) + "轮排序 ： " + Arrays.toString(array));
		}


	}
}
