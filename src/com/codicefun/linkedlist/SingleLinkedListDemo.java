package com.codicefun.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

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

        System.out.println("链表内容：");
        list.list();

        // 修改节点数据
        list.update(new HeroNode(2, "小卢", "玉麒麟～"));
        System.out.println("\n修改后的链表");
        list.list();

        // 第二次修改
        // list.update(new HeroNode(5, "哈", "ha"));

        // list.delete(1);
        // list.delete(2);
        // list.delete(3);
        list.delete(4);
        System.out.println("\n删除节点后的链表");
        list.list();
    }
}

/**
 * 定义 SingleLinkedList，管理我们的英雄
 */
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private final HeroNode head = new HeroNode(0, "", "");

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

    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空～");
            return;
        }

        HeroNode pos = head;
        while (pos.next!= null) {
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
