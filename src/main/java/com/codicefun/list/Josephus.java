package com.codicefun.list;

public class Josephus {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.list();

        System.out.println();
        list.countBoy(3, 7);
    }
}

/**
 * 环形单向链表
 */
class CircleSingleLinkedList {
    private Boy first;

    /**
     * 添加 n 个小孩
     *
     * @param n 小孩个数
     */
    public void addBoy(int n) {
        if (n < 2) {
            System.out.println("nums 值不正确");
            return;
        }

        Boy curBoy = null;
        for (int i = 1; i <= n; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                boy.next = first;
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.print(curBoy.no + " ");
            if (curBoy.next == first) {
                break;
            }
            curBoy = curBoy.next;
        }
    }

    /**
     * 小孩报数
     *
     * @param start 开始的小孩
     * @param n     报数的次数
     */
    public void countBoy(int start, int n) {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }

        Boy curBoy = first;
        // 报数的次数
        int t = 0;
        while (curBoy.next != curBoy) {
            if (curBoy.no == start) {
                t = 1; // 开始第轮次报数
                // 找到起始位置后修改 start，防止下次循环再次到达该位置重置 t
                start = -1;
            } else {
                t++; // 接着报数
            }
            if (t == n - 1) { // 找到将要出列的前一个孩子
                System.out.print(curBoy.next.no + " ");
                curBoy.next = curBoy.next.next;
                start = curBoy.next.no; // 开始下轮次报数
            }
            curBoy = curBoy.next;
        }

        System.out.print(curBoy.no + " ");
    }
}

class Boy {
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }
}
