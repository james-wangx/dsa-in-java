package com.codicefun.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (!key.equals("exit")) {
            System.out.println("\nshow: 显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            System.out.print("请输入你的选择：");
            key = scanner.next();
            int value;
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.print("请输入数据：");
                    value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    value = stack.pop();
                    System.out.println(value);
                    break;
                case "exit":
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayStack {
    private final int maxSize; // 栈的大小
    private final int[] stack; // 数组模拟栈，数据就放在数组
    private int top = -1; // 栈顶

    public ArrayStack(int maxSize) {
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
