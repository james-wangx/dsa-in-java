package com.codicefun.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(arr);
        root.preOrder();
    }

    // 创建哈夫曼树
    public static Node createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1、遍历 arr 数组
        // 2、将 arr 的每一个元素构成 Node
        // 3、将 Node 放入 ArrayList 中
        List<Node> nodes = new ArrayList<>();

        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            // 升序排序
            Collections.sort(nodes);
            // 取出根节点权值最小的二叉树
            // 1、取出权值最小的节点
            Node leftNode = nodes.get(0);
            // 2、取出权值第二小的节点
            Node rightNode = nodes.get(1);
            // 3、构建一颗新的二叉树
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 4、从 ArrayList 中删除处理过的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5、将 parentNode 加入到 nodes
            nodes.add(parentNode);
        }

        // 返回哈夫曼树的 root 节点
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    int value; // 权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);

        if (left != null) {
            left.preOrder();
        }

        if (right != null) {
            right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 升序排序
        return this.value - o.value;
    }
}
