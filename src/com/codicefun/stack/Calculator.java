package com.codicefun.stack;

public class Calculator {
    public static String expression = "30+2*6-20/2+7";
    public static int result;

    // 创建两个栈，一个数栈，一个符号栈
    public static CalcStack numStack = new CalcStack(10);
    public static CalcStack operatorStack = new CalcStack(10);

    public static void main(String[] args) {
        int index = 0; // 字符串索引，用于扫描
        StringBuilder num = new StringBuilder();

        while (true) {
            // 将每次扫描得到 char 保存到 ch
            char ch = expression.charAt(index);
            if (isOperator(ch)) {
                if (!operatorStack.isEmpty() && (ch == '+' || ch == '-')) {
                    // 如果符号栈不为空，且新符号为 + 或 -，就将当前栈中的结果计算出来
                    calBefore();
                    operatorStack.push(ch);
                } else {
                    operatorStack.push(ch);
                }
            } else {
                // // 数字直接入数栈
                // numStack.push(ch - 48);
                // 处理多位数
                num.append(ch);
                // 如果当前索引是最后一位，直接入数栈，退出循环
                if (index == expression.length() - 1) {
                    numStack.push(new Integer(num.toString()));
                    break;
                }
                // 如果新字符是运算符，直接入数栈
                if (isOperator(expression.charAt(index + 1))) {
                    numStack.push(new Integer(num.toString()));
                    num = new StringBuilder();
                }
            }
            index++;
        }

        cal2Nums();

        System.out.printf("表达式 %s = %d", expression, result);
    }

    /**
     * 判断是不是一个运算符
     *
     * @param val 一个字符
     * @return true or false
     */
    public static boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算
     *
     * @param num1     数值1
     * @param num2     数值2
     * @param operator 运算符
     * @return 计算结果
     */
    public static int cal(int num1, int num2, int operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }

        return res;
    }

    /**
     * 计算当前最后两个数
     */
    public static void cal2Nums() {
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        int operator = operatorStack.pop();
        result = cal(num1, num2, operator);
        System.out.printf("%d %s %d = %d\n", num1, (char) operator, num2, result);
        numStack.push(result);
    }

    /**
     * 计算当前栈中的结果
     */
    public static void calBefore() {
        do {
            cal2Nums();
        } while (!operatorStack.isEmpty());
    }
}

class CalcStack {
    private final int maxSize; // 栈的大小
    private final int[] stack; // 数组模拟栈，数据就放在数组
    private int top = -1; // 栈顶

    public CalcStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈是否满
     *
     * @return true or false
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否空
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value 数据
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        stack[++top] = value;
    }

    /**
     * 出栈
     *
     * @return 顶部数据
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }

        return stack[top--];
    }

    /**
     * 遍历栈（从栈顶开始）
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
        }

        for (int i = top; i > -1; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }
}
