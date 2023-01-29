package com.codicefun.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "70+2*6-4";

        // 创建两个栈，一个数栈，一个符号栈
        CalcStack numStack = new CalcStack(10);
        CalcStack operatorStack = new CalcStack(10);

        // 定义需要的相关变量
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到 char 保存到 ch
        StringBuilder keepNum = new StringBuilder(); // 用于拼接多位数

        // 开始 while 循环扫描 expression
        do {
            // 依次得到 expression 中每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断 ch 是什么，然后做相应的处理
            if (operatorStack.isOperator(ch)) {
                if (!operatorStack.isEmpty()) {
                    // 如果符号栈有运算符符，就进行比较
                    // 如果当前运算符的优先级 <= 栈顶运算符的优先级，就从数栈 pop 两个数，从符号栈 pop 一个运算符进行计算
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = numStack.cal(num1, num2, operator);
                        // 将计算结果 push 数栈，当前运算符 push 符号栈
                        numStack.push(res);
                        operatorStack.push(ch);
                    } else {
                        // 如果当前运算符的优先级 > 栈顶运算符的优先级，直接入符号栈
                        operatorStack.push(ch);
                    }
                } else {
                    // 如果为空，直接入符号栈
                    operatorStack.push(ch);
                }
            } else {
                // 如果是数，直接入数栈
                // numStack.push(ch - 48); // '1' -> 1
                // 分析思路
                // 1、当处理多位数时，不能发现一个数就立即入栈
                // 2、在处理数时，需要向 expression 的后再看一位，如果是数，就继续扫描，如果是符号才入栈
                // 3、因此我们需要定一个字符串变量，用于拼接

                // 处理多位数
                keepNum.append(ch);

                // 判断下一个字符是不是数字，如果是数字就继续扫描，如果是运算符，则入栈
                // 注意看后一位，不是 index++
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                } else if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                    // 如果后一位是运算符，则入栈
                    numStack.push(Integer.parseInt(keepNum.toString()));
                    // 重要的！！！！！！，keepNum 要清空
                    keepNum = new StringBuilder();
                }
            }
            // 让 index + 1，并判断是否扫描到 expression 最后
            index++;
        } while (index != expression.length());

        // 当表达式扫描完毕，就顺序的从数栈和符号栈 pop 出相应的数和符号，并运行
        while (true) {
            // 如果符号栈为空，则计算到最后结果，数栈中只有一个数字
            if (operatorStack.isEmpty()) {
                break;
            } else {
                num1 = numStack.pop();
                num2 = numStack.pop();
                operator = operatorStack.pop();
                res = numStack.cal(num1, num2, operator);
                numStack.push(res);
            }
        }

        System.out.printf("表达式 %s = %d", expression, res);
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

    /**
     * 查看栈顶的值
     *
     * @return 栈顶的值
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 返回运算符的优先级，优先级程序员来确定，优先级使用数字表示
     *
     * @param operator 运算符
     * @return 优先级
     */
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1; // 假定目前的运算符只有 + - * /
        }
    }

    /**
     * 判断是不是一个运算符
     *
     * @param val 一个字符
     * @return true or false
     */
    public boolean isOperator(char val) {
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
    public int cal(int num1, int num2, int operator) {
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
}
