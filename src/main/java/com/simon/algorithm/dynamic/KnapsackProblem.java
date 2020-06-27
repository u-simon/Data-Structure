package com.simon.algorithm.dynamic;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/6/27 9:25 上午
 */
public class KnapsackProblem {

	public static void main(String[] args) {
		int[] w = {1, 4, 3}; // 商品的重量
		int[] val = {1500, 3000, 2000}; // 商品的价值 就是val[i] 就是前面讲的v[i]

		int m = 4; // 背包的容量
		int n = val.length;// 物品的个数

		int[][] v = new int[n + 1][m + 1];
		for (int i = 0; i < v.length; i++) {
			// 初始化第一行第一列为0
			v[i][0] = 0;
			v[0][i] = 0;
		}

		int[][] path = new int[n + 1][m + 1];

		for (int i = 1; i < v.length; i++) {
			for (int j = 1; j < v[i].length; j++) { // j 表示每一行的重量
				if (w[i - 1] > j) { // 公式的i是从0开始的 程序的i是从1开始的所以这里要进行i-1
					v[i][j] = v[i - 1][j];
				} else {
					// 公式的i是从0开始的 程序的i是从1开始的所以这里要进行i-1
					if (v[i - 1][j] < (val[i - 1] + v[i - 1][j - w[i - 1]])) {
						v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
						path[i][j] = 1;
						continue;
					}
					v[i][j] = v[i - 1][j];
				}
			}
		}

		for (int i = 0; i < v.length; i++) {
			System.out.println(Arrays.toString(v[i]));
		}


		int i = path.length - 1;
		int j = path[0].length - 1;
		while (i > 0 && j > 0) {
			if (path[i][j] == 1) {
				System.out.println("商品 : " + i);
				j -= w[i - 1];
			}
			i--;
		}
	}
}
