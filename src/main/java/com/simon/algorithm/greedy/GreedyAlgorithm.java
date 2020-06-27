package com.simon.algorithm.greedy;

import java.util.*;

/**
 * @author simon
 * @date 2020/6/27 4:02 下午
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {
		Map<String, Set<String>> broadcasts = new HashMap<>();
		Set<String> k1 = new HashSet<>(Arrays.asList("北京", "上海", "天津"));
		Set<String> k2 = new HashSet<>(Arrays.asList("广州", "北京", "深圳"));
		Set<String> k3 = new HashSet<>(Arrays.asList("成都", "上海", "杭州"));
		Set<String> k4 = new HashSet<>(Arrays.asList("上海", "天津"));
		Set<String> k5 = new HashSet<>(Arrays.asList("杭州", "大连"));
		broadcasts.put("k1", k1);
		broadcasts.put("k2", k2);
		broadcasts.put("k3", k3);
		broadcasts.put("k4", k4);
		broadcasts.put("k5", k5);

		Set<String> allAreas = new HashSet<>();
		allAreas.addAll(k1);
		allAreas.addAll(k2);
		allAreas.addAll(k3);
		allAreas.addAll(k4);
		allAreas.addAll(k5);

		List<String> selects = new ArrayList<>();

		String maxKey = null;
		// 临时表来保存
		Set<String> tempSet = new HashSet<>();
		while (allAreas.size() > 0) {
			// 重置maxKey
			maxKey = null;
			for (Map.Entry<String, Set<String>> entry : broadcasts.entrySet()) {
				// 清空 临时表
				tempSet.clear();
				tempSet.addAll(entry.getValue());
				// 求出相同的数量
				tempSet.retainAll(allAreas);
				if (tempSet.size() > 0
						&& (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
					maxKey = entry.getKey();
				}
			}
			if (maxKey != null){
				selects.add(maxKey);
				//清除掉已经选择的地址
				allAreas.removeAll(broadcasts.get(maxKey));
			}
		}
		System.out.println("最终的结果 ： " + selects);
	}
}
