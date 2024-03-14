package com.codicefun.dsa.datastructure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedQueue} class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p>
 * This implementation uses a singly linked list with a non-static nested class
 * for linked-list nodes.  See {@link Queue} for a version that uses a static nested class.
 * The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 * operations all take constant time in the worst case.
 */
public class LinkedQueue<E> implements Iterable<E> {

    private int n;      // number of elements on queue
    private Node first; // beginning of queue
    private Node last;  // end of queue

    /**
     * Initializes an empty queue.
     */
    public LinkedQueue() {
        first = null;
        last = null;
        n = 0;
        assert check();
    }

    /**
     * Is this queue empty?
     *
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the element least recently added to this queue.
     *
     * @return the element least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }

        return first.element;
    }

    /**
     * Adds the element to this queue.
     *
     * @param element the element to add
     */
    public void enqueue(E element) {
        Node oldLast = last;
        last = new Node();
        last.element = element;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        n++;
        assert check();
    }

    /**
     * Returns and removes the element on this queue that was least recently added.
     *
     * @return the element on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        E element = first.element;
        first = first.next;
        n--;

        if (isEmpty()) {
            last = null; // to avoid loitering
        }

        assert check();

        return element;
    }

    // check internal invariants
    private boolean check() {
        if (n < 0) {
            return false;
        } else if (n == 0) {
            if (first != null) return false;
            if (last != null) return false;
        } else if (n == 1) {
            if (first == null || last == null) return false;
            if (first != last) return false;
            if (first.next != null) return false;
        } else {
            if (first == null || last == null) return false;
            if (first == last) return false;
            if (first.next == null) return false;
            if (last.next != null) return false;

            // check internal consistency of instance variable n
            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != n) return false;

            // check internal consistency of instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (lastNode != last) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (E e : this) {
            sb.append(e).append(" ");
        }

        return sb.toString().trim();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    // helper linked list class
    private class Node {
        private E element;
        private Node next;
    }

    private class LinkedIterator implements Iterator<E> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E element = current.element;
            current = current.next;

            return element;
        }
    }

}
