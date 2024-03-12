package com.codicefun.dsa.datastructure.binarysorttree;

/**
 * 二叉排序树
 */
public class BinarySortTree {
    private Node root;

    /**
     * 根据数组创建树
     *
     * @param arr 数组
     */
    public void createTree(int[] arr) {
        root = new Node(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            root.add(new Node(arr[i]));
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 删除节点
     *
     * @param value 待删除节点的值
     */
    public void del(int value) {
        // 空树
        if (root == null) {
            return;
        }

        // 未找到节点
        Node targetNode = root.search(value);
        if (targetNode == null) {
            return;
        }

        // 是根节点且仅此一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        Node parentNode = root.searchParent(value);
        // 叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 是父节点的左节点
            if (parentNode.left != null && parentNode.left == targetNode) {
                parentNode.left = null;
                return;
            }
            // 是父节点的右节点
            if (parentNode.right != null && parentNode.right == targetNode) {
                parentNode.right = null;
            }
            // 有两颗子树的节点
        } else if (targetNode.left != null && targetNode.right != null) {
            Node temp = targetNode.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            del(temp.value);
            targetNode.value = temp.value;
            // 只有一颗子树的节点
        } else {
            // 是根节点
            if (parentNode == null) {
                // 只有左节点
                if (targetNode.left != null) {
                    root = targetNode.left;
                    // 只有右节点
                } else {
                    root = targetNode.right;
                }
                return;
            }
            // 只有左节点
            if (targetNode.left != null) {
                // 是父节点的左节点
                if (parentNode.left == targetNode) {
                    parentNode.left = targetNode.left;
                    // 是父节点的右节点
                } else {
                    parentNode.right = targetNode.left;
                }
                // 只有右节点
            } else {
                // 是父节点的左节点
                if (parentNode.left == targetNode) {
                    parentNode.left = targetNode.right;
                    // 是父节点的右节点
                } else {
                    parentNode.right = targetNode.right;
                }
            }
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
