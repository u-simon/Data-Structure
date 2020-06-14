package com.simon.datastructures.tree.huffman;


import java.util.*;

/**
 * @author simon
 * @date 2020/6/11 10:03 下午
 */
public class HuffmanEncode {

	private static Map<Byte, String> huffmanCodes = new HashMap<>();


	public static void main(String[] args) {
		String content = "i like like like java do you like a java";
		byte[] zip = huffmanZip(content.getBytes());
		// System.out.println(Arrays.toString(zip));

		byte[] decode = decode(zip, huffmanCodes);
		System.out.println(new String(decode));
	}



	private static byte[] decode(byte[] dates, Map<Byte, String> huffmanTable) {
		// 将字节数组转换成 str
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dates.length; i++) {
			// 最后一位不进行拼接
			sb.append(byte2String((i != dates.length - 1), dates[i]));
		}
		// System.out.println(sb.toString());

		// 赫夫曼编码表反转
		Map<String, Byte> map = new HashMap<>();
		for (Map.Entry<Byte, String> entry : huffmanTable.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		// System.out.println(map);

		// 进行解码
		List<Byte> list = new ArrayList<>();
		StringBuilder key = new StringBuilder();;
		// 将字符串解码成byte数组
		for (int i = 0; i < sb.length(); i++) {
			key.append(sb.charAt(i));
			Byte aByte = map.get(key.toString());
			if (aByte != null) {
				list.add(aByte);
				key = new StringBuilder();
			}
		}
		byte[] bytes = new byte[list.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = list.get(i);
		}
		return bytes;
	}

	/**
	 * 解码时 将字符数组 转换成原先的字符串
	 */
	private static String byte2String(boolean flag, byte data) {
		int temp = data;
		if (flag) {
			// 正数 前面0会被省略掉 不够8位数
			temp |= 256;
		}
		String str = Integer.toBinaryString(temp);
		if (flag) {
			return str.substring(str.length() - 8);
		}
		return str;
	}

	private static byte[] huffmanZip(byte[] bytes) {
		// 将数据构造成结点数据 data为字符 weight出现的次数
		List<DataNode> nodes = getNodes(bytes);
		// 构造哈夫曼数
		DataNode treeify = treeify(nodes);
		// 生成哈夫曼码表
		getCodes(treeify);
		// 将字符数据进行赫夫曼编码
		return zip(bytes, huffmanCodes);
	}


	public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanTable) {
		StringBuilder sb = new StringBuilder();
		// 把原先的字节数组转换成赫夫曼编码后的字符串
		for (byte aByte : bytes) {
			sb.append(huffmanTable.get(aByte));
		}
		// 把字符串转换成字节数组
		int len = (sb.length() + 7) / 8;
		byte[] huffmanBytes = new byte[len];

		int index = 0;
		// 每8位转换成byte类型
		for (int i = 0; i < sb.length(); i += 8) {
			String str;
			if (i + 8 > sb.length()) {
				str = sb.substring(i);
			} else {
				str = sb.substring(i, i + 8);
			}
			huffmanBytes[index++] = (byte) Integer.parseInt(str, 2);
		}
		return huffmanBytes;
	}


	public static Map<Byte, String> getCodes(DataNode root) {
		if (root == null) {
			return null;
		}
		StringBuilder stringBuffer = new StringBuilder();
		// 向左递归
		getCodes(root.left, "0", stringBuffer);
		// 向右递归
		getCodes(root.right, "1", stringBuffer);

		return huffmanCodes;
	}

	/**
	 * 将传入的结点的所有叶子节点的赫夫曼编码得到 并放入huffmanCodes中
	 * 
	 * @param node 传入的节点
	 * @param code 路径 0-左子节点 1-右子节点
	 * @param stringBuilder 用户拼接路径
	 */
	public static void getCodes(DataNode node, String code, StringBuilder stringBuilder) {
		StringBuilder sb = new StringBuilder(stringBuilder);
		sb.append(code);
		if (node != null) {
			if (node.data == null) {
				// 当前节点为非叶子结点
				// 向左递归
				getCodes(node.left, "0", sb);
				// 向右递归
				getCodes(node.right, "1", sb);
				return;
			}
			// 叶子结点 将路径保存到map中
			huffmanCodes.put(node.data, sb.toString());
		}
	}

	/**
	 * 构造赫夫曼树
	 */
	public static DataNode treeify(List<DataNode> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);

			DataNode left = nodes.remove(0);
			DataNode right = nodes.remove(0);
			DataNode parent = new DataNode(null, left.weight + right.weight);
			parent.left = left;
			parent.right = right;

			nodes.add(parent);
		}
		return nodes.get(0);
	}


	/**
	 * 把数据构造成节点
	 */
	private static List<DataNode> getNodes(byte[] dates) {
		List<DataNode> nodes = new ArrayList<>();
		Map<Byte, Integer> nodeMap = new HashMap<>();
		// 计算字符对应的个数
		for (byte date : dates) {
			Integer count = nodeMap.get(date);
			if (count == null) {
				count = 0;
			}
			nodeMap.put(date, count + 1);
		}
		// byte字节 对应的次数 构造对应的结点
		for (Map.Entry<Byte, Integer> entry : nodeMap.entrySet()) {
			nodes.add(new DataNode(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}
}


class DataNode implements Comparable<DataNode> {

	public Byte data;

	public int weight;

	public DataNode left;

	public DataNode right;

	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	@Override
	public String toString() {
		return "DataNode{" + "data=" + data + ", weight=" + weight + '}';
	}

	public DataNode(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	@Override
	public int compareTo(DataNode o) {
		return this.weight - o.weight;
	}
}
