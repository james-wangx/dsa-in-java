package com.codicefun.dsa.datastructure.list;

import java.util.Stack;

/**
 * 演示栈 Stack 的基本使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        // 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); // pop 就是将栈顶的数据取出
        }
    }
}
