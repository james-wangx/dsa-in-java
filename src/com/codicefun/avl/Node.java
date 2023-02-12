package com.codicefun.avl;

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
     * 获取左子树的高度
     *
     * @return 高度
     */
    protected int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 获取右子树的高度
     *
     * @return 高度
     */
    protected int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 获取当前节点的高度
     *
     * @return 高度
     */
    protected int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左旋转
     */
    protected void leftRotate() {
        // 1、以当前节点的值创建新的节点
        Node newNode = new Node(value);
        // 2、设置新结点的左子树为当前节点的左子树
        newNode.left = left;
        // 3、设置新结点的右子树为当前节点的右子树的左子树
        newNode.right = right.left;
        // 4、把当前节点的值替换成右子节点的值
        value = right.value;
        // 5、把当前节点的右子树设置为当前节点右子树的右子树
        right = right.right;
        // 6、把当前节点的左子树设置为新结点
        left = newNode;
    }

    /**
     * 右旋转
     */
    protected void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        // 当添加完一个节点后，如果 (右子树的高度 - 左子树的高度) > 1，就进行左旋转
        if ((rightHeight() - leftHeight()) > 1) {
            leftRotate();
        }

        if ((leftHeight() - rightHeight()) > 1) {
            rightRotate();
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
