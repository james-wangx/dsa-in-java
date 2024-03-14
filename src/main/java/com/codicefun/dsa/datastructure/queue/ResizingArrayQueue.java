package com.codicefun.dsa.datastructure.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayQueue} class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item,
 * testing if the queue is empty, and iterating through
 * the items in FIFO order.
 * <p>
 * This implementation uses a resizing array, which double the underlying array
 * when it is full and halves the underlying array when it is one-quarter full.
 * The <em>enqueue</em> and <em>dequeue</em> operations take constant amortized time.
 * The <em>size</em>, <em>peek</em>, and <em>is-empty</em> operations takes
 * constant time in the worst case.
 */
public class ResizingArrayQueue<E> implements Iterable<E> {

    private static final int INIT_CAPACITY = 8;

    private E[] q;      // queue elements
    private int n;      // number of elements on queue
    private int first;  // index of first element of queue
    private int last;   // index of end element of queue

    /**
     * Initializes an empty queue
     */
    @SuppressWarnings("unchecked")
    public ResizingArrayQueue() {
        q = (E[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    /**
     * Is this queue empty?
     *
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        return n;
    }

    // resize the underlying array
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity > n;

        E[] copy = (E[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }

        q = copy;
        first = 0;
        last = n;
    }

    /**
     * Adds the element to this queue.
     *
     * @param element the element to add
     */
    public void enqueue(E element) {
        // double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2 * n); // double size of array if necessary
        q[last++] = element;              // add element
        if (last == q.length) last = 0;   // wrap-around
        n++;
    }

    /**
     * Returns and removes element on this queue that was least recently added.
     *
     * @return the element on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public E dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        E element = q[first];
        q[first] = null;                    // to avoid loitering
        n--;
        first++;
        if (first == q.length) first = 0;   // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length / 4) resize(q.length / 2);

        return element;
    }

    /**
     * Returns (but not remove) the element least recently added to this queue.
     *
     * @return the element least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");

        return q[first];
    }

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
        return new ArrayIterator();
    }

    // an array iterator, from first to last
    private class ArrayIterator implements Iterator<E> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            return q[(i++ + first) % q.length];
        }
    }

}
