package com.simon.datastructures.stack;

/**
 * @author simon
 * @date 2020/5/3 5:41 下午
 */
public class Calculator {

	/**
	 * 思路 1.通过一个index值来遍历我们的表达式 <br>
	 * 2.如我我们发现是一个数字,就直接入数栈 <br>
	 * 3.如果我们发现扫描到的是一个符号 就分如下情况 <br>
	 * a.如果发现当前的符号栈为空，就直接入栈<br>
	 * b.如果符号栈有操作符，就进行比较，如果当前操作符的优先级小于或等于栈中的操作符，就需要从数栈中取出两个元素进行运算
	 * 运算结果入数栈，并将当前的操作符入符号栈，如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈 <br>
	 * 4.当变道时扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号并运算<br>
	 * 5.最后在数栈只有一个数字，就是表达式的结果<br>
	 */
	public static void main(String[] args) {
		String expression = "70*2*2-5+1-5+3-4";
		LinkedStack numStack = new LinkedStack();
		LinkedStack operStack = new LinkedStack();
		int index = 0;
		char ch;
		String number = "";
		while (index < expression.length()) {
			ch = expression.charAt(index++);
			if (isNum(ch)) {
				// 数字直接入数栈
				// 减48是因为涉及到ascii码
				// 这里可能存在多位数，所以要查看一下下一位是来判是需要入栈
				if (index < expression.length() && isNum(expression.charAt(index))) {
					number += ch;
					continue;
				}
				numStack.push(number.equals("") ? (ch - 48) : Integer.parseInt(number + ch));
				number = "";
				continue;
			}
			// 操作符
			if (operStack.isEmpty()) {
				// 符号栈为空
				operStack.push(ch);
				continue;
			}
			// 符号栈不为empty
			if (Operational.parseValue(String.valueOf(ch)).getCode() > Operational
					.parseValue(String.valueOf((char) operStack.peek())).getCode()) {
				// 当前符号级别大于栈顶的符号
				operStack.push(ch);
			}
			// 取出栈顶符号 进行计算
			int num1 = numStack.pop().no;
			int num2 = numStack.pop().no;
			int result = calculate(num1, num2, (char) operStack.pop().no);
			numStack.push(result);
			operStack.push(ch);
		}

		while (true) {
			// 扫描完毕 继续计算
			if (numStack.isEmpty() || operStack.isEmpty()) {
				break;
			}
			int num1 = numStack.pop().no;
			int num2 = numStack.pop().no;
			int result = calculate(num1, num2, (char) operStack.pop().no);
			numStack.push(result);
		}
		System.out.println("计算结果为: " + numStack.pop().no);
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
