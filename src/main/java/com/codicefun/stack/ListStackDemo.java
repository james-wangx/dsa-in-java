package com.codicefun.stack;

import java.util.Scanner;

public class ListStackDemo {
    public static void main(String[] args) {
        ListStack stack = new ListStack(4);
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
                    stack.push(new Node(value));
                    break;
                case "pop":
                    Node node = stack.pop();
                    System.out.println(node);
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

/**
 * 循环双向链表实现栈
 */
class ListStack {
    public int maxSize;
    public Node first;
    public int size = 0;

    public ListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(Node node) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        if (isEmpty()) {
            first = node;
            first.next = first;
            first.prev = first;
            size++;
            return;
        }

        Node last = first.prev;
        last.next = node;
        node.prev = last;
        node.next = first;
        first.prev = node;
        size++;
    }

    public Node pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }

        Node last = first.prev;
        last.prev.next = first;
        first.prev = last.prev;
        last.prev = null;
        last.next = null;
        size--;

        return last;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }

        Node pos = first;
        int n = 0;
        while (n != size) {
            System.out.println(pos);
            pos = pos.next;
            n++;
        }
    }
}

class Node {
    public Node next;
    public Node prev;
    public int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
