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

	// ���ɼ���Ŀ
	public static String createasy() {
		int num1, num2;
		String[] op = { "+", "-", "��", "��" };
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

	// �����е��Ѷ���Ŀ
	public static String creatMid() {
		int num1, num2, num3;
		String[] op = { "+", "-", "��", "��" };
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

	// ����������Ŀ
	public static String creatHard() {
		int num1, num2, num3, num4;
		String[] op = { "+", "-", "��", "��" };
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

	// �����������
	public static String[] nsort(String num1, String num2, String num3) {
		String[] arr = new String[] { num1, num2, num3 };
		List list = Arrays.asList(arr);
		Collections.shuffle(list);
		String res[] = new String[4];
		for (int i = 0; i < 3; i++)
			res[i] = (String) list.get(i);
		return res;
	}

	// ��������
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

	// ���ɳ���
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
		return num1 + "��" + num2;
	}
	// ���ɳ���
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
			return num1 + "��" + num2;
		}
	// ���ɳ�������
	public static String pdivision(int max) {
		Random random = new Random();
		int num = random.nextInt(10);
		if (num < 4)
			return creatdivision(max);
		else
			return null;
	}

	/**
	 * ����java.util.Vector�������������ַ������ʽ��ֵ������׳��쳣����˵�����ʽ���������û�п���
	 * 
	 * @param computeExpr
	 *            ���������ַ������ʽ
	 * @return ������
	 */
	public double computeWithVector(String computeExpr) {
		StringTokenizer tokenizer = new StringTokenizer(computeExpr, "+-����()",
				true);
		Vector<Double> nums = new Vector<Double>();
		Vector<Operator> operators = new Vector<Operator>();
		Map<String, Operator> computeOper = this.getComputeOper();
		Operator curOper;
		String currentEle;
		while (tokenizer.hasMoreTokens()) {
			currentEle = tokenizer.nextToken().trim();
			if (!"".equals(currentEle)) {// ֻ����ǿ��ַ�
				if (this.isNum(currentEle)) { // ����
					nums.add(Double.valueOf(currentEle));
				} else { // �����֣������Ż��߲�����
					curOper = computeOper.get(currentEle);
					if (curOper != null) { // �������
						// �����б�Ϊ����֮ǰ����������ȼ��ϸ����ȼ���֮ǰ�����ȼ�
						while (!operators.isEmpty()
								&& operators.lastElement().priority() >= curOper
										.priority()) {
							compute(nums, operators);
						}
						// �ѵ�ǰ�����������������е�ĩ��
						operators.add(curOper);
					} else { // ����
						if ("(".equals(currentEle)) { // ������ʱֱ�ӷ�������б���
							operators.add(Operator.BRACKETS);
						} else {// ���������ŵ�ʱ��Ͱ��������������ִ���ˡ�
							// ѭ��ִ���������������ֱ������������Ϊֹ�������������(2+5*2)
							while (!operators.lastElement().equals(
									Operator.BRACKETS)) {
								compute(nums, operators);
							}
							// �Ƴ�������
							operators.remove(operators.size() - 1);
						}
					}
				}
			}
		}
		// �����������ı���������Ӧ����nums����ʣ����������������operators����ʣһ�����������������
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

	/* �����ַ������ */
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

	/* �˷����ַ������ */
	public  String creatcheng() {
		// TODO Auto-generated method stub
		int num1, num2;
		Random random = new Random();
		num1 = random.nextInt(10);
		num2 = random.nextInt(10);
		String a = String.valueOf(num1);
		String b = String.valueOf(num2);
		return a + "��" + b;
	}

	/**
	 * ����java.util.Stack�������������ַ������ʽ��ֵ������׳��쳣����˵�����ʽ���������û�п���
	 * java.util.Stack��ʵҲ�Ǽ̳���java.util.Vector�ġ�
	 * 
	 * @param computeExpr
	 *            ���������ַ������ʽ
	 * @return ������
	 */
	public double computeWithStack(String computeExpr) {
		// �ѱ��ʽ������������ŷָ��һ��һ�εģ����ҷָ��Ľ�������ָ���
		StringTokenizer tokenizer = new StringTokenizer(computeExpr, "+-��/()",
				true);
		Stack<Double> numStack = new Stack<Double>(); // ����������ֵ�ջ
		Stack<Operator> operStack = new Stack<Operator>(); // ��Ų�������ջ
		Map<String, Operator> computeOper = this.getComputeOper(); // ��ȡ���������
		String currentEle; // ��ǰԪ��
		while (tokenizer.hasMoreTokens()) {
			currentEle = tokenizer.nextToken().trim(); // ȥ��ǰ��Ŀո�
			if (!"".equals(currentEle)) { // ֻ����ǿ��ַ�
				if (this.isNum(currentEle)) { // Ϊ����ʱ����뵽����ջ��
					numStack.push(Double.valueOf(currentEle));
				} else { // ������
					Operator currentOper = computeOper.get(currentEle);// ��ȡ��ǰ���������
					if (currentOper != null) { // ��Ϊ��ʱ��Ϊ���������
						while (!operStack.empty()
								&& operStack.peek().priority() >= currentOper
										.priority()) {
							compute(numStack, operStack);
						}
						// �������ѵ�ǰ���������뵽����ջ��
						operStack.push(currentOper);
					} else {// ����
						if ("(".equals(currentEle)) { // ������ʱ�������Ų�������ջ��
							operStack.push(Operator.BRACKETS);
						} else { // ������ʱ, �������Ÿ�������֮��ʣ����������ִ���ˡ�
							while (!operStack.peek().equals(Operator.BRACKETS)) {
								compute(numStack, operStack);
							}
							operStack.pop();// �Ƴ�ջ����������
						}
					}
				}
			}
		}
		// �����������ı���������Ӧ����nums����ʣ����������������operators����ʣһ�����������������
		while (!operStack.empty()) {
			compute(numStack, operStack);
		}
		return numStack.pop();
	}

	/**
	 * �ж�һ���ַ����Ƿ�����������
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNum(String str) {
		String numRegex = "^\\d+(\\.\\d+)?$"; // ���ֵ�������ʽ
		return Pattern.matches(numRegex, str);
	}

	/**
	 * ��ȡ���������
	 * 
	 * @return
	 */
	private Map<String, Operator> getComputeOper() {
		return new HashMap<String, Operator>() { // �����
			private static final long serialVersionUID = 7706718608122369958L;
			{
				put("+", Operator.PLUS);
				put("-", Operator.MINUS);
				put("��", Operator.MULTIPLY);
				put("��", Operator.DIVIDE);
			}
		};
	}

	/**
	 * ȡnums������������֣�operators�����һ��������������㣬Ȼ����������ٷŵ�nums�б��ĩ��
	 * 
	 * @param nums
	 * @param operators
	 */
	private void compute(Vector<Double> nums, Vector<Operator> operators) {
		Double num2 = nums.remove(nums.size() - 1); // �ڶ������֣���ǰ���е����һ������
		Double num1 = nums.remove(nums.size() - 1); // ��һ�����֣���ǰ���е����һ������
		Double computeResult = operators.remove(operators.size() - 1).compute(
				num1, num2); // ȡ���һ����������м���
		nums.add(computeResult); // �Ѽ��������·ŵ����е�ĩ��
	}

	/**
	 * ȡnumStack������������֣�operStack�����һ��������������㣬Ȼ����������ٷŵ�numStack�����
	 * 
	 * @param numStack
	 *            ����ջ
	 * @param operStack
	 *            ����ջ
	 */
	private void compute(Stack<Double> numStack, Stack<Operator> operStack) {
		Double num2 = numStack.pop(); // ��������ջ��ϵ�������Ϊ����ĵڶ�������
		Double num1 = numStack.pop(); // ��������ջ��ϵ�������Ϊ����ĵ�һ������
		Double computeResult = operStack.pop().compute(num1, num2); // ��������ջ��ϵ���������м���
		numStack.push(computeResult); // �Ѽ��������·ŵ����е�ĩ��
	}

	/**
	 * �����
	 */
	private enum Operator {
		/**
		 * ��
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
		 * ��
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
		 * ��
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
		 * ��
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
		 * ����
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
		 * ��Ӧ�����ȼ�
		 * 
		 * @return
		 */
		public abstract int priority();

		/**
		 * ������������Ӧ��������
		 * 
		 * @param num1
		 *            ��һ��������
		 * @param num2
		 *            �ڶ���������
		 * @return
		 */
		public abstract double compute(double num1, double num2);
	}
}