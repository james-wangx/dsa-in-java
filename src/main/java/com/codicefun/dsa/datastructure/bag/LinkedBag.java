package com.codicefun.dsa.datastructure.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedBag} class represents a bag (or multiset) of
 * generic items. It supports insertion and iterating over the
 * items in arbitrary order.
 * <p>
 * This implementation uses a singly linked list with a non-static nested class Node.
 * See {@link Bag} for a version that uses a static nested class.
 * The <em>add</em>, <em>isEmpty</em>, and <em>size</em> operations
 * take constant time. Iteration takes time proportional to the number of items.
 */
public class LinkedBag<E> implements Iterable<E> {

    private Node first; // beginning of bag
    private int n;      // number of elements in bag

    /**
     * Initializes an empty bag.
     */
    public LinkedBag() {
        first = null;
        n = 0;
    }

    /**
     * Is this bag empty?
     *
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in this bag.
     *
     * @return the number of elements in this bag
     */
    public int size() {
        return n;
    }

    public void add(E element) {
        Node oldFirst = first;
        first = new Node();
        first.element = element;
        first.next = oldFirst;
        n++;
    }

    /**
     * Returns an iterator that iterates over the elements in this bag in arbitrary order.
     *
     * @return an iterator that iterates over the elements in this bag in arbitrary order
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator(first);
    }

    // helper linked list class
    private class Node {
        private E element;
        private Node next;
    }

    // a linked-list iterator
    private class LinkedIterator implements Iterator<E> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            E element = current.element;
            current = current.next;

            return element;
        }
    }
}
