package com.codicefun.dsa.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("a(add):  添加雇员");
            System.out.println("l(list): 显示雇员");
            System.out.println("f(find): 查找雇员");
            System.out.println("e(exit): 退出系统");
            key = scanner.next();
            switch (key) {
                case "a":
                    System.out.print("输入 id: ");
                    int id = scanner.nextInt();
                    System.out.print("输入 name: ");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "l":
                    hashTab.list();
                    break;
                case "f":
                    System.out.print("输入 id: ");
                    id = scanner.nextInt();
                    hashTab.findById(id);
                    break;
                case "e":
                    System.out.println("Bye");
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTab {
    private final EmpLinkedList[] listArray;
    private final int size;

    public HashTab(int size) {
        this.size = size;
        listArray = new EmpLinkedList[size];
        // 分别初始化每一个链表
        for (int i = 0; i < size; i++) {
            listArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        int listNo = hashFun(emp.id);
        listArray[listNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            listArray[i].list(i);
        }
    }

    public int hashFun(int id) {
        return id % size;
    }

    public void findById(int id) {
        int listNo = hashFun(id);
        Emp emp = listArray[listNo].findById(id);

        if (emp == null) {
            System.out.println("null");
        } else {
            System.out.println(emp);
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("=>{id=%d,name=%s}", id, name);
    }
}

class EmpLinkedList {
    private Emp head;

    // 添加雇员到链表
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }

        Emp pos = head;
        while (pos.next != null) {
            pos = pos.next;
        }

        pos.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            System.out.printf("[%d]=>null\n", no);
            return;
        }

        System.out.printf("[%d]", no);
        Emp pos = head;
        while (pos != null) {
            System.out.print(pos);
            pos = pos.next;
        }
        System.out.println();
    }

    public Emp findById(int id) {
        if (head == null) {
            return null;
        }

        Emp pos = head;
        while (pos != null) {
            if (pos.id == id) {
                return pos;
            }
            pos = pos.next;
        }

        return null;
    }
}
