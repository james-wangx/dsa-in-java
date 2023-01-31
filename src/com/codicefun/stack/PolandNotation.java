package com.codicefun.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // 为了方便，表达式的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
        // 思路
        // 1、先将 "3 4 + 5 * 6 -" 放入 ArrayList 中
        // 2、将 ArrayList 传递一个方法，遍历 ArrayList 配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println(list);

        int res = calculate(list);
        System.out.println("计算的结果是：" + res);
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
