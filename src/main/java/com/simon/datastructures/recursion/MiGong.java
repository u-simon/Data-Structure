package com.simon.datastructures.recursion;

/**
 * @author simon
 * @date 2020/5/16 2:56 下午
 */
public class MiGong {

	public static void main(String[] args) {
		int[][] miGong = new int[8][8];
		for (int i = 0; i < miGong.length; i++) {
			miGong[0][i] = 1;
			miGong[7][i] = 1;

			miGong[i][0] = 1;
			miGong[i][7] = 1;
		}
		miGong[3][1] = 1;
		miGong[3][2] = 1;

		setWay(miGong, 1, 1);

		for (int[] ints : miGong) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}
	}


	/**
	 * 下右上左的顺序一次寻找
	 * 约定 1表示墙 2表示此路通 3表示走过但是不通
	 */
	public static boolean setWay(int[][] map, int x, int y) {
		if (map[6][6] == 2) {
			return true;
		}
		if (map[x][y] == 0) {
			map[x][y] = 2;
			if (setWay(map, x + 1, y))
				return true;
			if (setWay(map, x, y + 1))
				return true;
			if (setWay(map, x - 1, y))
				return true;
			if (setWay(map, x, y-1))
				return true;
			map[x][y] = 3;
		}
		// 当前位置不是0 则可能为1 2 3
		return false;
	}
}
