package com.simon.datastructures.sort;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/5/21 9:44 下午
 */
public class ShellSort {
	public static void main(String[] args) {
		int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
		shell(array);
	}

	private static void test(int[] array) {
		for (int i = 5; i < array.length; i++) {
			for (int j = i - 5; j < i; j += 5) {
				if (array[j] > array[j + 5]) {
					System.out.println("i = " + i + ", j= " + j);
					array[j] = array[j] ^ array[j + 5];
					array[j + 5] = array[j] ^ array[j + 5];
					array[j] = array[j] ^ array[j + 5];
				}
			}
		}
		System.out.println("array = " + Arrays.toString(array));

		for (int i = 2; i < array.length; i++) {
			for (int j = i - 2; j < i; j += 2) {
				if (array[j] > array[j + 2]) {
					System.out.println("i = " + i + ", j= " + j);
					array[j] = array[j] ^ array[j + 2];
					array[j + 2] = array[j] ^ array[j + 2];
					array[j] = array[j] ^ array[j + 2];
				}
			}
		}
		System.out.println("array = " + Arrays.toString(array));
	}

	public static void shell(int[] array) {
		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < array.length; i++) {
				// 每轮都把当前分组中最小的数移动到分组的首位置
				for (int j = i - gap; j >= 0; j -= gap) {
					if (array[j] > array[j + gap]) {
						System.out.println("i = " + i + ", j= " + j);
						array[j] = array[j] ^ array[j + gap];
						array[j + gap] = array[j] ^ array[j + gap];
						array[j] = array[j] ^ array[j + gap];
					}
				}
			}
			System.out.println("array = " + Arrays.toString(array));
		}

		// for (int i = 2; i < array.length; i++) {
		// for (int j = i - 2; j >= 0; j -= 2) {
		// if (array[j] > array[j + 2]) {
		// System.out.println("i = " + i + ", j= " + j);
		// array[j] = array[j] ^ array[j + 2];
		// array[j + 2] = array[j] ^ array[j + 2];
		// array[j] = array[j] ^ array[j + 2];
		// }
		// }
		// }
		// System.out.println("array = " + Arrays.toString(array));
		//
		// for (int i = 1; i < array.length; i++) {
		// for (int j = i - 1; j >= 0; j -= 1) {
		// if (array[j] > array[j + 1]) {
		// System.out.println("i = " + i + ", j= " + j);
		// array[j] = array[j] ^ array[j + 1];
		// array[j + 1] = array[j] ^ array[j + 1];
		// array[j] = array[j] ^ array[j + 1];
		// }
		// }
		// }
		// System.out.println("array = " + Arrays.toString(array));
	}

	public static void shell2(int[] array) {

		for (int gap = array.length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < array.length; i++) {
				int index = i;
				int value = array[index];
				while (index - gap >= 0 && array[index - gap] > value) {
					array[index] = array[index - gap];
					index -= gap;
				}
				if (index != i) {
					array[index] = value;
				}
			}
			System.out.println("array = " + Arrays.toString(array));
		}

		// for (int i = 2; i < array.length; i++) {
		// int index = i;
		// int value = array[index];
		// while (index - 2 >= 0 && array[index - 2] > value) {
		// array[index] = array[index - 2];
		// index -= 2;
		// }
		// if (index != i) {
		// array[index] = value;
		// }
		// }
		// System.out.println("array = " + Arrays.toString(array));
		//
		// for (int i = 1; i < array.length; i++) {
		// int index = i;
		// int value = array[index];
		// while (index - 1 >= 0 && array[index - 1] > value) {
		// array[index] = array[index - 1];
		// index -= 1;
		// }
		// if (index != i) {
		// array[index] = value;
		// }
		// }
		// System.out.println("array = " + Arrays.toString(array));
	}
}
