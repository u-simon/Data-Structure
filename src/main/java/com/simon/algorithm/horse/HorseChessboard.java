package com.simon.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author simon
 * @date 2020/7/2 9:37 下午
 */
public class HorseChessboard {



	private static int X; // 棋盘的列数
	private static int Y; // 棋盘的行数

	// 创建一个数组，标记棋盘的各个位置是否被访问过
	private static boolean[] visited;

	// 使用一个属性，标记是够棋盘的所有位置都被访问
	private static boolean finished; // true 表示成功

	public static void main(String[] args) {
		X = 8;
		Y = 8;
		int row = 5;
		int column = 5;
		int[][] chessboard = new int[X][Y];
		visited = new boolean[X * Y];
		long begin = System.currentTimeMillis();
		traversalChessboard(chessboard, row - 1, column - 1, 1);
		long end = System.currentTimeMillis();
		System.out.println("ms " + (end - begin));

		for (int[] ints : chessboard) {
			System.out.println(Arrays.toString(ints));
		}
	}


	public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
		// 先把row column 标记 step
		chessboard[row][column] = step;
		// 标记该位置已经被访问
		visited[row * X + column] = true;
		// 获取当前位置可以走的下一个位置的集合
		ArrayList<Point> next = next(new Point(column, row));

		// 对ps进程排序，排序的规则是对ps的所以point的对象的下一步的位置的数目,进行非递减排序(贪心算法进行优化-减少每一步回溯的次数)
		next.sort(Comparator.comparingInt(p -> next(p).size()));
		while (!next.isEmpty()) {
			// 取出下一个可以走的位置
			Point point = next.remove(0);
			// 判断该点是否被访问过
			if (!visited[point.y * X + point.x]) {// 说明没有被访问过
				traversalChessboard(chessboard, point.y, point.x, step + 1);
			}
		}
		// 判断马儿是否完成了任务 s使用step和应该走的步数比较
		// 如果没有达到数量，则表示没有完成任务，将整个棋盘置0
		// 说明 step< X*Y 成立的情况有两种
		// 1.棋盘目前位置仍然没有走完
		// 2. 棋盘处于一个回溯的过程
		if (step < X * Y && !finished) {
			chessboard[row][column] = 0;
			visited[row * X + column] = false;
		} else {
			finished = true;
		}
	}


	private static ArrayList<Point> next(Point curr) {
		ArrayList<Point> ps = new ArrayList<>();
		Point p1 = new Point();

		// 表示马儿可以走5这个位置
		if ((p1.x = curr.x - 2) >= 0 && (p1.y = curr.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		// 表示马儿可以走6这个位置
		if ((p1.x = curr.x - 1) >= 0 && (p1.y = curr.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		// 表示马儿可以走7这个位置
		if ((p1.x = curr.x + 1) < X && (p1.y = curr.y - 2) >= 0) {
			ps.add(new Point(p1));
		}
		// 表示马儿可以走0这个位置
		if ((p1.x = curr.x + 2) < X && (p1.y = curr.y - 1) >= 0) {
			ps.add(new Point(p1));
		}
		// 表示马儿可以走1这个位置
		if ((p1.x = curr.x + 1) < X && (p1.y = curr.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		// 表示马儿可以走2这个位置
		if ((p1.x = curr.x + 1) < X && (p1.y = curr.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		// 表示马儿可以走3这个位置
		if ((p1.x = curr.x - 1) >= 0 && (p1.y = curr.y + 2) < Y) {
			ps.add(new Point(p1));
		}
		// 表示马儿可以走4这个位置
		if ((p1.x = curr.x - 2) >= 0 && (p1.y = curr.y + 1) < Y) {
			ps.add(new Point(p1));
		}
		return ps;
	}
}
