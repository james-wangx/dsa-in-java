package com.codicefun.binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTree {
    private Node root;

    /**
     * 根据数组创建树
     *
     * @param arr 数组
     * @return 根节点
     */
    public Node createTree(int[] arr) {
        root = new Node(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            root.add(new Node(arr[i]));
        }

        return root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public String infixOrder() {
        StringBuilder sb = new StringBuilder();

        if (root != null) {
            root.infixOrder(sb);
        }

        return sb.toString();
    }
}
