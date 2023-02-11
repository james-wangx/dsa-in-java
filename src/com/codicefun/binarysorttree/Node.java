package com.codicefun.binarysorttree;

/**
 * 节点
 */
public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加节点
     *
     * @param node 新结点
     */
    protected void add(Node node) {
        if (node.value < value) {
            if (left == null) {
                left = node;
            } else {
                left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
            } else {
                right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    protected void infixOrder(StringBuilder sb) {
        if (left != null) {
            left.infixOrder(sb);
        }
        sb.append(this.value).append(" ");
        if (right != null) {
            right.infixOrder(sb);
        }
    }

    @Override
    public String toString() {
        return String.format("{value=%d}", value);
    }
}
