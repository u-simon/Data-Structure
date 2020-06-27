package com.simon.algorithm.kmp;

import java.util.Arrays;

/**
 * @author simon
 * @date 2020/6/27 11:37 上午
 */
public class KmpMatch {

	public static void main(String[] args) {
		String s1 = "BBC ABCDAB ABCDABCDABDE";
		String s2 = "ACSDSD";
		int[] next = kmpNext(s2 );
		System.out.println(Arrays.toString(next));

		System.out.println(kmpSearch(s1, s2, next));
	}

	private static int kmpSearch(String s1, String s2, int[] next) {
		for (int i = 0, j = 0; i < s1.length(); i++) {
			// s1.i位置和s2的j位置不相等
			while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
				j = next[j - 1];
			}

			// s1.i位置和s2的j位置相等 则i++ j++
			if (s1.charAt(i) == s2.charAt(j)) {
				j++;
			}
			if (j == s2.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}

	private static int[] kmpNext(String str) {
		int[] next = new int[str.length()];

		for (int i = 1, j = 0; i < str.length(); i++) {

			// 不相等就向前找到相等的位置
			while (j > 0 && str.charAt(i) != str.charAt(j)) {
				j = next[j - 1];
			}
			// 相同就找下一个位置
			if (str.charAt(i) == str.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}
}
