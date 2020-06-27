package com.simon.algorithm.prim;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/6/27 5:07 下午
 */
public class PrimAlgorithm {


	public static void main(String[] args) {

		char[] data = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int verxs = data.length;
		int[][] weight = new int[][] {{100000, 5, 7, 100000, 100000, 100000, 2},
				{5, 100000, 100000, 9, 100000, 100000, 3},
				{7, 100000, 100000, 100000, 8, 100000, 100000},
				{100000, 9, 100000, 100000, 100000, 4, 100000},
				{100000, 100000, 8, 100000, 100000, 5, 4},
				{100000, 100000, 100000, 4, 5, 100000, 6}, {2, 3, 100000, 100000, 4, 6, 100000}};
		MinTree minTree = new MinTree();
		minTree.createMGraph(verxs, data, weight);

		minTree.showGraph();

		minTree.prim(2);
	}
}


class MinTree {

	MGraph mGraph;

	public void createMGraph(int verxs, char[] data, int[][] weight) {
		mGraph = new MGraph(verxs, data, weight);
	}

	public void showGraph() {
		for (int[] ints : mGraph.weight) {
			System.out.println(Arrays.toString(ints));
		}
	}

	public void prim(int index) {
		// 标记第i个结点是否被访问过
		boolean[] visited = new boolean[mGraph.verxs];
		// 标记index为反问过
		visited[index] = true;

		// h1,h2记录两个顶点的坐标
		int h1 = -1, h2 = -1;

		int minWeight;

		// 因为有verxs个顶点 所以需要生成verxs-1条边把所有顶点连接起来
		for (int i = 1; i < mGraph.verxs; i++) {
			minWeight = 100000;
			// 从第一个被访问的节点开始 寻找他的所有连通的节点 求出最小的权重
			for (int j = 0; j < mGraph.verxs; j++) {
				for (int k = 0; k < mGraph.verxs; k++) {
					// visited[j] 表示被访问过 visited[k] 表示没有被访问过 并且这个边的权重最小
					if (visited[j] && !visited[k] && minWeight > mGraph.weight[j][k]) {
						minWeight = mGraph.weight[j][k];
						h1 = j;
						h2 = k;
					}
				}
			}

			System.out
					.println("边<" + mGraph.data[h1] + "," + mGraph.data[h2] + ">权值 : " + minWeight);
			// 设置这个节点被访问过
			visited[h2] = true;

		}

	}
}


class MGraph {
	int verxs;

	char[] data;

	int[][] weight;

	public MGraph(int verxs, char[] data, int[][] weight) {
		this.verxs = verxs;
		this.data = data;
		this.weight = weight;
	}
}
