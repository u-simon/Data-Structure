package com.simon.datastructures.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author simon
 * @date 2020/5/3 7:00 下午
 */
public class PolandNotation {

	public static void main(String[] args) {
		String expression = "30 4 + 5 * 6 -";
		System.out.println(cal(Arrays.asList(expression.split(" "))));
	}


	public static String cal(List<String> expression) {
		Stack<String> stack = new Stack<>();
		for (String str : expression) {
			if (isNum(str.charAt(0))) {
				stack.push(str);
				continue;
			}
			int num1 = Integer.parseInt(stack.pop());
			int num2 = Integer.parseInt(stack.pop());
			int calculate = calculate(num1, num2, str.charAt(0));
			stack.push(String.valueOf(calculate));
		}
		return stack.pop();
	}

	public static int calculate(int num1, int num2, char ope) {
		switch (ope) {
			case '+':
				return num2 + num1;
			case '-':
				return num2 - num1;
			case '*':
				return num2 * num1;
			case '/':
				return num2 / num1;
			default:
				throw new RuntimeException("不存在的运算符");
		}
	}

	public static boolean isNum(char ch) {
		return ch >= 48 && ch <= 57;
	}
}
