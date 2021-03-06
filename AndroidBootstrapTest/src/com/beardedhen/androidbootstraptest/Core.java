package com.beardedhen.androidbootstraptest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

public class Core {
	/*
	 * public static void main(String args[]) { String str = creatHard(); Core
	 * test = new Core(); System.out.println(str); double result =
	 * test.computeWithVector(str); System.out.println(result); }
	 */

	// 生成简单题目
	public static String createasy() {
		int num1, num2;
		String[] op = { "+", "-", "×", "÷" };
		Random random = new Random();
		int randop = random.nextInt(3);
		String result = pdivision(20);
		if (result == null) {
			if (randop < 1) {
				num1 = random.nextInt(50);
				num2 = random.nextInt(50);

			} else {
				num1 = random.nextInt(10);
				num2 = random.nextInt(10);
			}
			return num1 + op[randop] + num2;
		}
		return result;
	}

	// 生成中等难度题目
	public static String creatMid() {
		int num1, num2, num3;
		String[] op = { "+", "-", "×", "÷" };
		Random random = new Random();
		int randop1 = random.nextInt(3);
		int randop2 = random.nextInt(3);
		String result = pdivision(50);
		if (result == null) {
			if (randop1 < 1 && randop2 < 1) {
				num1 = random.nextInt(100);
				num2 = random.nextInt(100);
				num3 = random.nextInt(100);
			} else {
				num1 = random.nextInt(20);
				num2 = random.nextInt(20);
				num3 = random.nextInt(20);
			}
			return num1 + op[randop1] + num2 + op[randop2] + num3;
		}
		num1 = random.nextInt(20);
		return num1 + op[randop1] + result;

	}

	// 生成困难题目
	public static String creatHard() {
		int num1, num2, num3, num4;
		String[] op = { "+", "-", "×", "÷" };
		Random random = new Random();
		int randop1 = random.nextInt(3);
		int randop2 = random.nextInt(3);
		int randop3 = random.nextInt(2);
		String result = pdivision(50);
		String brack[] = brackets();
		if (result == null) {
			if (randop1 < 1 && randop2 < 1 && randop3 < 1) {
				num1 = random.nextInt(100);
				num2 = random.nextInt(100);
				num3 = random.nextInt(100);
				num4 = random.nextInt(100);
			} else {
				num1 = random.nextInt(20);
				num2 = random.nextInt(20);
				num3 = random.nextInt(20);
				num4 = random.nextInt(20);
			}
			return brack[0] + num1 + op[randop1] + brack[1] + num2
					+ op[randop2] + num3 + brack[2] + op[randop3] + num4
					+ brack[3];
		}
		num1 = random.nextInt(20);
		num2 = random.nextInt(20);
		String res[] = nsort(String.valueOf(num1), String.valueOf(num2), result);

		return res[0] + op[randop1] + res[1] + op[randop2] + res[2];

	}

	// 进行随机排序
	public static String[] nsort(String num1, String num2, String num3) {
		String[] arr = new String[] { num1, num2, num3 };
		List list = Arrays.asList(arr);
		Collections.shuffle(list);
		String res[] = new String[4];
		for (int i = 0; i < 3; i++)
			res[i] = (String) list.get(i);
		return res;
	}

	// 插入括号
	public static String[] brackets() {
		int num;
		Random random = new Random();
		num = random.nextInt(10);
		String brack[] = new String[4];
		if (num > 0 && num < 5) {
			brack[random.nextInt(2)] = "(";
			brack[2 + random.nextInt(2)] = ")";
		}
		for (int i = 0; i < 4; i++) {
			if (brack[i] == null)
				brack[i] = "";
		}
		return brack;
	}

	// 生成除法
	public static String creatdivision(int max) {
		int num1, num2;
		Random random = new Random();
		num1 = random.nextInt(max);
		num2 = 1 + random.nextInt(max);
		while (num1 % num2 != 0) {
			num1 = random.nextInt(max);
			num2 = random.nextInt(max);
			if (num2 == 0)
				num2 = 2;
		}
		return num1 + "÷" + num2;
	}
	// 生成除法
		public  String creatchu(int max) {
			int num1, num2;
			Random random = new Random();
			num1 = random.nextInt(max);
			num2 = 1 + random.nextInt(max);
			while (num1 % num2 != 0) {
				num1 = random.nextInt(max);
				num2 = random.nextInt(max);
				if (num2 == 0)
					num2 = 2;
			}
			return num1 + "÷" + num2;
		}
	// 生成除法几率
	public static String pdivision(int max) {
		Random random = new Random();
		int num = random.nextInt(10);
		if (num < 4)
			return creatdivision(max);
		else
			return null;
	}

