package com.codicefun.dsa.datastructure.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Bag} class represents a bag (or multiset) of
 * generic items. It supports insertion and iterating over the
 * items in arbitrary order.
 * <p>
 * This implementation uses a singly linked list with a static nested class Node.
 * See {@link LinkedBag} for the version from the
 * textbook that uses a non-static nested class.
 * See {@link ResizingArrayBag} for a version that uses a resizing array.
 * The <em>add</em>, <em>isEmpty</em>, and <em>size</em> operations
 * take constant time. Iteration takes time proportional to the number of items.
 *
 * @param <E> the generic type of each item in this bag
 */
public class Bag<E> implements Iterable<E> {

    private Node<E> first;  // beginning of bag
    private int n;          // number of elements

    /**
     * Initializes an empty bag
     */
    public Bag() {
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

    /**
     * Adds the element to this bag.
     *
     * @param element the element to add
     */
    public void add(E element) {
        Node<E> oldFirst = first;
        first = new Node<>();
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
    private static class Node<E> {
        private E element;
        private Node<E> next;
    }

    // a linked-list iterator
    private class LinkedIterator implements Iterator<E> {
        private Node<E> current;

        public LinkedIterator(Node<E> first) {
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
