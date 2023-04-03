package com.codicefun.tree.binarytree;

/**
 * 二叉树
 *
 * @param <E> 数据类型
 */
public class BinaryTree<E> {
    private Node<E> root;

    public BinaryTree() {
        root = null;
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    /**
     * 先续遍历方法的重载（从 root 开始），并打印换行符
     */
    public void printPreorder() {
        if (root != null) {
            printPreorder(root);
            System.out.println();
        }
    }

    /**
     * 中续遍历方法的重载（从 root 开始），并打印换行符
     */
    public void printInorder() {
        if (root != null) {
            printInorder(root);
            System.out.println();
        }
    }

    /**
     * 后续遍历方法的重载（从 root 开始），并打印换行符
     */
    public void printPostorder() {
        if (root != null) {
            printPostorder(root);
            System.out.println();
        }
    }

    /**
     * 先续查找方法的重载（从 root 节点开始）
     *
     * @param data 查找的数据
     * @return 找到的节点
     */
    public Node<E> searchPreorder(E data) {
        if (root != null) {
            return searchPreorder(root, data);
        }

        return null;
    }

    /**
     * 中续查找方法的重载（从 root 节点开始）
     *
     * @param data 查找的数据
     * @return 找到的节点
     */
    public Node<E> searchInorder(E data) {
        if (root != null ) {
            return searchInorder(root, data);
        }

        return null;
    }

    /**
     * 后续查找方法的重载（从 root 节点开始）
     *
     * @param data 查找的数据
     * @return 找到的节点
     */
    public Node<E> searchPostorder(E data) {
        if (root != null) {
            return searchPostorder(root, data);
        }

        return null;
    }

    /**
     * 先续遍历
     *
     * @param node 开始的节点
     */
    public void printPreorder(Node<E> node) {
        System.out.print(node.getData() + " ");

        if (node.getLeft() != null) {
            printPreorder(node.getLeft());
        }

        if (node.getRight() != null) {
            printPreorder(node.getRight());
        }
    }

    /**
     * 中续遍历
     *
     * @param node 开始的节点
     */
    public void printInorder(Node<E> node) {
        if (node.getLeft() != null) {
            printInorder(node.getLeft());
        }

        System.out.print(node.getData() + " ");

        if (node.getRight() != null) {
            printInorder(node.getRight());
        }
    }

    /**
     * 后续遍历
     *
     * @param node 开始的节点
     */
    public void printPostorder(Node<E> node) {
        if (node.getLeft() != null) {
            printPostorder(node.getLeft());
        }

        if (node.getRight() != null) {
            printPostorder(node.getRight());
        }

        System.out.print(node.getData() + " ");
    }

    /**
     * 先序查找
     *
     * @param node 开始的节点
     * @param data 查找的数据
     * @return 找到的节点
     */
    public Node<E> searchPreorder(Node<E> node, E data) {
        if (data.equals(node.getData())) {
            return node;
        }

        Node<E> find = null;
        if (node.getLeft() != null) {
            find = searchPreorder(node.getLeft(), data);
        }
        if (find != null) {
            return find;
        }

        if (node.getRight() != null) {
            find = searchPreorder(node.getRight(), data);
        }

        return find;
    }

    /**
     * 中序查找
     *
     * @param node 开始的节点
     * @param data 查找的数据
     * @return 找到的节点
     */
    public Node<E> searchInorder(Node<E> node, E data) {
        Node<E> find = null;
        if (node.getLeft() != null) {
            find = searchInorder(node.getLeft(), data);
        }
        if (find != null) {
            return find;
        }

        if (data.equals(node.getData())) {
            return node;
        }

        if (node.getRight() != null) {
            find = searchInorder(node.getRight(), data);
        }

        return find;
    }

    /**
     * 后序查找
     *
     * @param node 开始的节点
     * @param data 查找的数据
     * @return 找到的节点
     */
    public Node<E> searchPostorder(Node<E> node, E data) {
        Node<E> find = null;
        if (node.getLeft() != null) {
            find = searchPostorder(node.getLeft(), data);
        }
        if (find != null) {
            return find;
        }

        if (node.getRight() != null) {
            find = searchPostorder(node.getRight(), data);
        }
        if (find != null) {
            return find;
        }

        if (data.equals(node.getData())) {
            return node;
        }

        return null;
    }

    public void deleteNode(E data) {
        if (data.equals(root.getData())) {
            root = null;
        } else {
            root.deleteNode(data);
        }
    }
}
