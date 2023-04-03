package com.codicefun.tree.binarytree;

import java.util.Objects;

public class Node<E> {
    private E data;
    private Node<E> left;
    private Node<E> right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public void deleteNode(E data) {
        if (left != null && left.data.equals(data)) {
            left = null;
            return;
        }

        if (right != null && right.data.equals(data)) {
            right = null;
            return;
        }

        if (left != null) {
            left.deleteNode(data);
        }

        if (right != null) {
            right.deleteNode(data);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (!data.equals(node.data)) return false;
        if (!Objects.equals(left, node.left)) return false;
        return Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