	/**
	 * 利用java.util.Vector计算四则运算字符串表达式的值，如果抛出异常，则说明表达式有误，这里就没有控制
	 * 
	 * @param computeExpr
	 *            四则运算字符串表达式
	 * @return 计算结果
	 */
	public double computeWithVector(String computeExpr) {
		StringTokenizer tokenizer = new StringTokenizer(computeExpr, "+-×÷()",
				true);
		Vector<Double> nums = new Vector<Double>();
		Vector<Operator> operators = new Vector<Operator>();
		Map<String, Operator> computeOper = this.getComputeOper();
		Operator curOper;
		String currentEle;
		while (tokenizer.hasMoreTokens()) {
			currentEle = tokenizer.nextToken().trim();
			if (!"".equals(currentEle)) {// 只处理非空字符
				if (this.isNum(currentEle)) { // 数字
					nums.add(Double.valueOf(currentEle));
				} else { // 非数字，即括号或者操作符
					curOper = computeOper.get(currentEle);
					if (curOper != null) { // 是运算符
						// 运算列表不为空且之前的运算符优先级较高则先计算之前的优先级
						while (!operators.isEmpty()
								&& operators.lastElement().priority() >= curOper
										.priority()) {
							compute(nums, operators);
						}
						// 把当前运算符放在运算符队列的末端
						operators.add(curOper);
					} else { // 括号
						if ("(".equals(currentEle)) { // 左括号时直接放入操作列表中
							operators.add(Operator.BRACKETS);
						} else {// 当是右括号的时候就把括号里面的内容执行了。
							// 循环执行括号里面的内容直到遇到左括号为止。试想这种情况(2+5*2)
							while (!operators.lastElement().equals(
									Operator.BRACKETS)) {
								compute(nums, operators);
							}
							// 移除左括号
							operators.remove(operators.size() - 1);
						}
					}
				}
			}
		}
		// 经过上面代码的遍历后最后的应该是nums里面剩两个数或三个数，operators里面剩一个或两个运算操作符
		while (!operators.isEmpty()) {
			compute(nums, operators);
		}
		return nums.firstElement();
	}

	public  String creatadd() {
		// TODO Auto-generated method stub
		int num1, num2;
		Random random = new Random();
		num1 = random.nextInt(100);
		num2 = random.nextInt(100);
		String a = String.valueOf(num1);
		String b = String.valueOf(num2);
		return a + "+" + b;
	}

	/* 减法字符串输出 */
	public  String creatjian() {
		// TODO Auto-generated method stub
		int num1, num2;
		Random random = new Random();
		num1 = random.nextInt(100);
		num2 = random.nextInt(100);
		String a = String.valueOf(num1);
		String b = String.valueOf(num2);
		return a + "-" + b;
	}

	/* 乘法法字符串输出 */
	public  String creatcheng() {
		// TODO Auto-generated method stub
		int num1, num2;
		Random random = new Random();
		num1 = random.nextInt(10);
		num2 = random.nextInt(10);
		String a = String.valueOf(num1);
		String b = String.valueOf(num2);
		return a + "×" + b;
	}

	/**
	 * 利用java.util.Stack计算四则运算字符串表达式的值，如果抛出异常，则说明表达式有误，这里就没有控制
	 * java.util.Stack其实也是继承自java.util.Vector的。
	 * 
	 * @param computeExpr
	 *            四则运算字符串表达式
	 * @return 计算结果
	 */
	public double computeWithStack(String computeExpr) {
		// 把表达式用运算符、括号分割成一段一段的，并且分割后的结果包含分隔符
		StringTokenizer tokenizer = new StringTokenizer(computeExpr, "+-×/()",
				true);
		Stack<Double> numStack = new Stack<Double>(); // 用来存放数字的栈
		Stack<Operator> operStack = new Stack<Operator>(); // 存放操作符的栈
		Map<String, Operator> computeOper = this.getComputeOper(); // 获取运算操作符
		String currentEle; // 当前元素
		while (tokenizer.hasMoreTokens()) {
			currentEle = tokenizer.nextToken().trim(); // 去掉前后的空格
			if (!"".equals(currentEle)) { // 只处理非空字符
				if (this.isNum(currentEle)) { // 为数字时则加入到数字栈中
					numStack.push(Double.valueOf(currentEle));
				} else { // 操作符
					Operator currentOper = computeOper.get(currentEle);// 获取当前运算操作符
					if (currentOper != null) { // 不为空时则为运算操作符
						while (!operStack.empty()
								&& operStack.peek().priority() >= currentOper
										.priority()) {
							compute(numStack, operStack);
						}
						// 计算完后把当前操作符加入到操作栈中
						operStack.push(currentOper);
					} else {// 括号
						if ("(".equals(currentEle)) { // 左括号时加入括号操作符到栈顶
							operStack.push(Operator.BRACKETS);
						} else { // 右括号时, 把左括号跟右括号之间剩余的运算符都执行了。
							while (!operStack.peek().equals(Operator.BRACKETS)) {
								compute(numStack, operStack);
							}
							operStack.pop();// 移除栈顶的左括号
						}
					}
				}
			}
		}
		// 经过上面代码的遍历后最后的应该是nums里面剩两个数或三个数，operators里面剩一个或两个运算操作符
		while (!operStack.empty()) {
			compute(numStack, operStack);
		}
		return numStack.pop();
	}

