package com.codicefun.dsa.stack;

/**
 * 中缀表达式计算器
 */
public class InfixExpressionCalculator {
    private int result;
    private final ListStack<Integer> numStack = new ListStack<>();
    private final ListStack<Character> opeStack = new ListStack<>();

    /**
     * 按给定的中缀表达式进行计算
     * 如果当前运算符的优先级较小，比如 '+' 或 '-'，直接计算当前表达式的结果，而不是只计算两个数
     * 这样做可以解决 '-' 和 '/' 的运算顺序的问题，因为它们的运算顺序是从左到右
     * 如果堆到最后再出栈计算，就变成了从右到左计算。
     *
     * @param exp 中缀表达式
     * @return 计算结果
     */
    public int calc(String exp) {
        StringBuilder num = new StringBuilder();
        int i = 0;

        while (true) {
            char ch = exp.charAt(i);
            if (isOpe(ch)) {
                if (!opeStack.isEmpty() && opeStack.peek() != '(' && (ch == '+' || ch == '-')) {
                    calcBefore();
                    opeStack.push(ch);
                } else if (ch == ')') {
                    calcBrackets();
                } else {
                    opeStack.push(ch);
                }
            } else {
                num.append(ch);
                if (i == exp.length() - 1) {
                    numStack.push(Integer.valueOf(num.toString()));
                    break;
                }
                if (isOpe(exp.charAt(i + 1))) {
                    numStack.push(Integer.valueOf(num.toString()));
                    num = new StringBuilder();
                }
            }
            i++;
        }

        calc2Nums();

        return result;
    }

    /**
     * 判断该字符是否为操作符
     *
     * @param val 字符
     * @return true or false
     */
    private boolean isOpe(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/' || val == '(' || val == ')';
    }

    /**
     * 按给定的两个数和一个运算符进行计算
     *
     * @param num1 数值1
     * @param num2 数值2
     * @param ope  运算符
     * @return 计算结果
     */
    private int calc(int num1, int num2, int ope) {
        int res = 0;
        switch (ope) {
            case '+' -> res = num1 + num2;
            case '-' -> res = num2 - num1; // 注意顺序
            case '*' -> res = num1 * num2;
            case '/' -> res = num2 / num1;
            default -> {
            }
        }

        return res;
    }

    /**
     * 计算当前最后两个数
     */
    private void calc2Nums() {
        int num1 = numStack.pop();
        int num2 = numStack.pop();
        int operator = opeStack.pop();
        result = calc(num1, num2, operator);
        numStack.push(result);
    }

    /**
     * 计算当前表达式中的结果
     */
    private void calcBefore() {
        do {
            calc2Nums();
        } while (!opeStack.isEmpty());
    }

    /**
     * 计算小括号'()'中表达式的结果
     */
    private void calcBrackets() {
        do {
            calc2Nums();
        } while (opeStack.peek() != '(');

        opeStack.pop();
    }
}
