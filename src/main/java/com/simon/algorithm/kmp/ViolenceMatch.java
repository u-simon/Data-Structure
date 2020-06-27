package com.simon.algorithm.kmp;

/**
 * @author simon
 * @date 2020/6/27 10:23 上午
 */
public class ViolenceMatch {

	public static void main(String[] args) {
		String s1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
		String s2 = "尚硅谷你尚硅你";
		System.out.println(violenceMatch(s1, s2));
	}

	private static int violenceMatch(String s1, String s2) {

		char[] s1Ch = s1.toCharArray();
		char[] s2Ch = s2.toCharArray();

		int s1Len = s1.length();
		int s2Len = s2.length();

		int i = 0, j = 0;

		while (i < s1Len && j < s2Len) {
			if (s1Ch[i] == s2Ch[j]) {
				i++;
				j++;
				continue;
			}
			i = i - (j - 1);
			j = 0;

		}

		if (j == s2Len) {
			return i - j;
		}
		return -1;
	}
}