	/**
	 * 判断一个字符串是否是数字类型
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNum(String str) {
		String numRegex = "^\\d+(\\.\\d+)?$"; // 数字的正则表达式
		return Pattern.matches(numRegex, str);
	}

	/**
	 * 获取运算操作符
	 * 
	 * @return
	 */
	private Map<String, Operator> getComputeOper() {
		return new HashMap<String, Operator>() { // 运算符
			private static final long serialVersionUID = 7706718608122369958L;
			{
				put("+", Operator.PLUS);
				put("-", Operator.MINUS);
				put("×", Operator.MULTIPLY);
				put("÷", Operator.DIVIDE);
			}
		};
	}

	/**
	 * 取nums的最后两个数字，operators的最后一个运算符进行运算，然后把运算结果再放到nums列表的末端
	 * 
	 * @param nums
	 * @param operators
	 */
	private void compute(Vector<Double> nums, Vector<Operator> operators) {
		Double num2 = nums.remove(nums.size() - 1); // 第二个数字，当前队列的最后一个数字
		Double num1 = nums.remove(nums.size() - 1); // 第一个数字，当前队列的最后一个数字
		Double computeResult = operators.remove(operators.size() - 1).compute(
				num1, num2); // 取最后一个运算符进行计算
		nums.add(computeResult); // 把计算结果重新放到队列的末端
	}

	/**
	 * 取numStack的最顶上两个数字，operStack的最顶上一个运算符进行运算，然后把运算结果再放到numStack的最顶端
	 * 
	 * @param numStack
	 *            数字栈
	 * @param operStack
	 *            操作栈
	 */
	private void compute(Stack<Double> numStack, Stack<Operator> operStack) {
		Double num2 = numStack.pop(); // 弹出数字栈最顶上的数字作为运算的第二个数字
		Double num1 = numStack.pop(); // 弹出数字栈最顶上的数字作为运算的第一个数字
		Double computeResult = operStack.pop().compute(num1, num2); // 弹出操作栈最顶上的运算符进行计算
		numStack.push(computeResult); // 把计算结果重新放到队列的末端
	}

	/**
	 * 运算符
	 */
	private enum Operator {
		/**
		 * 加
		 */
		PLUS {
			@Override
			public int priority() {
				return 1;
			}

			@Override
			public double compute(double num1, double num2) {
				return num1 + num2;
			}
		},
		/**
		 * 减
		 */
		MINUS {
			@Override
			public int priority() {
				return 1;
			}

			@Override
			public double compute(double num1, double num2) {
				return num1 - num2;
			}
		},
		/**
		 * 乘
		 */
		MULTIPLY {
			@Override
			public int priority() {
				return 2;
			}

			@Override
			public double compute(double num1, double num2) {
				return num1 * num2;
			}
		},
		/**
		 * 除
		 */
		DIVIDE {
			@Override
			public int priority() {
				return 2;
			}

			@Override
			public double compute(double num1, double num2) {
				return num1 / num2;
			}
		},
		/**
		 * 括号
		 */
		BRACKETS {
			@Override
			public int priority() {
				return 0;
			}

			@Override
			public double compute(double num1, double num2) {
				return 0;
			}
		};
		/**
		 * 对应的优先级
		 * 
		 * @return
		 */
		public abstract int priority();

		/**
		 * 计算两个数对应的运算结果
		 * 
		 * @param num1
		 *            第一个运算数
		 * @param num2
		 *            第二个运算数
		 * @return
		 */
		public abstract double compute(double num1, double num2);
	}
}