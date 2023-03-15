package com.codicefun.list;

import java.util.Iterator;

public class SinglyLinkedList<E> implements Iterable<E> {
    private Node<E> first;
    private int size;
    public SinglyLinkedList() {
    }

    public boolean add(E item) {
        if (first == null) {
            first = new Node<>(item);
            first.item = item;
            size++;
            return true;
        }

        Node<E> pos = first;
        while (pos.next != null) {
            pos = pos.next;
        }
        pos.next = new Node<>(item);
        size++;

        return true;
    }

    public boolean remove(E item) {
        if (first == null) {
            return false;
        }

        if (first.item.equals(item)) {
            first = first.next;
            size--;
            return true;
        }

        for (Node<E> pos = first; pos.next != null; pos = pos.next) {
            if (pos.next.item.equals(item)) {
                pos.next = pos.next.next;
                size--;
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
            this.next = null;
        }
    }

    private class ListIterator implements Iterator<E> {
        Node<E> pos = first;

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public E next() {
            Node<E> cur = pos;
            pos = pos.next;
            return cur.item;
        }
    }
}
