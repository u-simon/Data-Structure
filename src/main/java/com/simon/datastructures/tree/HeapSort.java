package com.simon.datastructures.tree;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/6/7 6:33 下午
 */
public class HeapSort {


	public static void main(String[] args) {
		int[] array = {4, 6, 8, 5, 9, -1, -100, 234, 54, -999, 0 , 4};
		sort(array);
		System.out.println("排序后 " + Arrays.toString(array));
	}

	private static void sort(int[] array) {

		// 先构造大顶推 i 从最后一个叶子节点向前遍历 所有的叶子节点
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			adjust(array, i, array.length);
		}

		// 交换堆顶元素和堆尾元素 将最大元素放到尾部
		// 重新调整结构，然后继续交换 反复执行 调整+交换 直到整个数组有序

		for (int j = array.length - 1; j > 0; j--) {
			// 交换堆顶和堆尾元素
			array[0] = array[0] ^ array[j];
			array[j] = array[0] ^ array[j];
			array[0] = array[0] ^ array[j];

			// 继续调整成大顶堆 因为堆顶元素每次都和堆尾交换 所以每次调整都需要从堆顶开始
			// 不断到把最大元素放到堆尾 所以 堆尾指针要前移
			adjust(array, 0, j);
		}

	}

	/**
	 * 功能 完成将 以 i 对应的非叶子节点的树调成大顶堆
	 *
	 * @param array 待调整的数组
	 * @param i 表示非叶子节点在数组中的索引
	 * @param length 表示对多少个元素进行调整 length是在逐渐减少
	 */
	private static void adjust(int[] array, int i, int length) {
		// 记录初始非叶子节点的数据 因为要进行交换
		int temp = array[i];

		// k = 2 * i + 1 表示i的左子节点 k = 2*k+1 代表k的左子节点
		for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
			// i的右子节点为 2*k+2 k为i的左子节点 是2 * i + 1 所以 k + 1代表右子节点
			if (k + 1 < length && array[k] < array[k + 1]) {
				k++;
			}
			if (array[k] > temp) {
				// 子节点大于父节点的值则交换
				array[i] = array[k];
				// i指向k 继续查看k的子节点
				i = k;
			} else {
				break;
			}
		}
		// 把原先i位置的值temp替换到子节点的位置 因为进行了交换
		array[i] = temp;
	}
}
