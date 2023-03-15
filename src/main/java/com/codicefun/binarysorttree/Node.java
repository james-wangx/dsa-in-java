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

    /**
     * 查找目标节点
     *
     * @param value 待查找节点的值
     * @return 目标节点或 null
     */
    protected Node search(int value) {
        if (this.value == value) { // 当前节点
            return this;
        } else if (value < this.value) {// 在左子树
            if (left == null) {// 左节点不存在
                return null;
            }
            return left.search(value);// 向左递归
        } else {// 在右子树
            if (right == null) {// 右节点不存在
                return null;
            }
            return right.search(value); // 向右递归
        }
    }

    /**
     * 查找目标节点的父节点
     *
     * @param value 目标节点的值
     * @return 目标节点的父节点或 null
     */
    protected Node searchParent(int value) {
        if ((left != null && left.value == value)
                || (right != null && right.value == value)) { // 当前节点
            return this;
        } else {
            if (value < this.value && left != null) { // 在左子树
                return left.searchParent(value); // 向左递归
            } else if (value > this.value && right != null) { // 在右子树
                return right.searchParent(value); // 向右递归
            } else { // 没有父节点
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("{value=%d}", value);
    }
}
