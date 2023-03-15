package com.codicefun.stack;

import java.util.Iterator;

public class ListStack<E> implements Iterable<E> {

    private Node<E> top;

    public ListStack() {
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(E ele) {
        if (top == null) {
            top = new Node<>(ele);
            return;
        }

        Node<E> node = new Node<>(ele);
        node.next = top;
        top = node;
    }

    public E pop() {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty!");
        }

        Node<E> node = top;
        top = top.next;
        node.next = null;

        return node.ele;
    }

    public E peek() {
        if (isEmpty()) {
            throw new StackEmptyException("Stack is empty!");
        }

        return top.ele;
    }

    private static class Node<E> {
        E ele;
        Node<E> next;

        Node(E ele) {
            this.ele = ele;
            this.next = null;
        }
    }

    private class StackIterator implements Iterator<E> {
        Node<E> pos = top;

        @Override
        public boolean hasNext() {
            return pos == null;
        }

        @Override
        public E next() {
            E ele = pos.ele;
            pos = pos.next;

            return ele;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
}
