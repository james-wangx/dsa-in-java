package com.codicefun.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(6, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(4, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(8, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();
        // list.add(hero1);
        // list.add(hero4);
        // list.add(hero2);
        // list.add(hero3);

        list.addByOrder(hero1);
        list.addByOrder(hero4);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero3);

        System.out.println("链表1内容：");
        list.list();

        HeroNode hero5 = new HeroNode(3, "3", "3");
        HeroNode hero6 = new HeroNode(7, "7", "7");
        HeroNode hero7 = new HeroNode(2, "2", "2");
        HeroNode hero8 = new HeroNode(5, "5", "5");

        SingleLinkedList list2 = new SingleLinkedList();
        list2.addByOrder(hero5);
        list2.addByOrder(hero6);
        list2.addByOrder(hero7);
        list2.addByOrder(hero8);

        System.out.println("\n链表2内容：");
        list2.list();

        SingleLinkedList newList = mergeList(list.getHead(), list2.getHead());
        System.out.println("\n合并后链表内容：");
        newList.list();

        // System.out.println("链表内容倒序打印：");
        // reversePrint(list.getHead());

        // System.out.println("倒序后：");
        // // list.reverse();
        // // list.list();
        // SingleLinkedList reversedList = list.reverse();
        // reversedList.list();
        // System.out.println("原先的链表：");
        // list.list();
        //
        // // 修改节点数据
        // list.update(new HeroNode(2, "小卢", "玉麒麟～"));
        // System.out.println("\n修改后的链表");
        // list.list();
        //
        // // 第二次修改
        // // list.update(new HeroNode(5, "哈", "ha"));
        //
        // // list.delete(1);
        // // list.delete(2);
        // // list.delete(3);
        // list.delete(4);
        // System.out.println("\n删除节点后的链表");
        // list.list();
        //
        //
        // System.out.println("有效结点个数：" + getLength(list.getHead()));
        //
        // System.out.println("倒数第 2 个节点是：" + getKndLast(list.getHead(), 2));
    }

    /**
     * 获取到单链表的节点个数（如果是带头结点的链表，不统计头结点）
     *
     * @param head 链表的头结点
     * @return 有效结点个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }

        int length = 0;
        HeroNode pos = head.next;
        while (pos != null) {
            length++;
            pos = pos.next;
        }

        return length;
    }

    /**
     * 查找单链表中的倒数第 k 个节点
     *
     * @param head 链表头节点
     * @param k    倒数索引
     * @return 倒数第 k 个节点
     */
    public static HeroNode getKndLast(HeroNode head, int k) {
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }

        int length = getLength(head);
        if (k <= 0 || k > length) {
            throw new RuntimeException("链表有效节点个数为：" + length);
        }

        HeroNode pos = head.next;
        while (pos != null) {
            if (length-- == k) {
                return pos;
            }
            pos = pos.next;
        }

        return null;
    }

    /**
     * 方式一：递归
     *
     * @param node 节点
     */
    public static void reversePrint(HeroNode node) {
        if (node.next != null) {
            reversePrint(node.next);
        }

        if (node.no == 0) {
            return;
        }

        System.out.println(node);
    }

    /**
     * 方式二：利用栈先进后出的特点
     *
     * @param head 头结点
     */
    public static void reversePrint2(HeroNode head) {
        if (head.next == null) {
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode pos = head.next;
        while (pos != null) {
            stack.push(pos); // 入栈
            pos = pos.next;
        }

        // 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 按编号顺序合并两个链表
     *
     * @param head1 第一个链表头
     * @param head2 第二个链表头
     * @return 合并后的链表
     */
    public static SingleLinkedList mergeList(HeroNode head1, HeroNode head2) {
        SingleLinkedList newList = new SingleLinkedList();

        HeroNode pos1 = head1.next;
        while (pos1 != null) {
            HeroNode newPos = new HeroNode(pos1.no, pos1.name, pos1.nickname);
            newList.addByOrder(newPos);
            pos1 = pos1.next;
        }

        HeroNode pos2 = head2.next;
        while (pos2 != null) {
            HeroNode newPos = new HeroNode(pos2.no, pos2.name, pos2.nickname);
            newList.addByOrder(newPos);
            pos2 = pos2.next;
        }

        return newList;
    }
}

/**
 * 定义 SingleLinkedList，管理我们的英雄
 */
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private final HeroNode head = new HeroNode(0, "", "");

    /**
     * 返回头节点
     *
     * @return 头节点
     */
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加一个节点
     * 思路：当不考虑编号的顺序时
     * 1、找到当前链表的最后节点
     * 2、将最后节点的 next 指向新的节点
     *
     * @param node 节点
     */
    public void add(HeroNode node) {
        // 因为 head 不能动，因此需要一个辅助节点 pos，代表当前位置
        HeroNode pos = head;

        // 遍历链表，找到最后节点
        while (pos.next != null) {
            pos = pos.next;
        }

        pos.next = node;
    }

    /**
     * 按照编号顺序添加节点
     *
     * @param node 新结点
     */
    public void addByOrder(HeroNode node) {
        HeroNode pos = head;

        // 因为是单链表，所以要找到添加位置的前一个节点
        while (pos.next != null) {
            if (pos.next.no > node.no) {
                node.next = pos.next;
                pos.next = node;
                return;
            } else if (pos.next.no == node.no) {
                System.out.printf("编号 %d 已经存在: %s\n", node.no, node);
                return;
            }
            pos = pos.next;
        }

        pos.next = node;
    }

    /**
     * 修改节点的信息，根据 no 编号来修改，即 no 编号不可改
     *
     * @param node 新的节点
     */
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空～");
            return;
        }

        HeroNode pos = head.next;
        while (pos != null) {
            if (pos.no == node.no) {
                pos.name = node.name;
                pos.nickname = node.nickname;
                return;
            }
            pos = pos.next;
        }

        System.out.printf("未找到编号 %d 的节点，不能修改", node.no);
    }

    /**
     * 根据编号删除节点
     *
     * @param no 编号
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空～");
            return;
        }

        HeroNode pos = head;
        while (pos.next != null) {
            if (pos.next.no == no) {
                pos.next = pos.next.next;
                return;
            }
            pos = pos.next;
        }

        System.out.printf("要删除的 %d 节点不存在\n", no);
    }

    /**
     * 显示链表
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode pos = head.next;
        while (pos != null) {
            System.out.println(pos);
            pos = pos.next;
        }
    }

    /**
     * 链表反转自身
     */
    public void reverseSelf() {
        if (head.next == null) {
            return;
        }
        if (head.next.next == null) {
            return;
        }

        HeroNode prev;
        HeroNode pos = head.next;
        HeroNode next = pos.next;
        pos.next = null;
        while (next.next != null) {
            prev = pos;
            pos = next;
            next = next.next;
            pos.next = prev;
        }

        next.next = pos;
        head.next = next;
    }

    /**
     * 链表反转
     *
     * @return 一个新的反序的链表
     */
    public SingleLinkedList reverse() {
        SingleLinkedList newList = new SingleLinkedList();
        HeroNode newHead = newList.getHead();

        HeroNode pos = head.next;
        while (pos != null) {
            HeroNode newNode = new HeroNode(pos.no, pos.name, pos.nickname);
            newNode.next = newHead.next;
            newHead.next = newNode;
            pos = pos.next;
        }

        return newList;
    }
}

/**
 * 定义 HeroNode，每个 HeroNode 对象就是一个结点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
