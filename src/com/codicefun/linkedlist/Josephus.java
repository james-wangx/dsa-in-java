package com.codicefun.linkedlist;

public class Josephus {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addNode(5);
        list.list();
    }
}

/**
 * 环形单向链表
 */
class CircleSingleLinkedList {
    private Node first;

    /**
     * 添加 n 个节点
     *
     * @param n 节点个数
     */
    public void addNode(int n) {
        if (n < 2) {
            System.out.println("nums 值不正确");
            return;
        }

        Node curNode = null;
        for (int i = 1; i <= n; i++) {
            Node node = new Node(i);
            if (i == 1) {
                first = node;
                node.setNext(first);
                curNode = first;
            } else {
                curNode.setNext(node);
                node.setNext(first);
                curNode = node;
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

        Node curNode = first;
        while (true) {
            System.out.print(curNode.getNo() + " ");
            if (curNode.getNext() == first) {
                break;
            }
            curNode = curNode.getNext();
        }
    }
}

class Node {
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
