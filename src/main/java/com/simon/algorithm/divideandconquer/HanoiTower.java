package com.simon.algorithm.divideandconquer;

/**
 * @author simon
 * @date 2020/6/26 9:57 上午
 */
public class HanoiTower {

	public static void main(String[] args) {
		hanoiTower(4, 'A', 'B', 'C');
	}

	/**
	 * 
	 * @param num 表示有几个盘
	 * @param a 第一个柱子
	 * @param b 第二个柱子
	 * @param c 第三个柱子
	 */
	private static void hanoiTower(int num, char a, char b, char c) {
		if (num == 1) {
			System.out.println("第" + num + "盘从 " + a + "->" + c);
		} else {
			// 如果n>=2的情况我们可以看做两个盘 1 最下面的盘 2上面的所有盘

			// 先把做上面的盘A->B
			hanoiTower(num - 1, a, c, b);
			// 把最下面的盘 A->C
			System.out.println("第" + num + "盘从 " + a + "->" + c);
			// 把B塔的左右盘从B->C
			hanoiTower(num -1 , b, a, c);
		}
	}
}
