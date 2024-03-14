package com.codicefun.dsa.datastructure.bag;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<E> implements Iterable<E> {

    private static final int INIT_CAPACITY = 8;

    private E[] a; // array of bag
    private int n; // number if elements on bag

    /**
     * Initializes an empty bag
     */
    @SuppressWarnings("unchecked")
    public ResizingArrayBag() {
        a = (E[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    /**
     * Is this bag empty?
     *
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of elements in this bag.
     *
     * @return the number of elements in this bag
     */
    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity > n;
        a = Arrays.copyOf(a, capacity);
    }

    /**
     * Adds the element to this bag.
     *
     * @param element the element to add
     */
    public void add(E element) {
        if (n == a.length) resize(2 * n);   // double size of array if necessary
        a[n++] = element;                   // add element
    }

    /**
     * Returns an iterator that iterates over the element in the bag in arbitrary order.
     *
     * @return an iterator that iterates over the element in the bag in arbitrary order
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    // an array iterator
    private class ArrayIterator implements Iterator<E> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();

            return a[i++];
        }
    }

}
