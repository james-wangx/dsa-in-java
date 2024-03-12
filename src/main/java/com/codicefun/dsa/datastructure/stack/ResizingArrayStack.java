package com.codicefun.dsa.datastructure.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<E> implements Iterable<E> {

    private static final int INIT_CAPACITY = 8;

    private E[] a;
    private int n;

    /**
     * Initializes an empty stack.
     */
    @SuppressWarnings("unchecked")
    public ResizingArrayStack() {
        a = (E[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    /**
     * Is this stack empty?
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size() {
        return n;
    }

    /**
     * resize the underlying array holding the elements
     *
     * @param capacity the new capacity to resize
     */
    private void resize(int capacity) {
        a = Arrays.copyOf(a, capacity);
    }

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    public void push(E item) {
        // double size of array if necessary
        if (n == a.length) {
            resize(2 * a.length);
        }

        a[n++] = item;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        E item = a[n - 1];
        a[n - 1] = null; // to avoid loitering
        n--;

        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }

        return item;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        return a[n - 1];
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * an array iterator, in reverse order
     */
    private class ReverseArrayIterator implements Iterator<E> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
        }

        @Override
        public boolean hasNext() {
            return i >= 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return a[i--];
        }
    }

}
