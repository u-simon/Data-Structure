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
		List<String> strings = toInFixExpression(
				Arrays.asList("1", "+", "(", "(", "2", "+", "3", ")", "*", "4", ")", "-", "5"));

		System.out.println(cal(strings));
	}

	/**
	 * 中缀表达式转后缀表达式
	 */

	public static List<String> toInFixExpression(List<String> expression) {

		Stack<String> stack = new Stack<>();
		List<String> result = new ArrayList<>(100);
		for (String exp : expression) {
			// 数字 直接放到结果
			if (isNum(exp.charAt(0))) {
				result.add(exp);
				continue;
			}
			// 括号
			if ("(".equals(exp)) {
				// 左括号
				stack.push(exp);
				continue;
			}
			if (")".equals(exp)) {
				// 右括号

				while (!"(".equals(stack.peek())) {
					result.add(stack.pop());
				}
				stack.pop();
				continue;
			}
			// 运算符 操作符栈为空 直接入栈
			if (stack.empty() || "(".equals(stack.peek())) {
				stack.push(exp);
				continue;
			}
			// 和栈顶元素比较优先级
			if (Operational.parseValue(exp).getCode() > Operational.parseValue(stack.peek())
					.getCode()) {
				stack.push(exp);
				continue;
			}
			result.add(stack.pop());
			stack.push(exp);
		}
		while (!stack.empty()){
			result.add(stack.pop());
		}
		return result;
	}

	/**
	 * 后缀表达式(逆波兰表达式)
	 *
	 * @param expression
	 * @return
	 */
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
