package com.simon.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author simon
 * @date 2020/6/22 10:14 下午
 */
public class Graph {

	private List<String> vertexes;

	private int[][] edges;

	private int numOfEdge;

	private boolean[] isVisited;

	public Graph(int n) {
		this.vertexes = new ArrayList<>(n);
		this.edges = new int[n][n];
		this.numOfEdge = 0;
	}

	private int getFirstNeighbor(int index) {
		for (int i = 0; i < vertexes.size(); i++) {
			if (edges[index][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	private int getNextNeighbor(int i, int w) {
		for (int j = w + 1; j < vertexes.size(); j++) {
			if (edges[i][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * 广度优先算法 Broad first search
	 */

	public void bfs() {
		this.isVisited = new boolean[vertexes.size()];
		for (int i = 0; i < vertexes.size(); i++) {
			if (!isVisited[i]) {
				bfs(isVisited, i);
			}
		}
	}

	/**
	 * 步骤:</br>
	 * 1.访问初始节点v并标记节点v为已访问</br>
	 * 2.结点v入队列</br>
	 * 3.当队列非空时 继续执行 否则算法结束</br>
	 * 4.出队列 取得队头结点u</br>
	 * 5.查找节点u的第一个邻接结点</br>
	 * 6.若节点u的邻接结点w不存在 则转到步骤3 否则循环执行以下3个步骤</br>
	 * -> a.若结点w尚未被访问 则访问结点w并标记为已访问</br>
	 * -> b.节点w入队列</br>
	 * -> c.查找节点u的继w邻接结点后的下一个邻接结点w转到步骤6</br>
	 */
	public void bfs(boolean[] isVisited, int i) {
		int u;
		int w;
		LinkedList<Integer> queue = new LinkedList<>();

		// 访问结点v
		System.out.print(vertexes.get(i) + "=>");
		// 结点v标记为已访问
		isVisited[i] = true;
		// 结点v入队列
		queue.addLast(i);
		// 队列不为空
		while (!queue.isEmpty()) {
			// 取得队列头结点
			u = queue.removeFirst();
			// 查找u的第一个邻接结点
			w = getFirstNeighbor(u);
			while (w != -1) {
				// 邻接结点存在
				if (!isVisited[w]) {
					// 6.a
					System.out.print(vertexes.get(w) + "=>");
					isVisited[w] = true;
					// 6.b
					queue.addLast(w);
				}
				// 6.c
				w = getNextNeighbor(u, w);
			}
		}
	}

	/**
	 * 深度优先算法 depth first search
	 */
	public void dfs() {
		this.isVisited = new boolean[vertexes.size()];
		for (int i = 0; i < vertexes.size(); i++) {
			if (!isVisited[i]) {
				// 结点没有被输出过
				dfs(isVisited, i);
			}
		}
	}

	/**
	 * 步骤</br>
	 * 1.访问初始节点v 并标记结点v为已访问 </br>
	 * 2.查找节点v的第一个邻接结点w </br>
	 * 3.若w存在则继续执行步骤4 如果w不存在则回到第一步 将从v的下一个节点继续 </br>
	 * 4.若w未被访问、对w进行深度优先遍历递归(即把w当做另一个v然后进行步骤123)</br>
	 * 5.查找结点v的w邻接结点的下一个邻接结点转到步骤3</br>
	 */
	private void dfs(boolean[] isVisited, int i) {
		// 输出当前节点
		System.out.print(vertexes.get(i) + " -> ");
		// 标记当前节点被访问过
		isVisited[i] = true;
		// 查找当前节点的第一个邻接结点
		int w = getFirstNeighbor(i);
		while (w != -1) {
			// 节点存在
			if (!isVisited[w]) {
				// 对w进行深度优先算法
				dfs(isVisited, w);
			}
			// 节点已经被访问, 查找这个节点下一个节点 也就是i的下一个邻接结点
			w = getNextNeighbor(i, w);
		}
	}

	public void showEdge() {
		for (int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}

	/**
	 * 插入节点
	 */
	public void insertVertex(String vertex) {
		vertexes.add(vertex);
	}

	public void insertEdge(int v1, int v2, int weight) {
		this.edges[v1][v2] = weight;
		this.edges[v2][v1] = weight;
		this.numOfEdge++;
	}

	public static void main(String[] args) {
		String[] vertex = {"A", "B", "C", "D", "E"};
		Graph graph = new Graph(vertex.length);
		for (String s : vertex) {
			graph.insertVertex(s);
		}
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);

		graph.showEdge();
		graph.dfs();
		System.out.println();

		graph.bfs();
	}
}
