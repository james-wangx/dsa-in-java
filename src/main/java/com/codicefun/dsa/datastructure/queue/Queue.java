package com.codicefun.dsa.datastructure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Queue} class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p>
 * This implementation uses a singly linked list with a static nested class for
 * linked-list nodes. See {@link LinkedQueue} for the version from the
 * textbook that uses a non-static nested class.
 * See {@link ResizingArrayQueue} for a version that uses a resizing array.
 * The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 * operations all take constant time in the worst case.
 *
 * @param <E> the generic type of each item in this queue
 */
public class Queue<E> implements Iterable<E> {

    private Node<E> first; // beginning of queue
    private Node<E> last;  // end of queue
    private int n;         // number of elements on queue

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
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
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     */
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.element;
    }

    /**
     * Adds the element to this queue.
     *
     * @param element the element to add
     */
    public void enqueue(E element) {
        Node<E> oldLast = last;
        last = new Node<>();
        last.element = element;
        last.next = null;

        if (isEmpty()) first = last;
        else oldLast.next = last;

        n++;
    }

    /**
     * Removes and returns the element on this queue that was least recently added
     *
     * @return the element on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        E element = first.element;
        first = first.next;
        n--;

        if (isEmpty()) last = null; // to avoid loitering

        return element;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of elements in FIFO order, separated by spaces
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (E e : this) {
            sb.append(e).append(" ");
        }

        return sb.toString().trim();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
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
            this.current = first;
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
