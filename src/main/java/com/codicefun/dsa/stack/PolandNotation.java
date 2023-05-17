package com.codicefun.dsa.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 波兰表达式的各种计算
 */
public class PolandNotation {
    public static void main(String[] args) {
        String infixExp = "1+((2+3)*4)-5";
        List<String> infixList = infixExp2List(infixExp);
        List<String> suffixList = infix2suffix(infixList);
        int res = calculate(suffixList);

        System.out.println("中缀链表：" + infixList);
        System.out.println("后缀链表：" + suffixList);
        System.out.println("计算的结果是：" + res);
    }

    /**
     * 将中缀表达式转成链表
     *
     * @param infixExp 中缀表达式
     * @return 链表
     */
    public static List<String> infixExp2List(String infixExp) {
        ArrayList<String> list = new ArrayList<>();
        int index = 0; // 一个指针，用于遍历表达式
        StringBuilder num = new StringBuilder(); // 多位数拼接
        char ch; // 每遍历一个字符，就放入 ch

        while (true) {
            // 如果 ch 不是数字，就加入到 list
            if ((ch = infixExp.charAt(index)) < 48 || ch > 57) {
                list.add("" + ch);
            } else { // 如果是一个数，需要考虑多位数问题
                num.append(ch);
                if (index == infixExp.length() - 1) {
                    list.add(num.toString());
                    break;
                }
                if ((ch = infixExp.charAt(index + 1)) < 48 || ch > 57) {
                    list.add(num.toString());
                    num = new StringBuilder();
                }
            }
            index++;
        }

        return list;
    }

    /**
     * 中缀转后缀
     *
     * @param infixList 中缀链表
     * @return 后缀链表
     */
    public static List<String> infix2suffix(List<String> infixList) {
        Stack<String> operatorStack = new Stack<>();
        ArrayList<String> suffixList = new ArrayList<>();

        for (String item : infixList) {
            if (item.matches("\\d+")) {
                // 如果是操作数，直接加入
                suffixList.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号，直接入符号栈
                operatorStack.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号，依次弹出符号栈顶的运算符，并加入 suffixList，直到遇到左括号为止
                while (!operatorStack.peek().equals("(")) {
                    suffixList.add(operatorStack.pop());
                }
                operatorStack.pop();
            } else {
                while (true) {
                    if (operatorStack.isEmpty() || operatorStack.peek().equals("(")) {
                        // 如果符号栈为空，或者栈顶运算符为左括号"("，则直接入符号栈，退出循环
                        operatorStack.push(item);
                        break;
                    } else if (priority(item) > priority(operatorStack.peek())) {
                        // 如果优先级比栈顶运算符优先级高，直接入符号栈，退出循环
                        operatorStack.push(item);
                        break;
                    } else {
                        // 否则，将符号栈顶的运算符弹出并加入 suffixList 中，再下一次循环
                        suffixList.add(operatorStack.pop());
                    }
                }
            }
        }

        // 将符号栈中的运算符依次弹出，并将加入 suffixList
        while (!operatorStack.isEmpty()) {
            suffixList.add(operatorStack.pop());
        }

        return suffixList;
    }

    /**
     * 计算运算符优先级
     *
     * @param operator 运算符
     * @return 优先级
     */
    public static int priority(String operator) {
        if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 将逆波兰表达式字符串转为 ArrayList
     *
     * @param suffixExpression 逆波兰表达式
     * @return List
     */
    public static List<String> getListString(String suffixExpression) {
        // 将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");

        return new ArrayList<>(Arrays.asList(split));
    }


    /**
     * 计算逆波兰表达式
     *
     * @param list 逆波兰表达式
     * @return 计算结果
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();

        for (String item : list) {
            // 使用正则表达式取出数
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
