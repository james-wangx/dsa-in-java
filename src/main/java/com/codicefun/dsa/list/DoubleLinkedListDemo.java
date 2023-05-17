package com.codicefun.dsa.list;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(6, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(4, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(8, "林冲", "豹子头");

        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(hero1);
        list.addByOrder(hero2);
        list.addByOrder(hero3);
        list.addByOrder(hero4);

        System.out.println("链表内容：");
        list.list();

        list.delete(8);
        System.out.println("\n删除最后一个节点后，链表内容：");
        list.list();

        list.update(new HeroNode2(6, "小卢", "玉麒麟～"));
        System.out.println("\n更新后，链表内容：");
        list.list();
    }
}

class DoubleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private final HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
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
    public void add(HeroNode2 node) {
        // 因为 head 不能动，因此需要一个辅助节点 pos，代表当前位置
        HeroNode2 pos = head;

        // 遍历链表，找到最后节点
        while (pos.next != null) {
            pos = pos.next;
        }

        pos.next = node;
        node.prev = pos;
    }

    /**
     * 按照编号顺序添加节点
     *
     * @param node 新结点
     */
    public void addByOrder(HeroNode2 node) {
        if (head.next == null) {
            head.next = node;
            node.prev = head;
            return;
        }

        HeroNode2 pos = head;
        while (pos.next != null) {
            if (pos.next.no > node.no) {
                node.next = pos.next;
                pos.next.prev = node;
                pos.next = node;
                node.prev = pos;
                return;
            } else if (pos.next.no == node.no) {
                System.out.printf("编号 %d 已经存在: %s\n", node.no, node);
                return;
            }
            pos = pos.next;
        }

        pos.next = node;
        node.prev = pos;
    }

    /**
     * 修改节点的信息，根据 no 编号来修改，即 no 编号不可改
     *
     * @param node 新的节点
     */
    public void update(HeroNode2 node) {
        if (head.next == null) {
            System.out.println("链表为空～");
            return;
        }

        HeroNode2 pos = head.next;
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

        HeroNode2 pos = head.next;
        while (pos != null) {
            if (pos.no == no) {
                pos.prev.next = pos.next;
                if (pos.next != null) { // 判断是否是最后一个节点
                    pos.next.prev = pos.prev;
                }
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

        HeroNode2 pos = head.next;
        while (pos != null) {
            System.out.println(pos);
            pos = pos.next;
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 prev; // 指向前一个节点

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
