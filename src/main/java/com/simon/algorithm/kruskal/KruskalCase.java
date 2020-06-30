package com.simon.algorithm.kruskal;

import java.security.cert.PKIXReason;
import java.util.Arrays;

/**
 * @author simon
 * @date 2020/6/29 10:22 下午
 */
public class KruskalCase {

	private int edge;

	private char[] vertexs;

	private int[][] weight;

	private static final int INF = Integer.MAX_VALUE;

	public KruskalCase(char[] vertexs, int[][] weight) {
		this.vertexs = vertexs;
		this.weight = weight;

		for (int i = 0; i < weight.length; i++) {
			for (int j = i + 1; j < weight[i].length; j++) {
				if (INF != weight[i][j]) {
					this.edge++;
				}
			}
		}
	}

	public void print() {
		for (int[] array : weight) {
			System.out.println(Arrays.toString(array));
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param eData
	 */
	private void sort(EData[] eData) {

		for (int i = 0; i < eData.length - 1; i++) {
			for (int j = 0; j < eData.length - 1 - i; j++) {
				if (eData[j].weight > eData[j + 1].weight) {
					EData tem = eData[j];
					eData[j] = eData[j + 1];
					eData[j + 1] = tem;
				}
			}
		}
	}

	/**
	 * 根据顶点的值返回对应的下标
	 */
	private int getPosition(char ch) {
		for (int i = 0; i < vertexs.length; i++) {
			if (vertexs[i] == ch) {
				return i;
			}
		}
		return -1;
	}

	public EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[this.edge];
		for (int i = 0; i < vertexs.length; i++) {
			for (int j = i + 1; j < vertexs.length; j++) {
				if (weight[i][j] != INF) {
					edges[index++] = new EData(vertexs[i], vertexs[j], weight[i][j]);
				}
			}
		}
		return edges;
	}

	private int getEnd(int[] ends, int i ){
		while (ends[i] != 0){
			i = ends[i];
		}
		return i;
	}


	public void kruskal(){
		int index = 0;//表示最后结果的索引
		int[] ends = new int[edge];// 用于保存已有最小生成树中的每个顶点在最小生成树中的重点
		// 创建数组来保存结果
		EData[] result = new EData[this.edge];

		//获取所有边的集合
		EData[] edges = getEdges();

		// 按照边的权重先进行排序
		sort(edges);

		// 遍历边的数组 将边添加到最小生成树中
		for (int i = 0; i < edge; i++) {
			// 获取第i条边
			EData edge = edges[i];
			// 获取边两个顶点的位置
			int start = getPosition(edge.start);
			int end = getPosition(edge.end);

			// 获取两个顶点在最小生成树的终点
			int m = getEnd(ends, start);
			int n = getEnd(ends, end);
			// 判断是否构成回路
			if (m != n){
				ends[m] = n; //  设置m在已有最小生成树中的终点<E, F>
				result[index++] = edges[i];
			}
		}

		System.out.println(Arrays.toString(result));
	}

	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] weight = {
				{0, 12, INF, INF, INF, 16, 14},
				{12, 0, 10, INF, INF, 7, INF},
				{INF, 10, 0, 3, 5, 6, INF},
				{INF, INF, 3, 0, 4, INF, INF},
				{INF, INF, 5, 4, 0, 2, 8},
				{16, 7, 6, INF, 2, 0, 9},
				{14, INF, INF, INF, 8, 9, 0}
		};
		KruskalCase kruskalCase = new KruskalCase(vertexs, weight);
		kruskalCase.print();

		kruskalCase.kruskal();

	}
}


class EData {
	char start;
	char end;
	int weight;

	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EData{" +
				"start=" + start +
				", end=" + end +
				", weight=" + weight +
				'}';
	}
}
