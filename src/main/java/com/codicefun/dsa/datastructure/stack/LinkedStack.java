package com.codicefun.dsa.datastructure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * The {@code LinkedStack} class represents a last-in-first-out (LIFO) stack of
 * generic items.
 * It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 * for peeking at the top item, testing if the stack is empty, and iterating through
 * the items in LIFO order.
 * <p>
 * This implementation uses a singly linked list with a non-static nested class for
 * linked-list nodes. See {@link Stack} for a version that uses a static nested class.
 * The <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 * operations all take constant time in the worst case.
 */
public class LinkedStack<E> implements Iterable<E> {

    private int n; // size of stack
    private Node first; // top of stack

    /**
     * Initializes an empty stack.
     */
    public LinkedStack() {
        this.n = 0;
        this.first = null;
        assert check();
    }

    /**
     * Is this stack empty?
     *
     * @return ture if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
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
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    public void push(E item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
        assert check();
    }

    /**
     * Removes and returns the item most recently added to this stack
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        E item = first.item; // save item to return
        first = first.next; // delete first item
        n--;
        assert check();

        return item; // return the saved item
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        return first.item;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in the stack in LIFO order, separated by space
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (E item : this) {
            sb.append(item).append(" ");
        }

        return sb.toString().trim();
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    // check internal invariants
    private boolean check() {
        // check a few properties of instance variable 'first'
        if (n < 0) {
            return false;
        }
        if (n == 0) {
            if (first != null) {
                return false;
            }
        } else if (n == 1) {
            if (first == null) {
                return false;
            }
            if (first.next != null) {
                return false;
            }
        } else {
            if (first == null) {
                return false;
            }
            if (first.next == null) {
                return false;
            }
        }

        // check internal consistency of instance variable n
        int numberOfNodes = 0;

        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
            numberOfNodes++;
        }

        if (numberOfNodes != n) {
            return false;
        }

        return true;
    }

    /**
     * Helper linked list class.
     */
    private class Node {
        private E item;
        private Node next;
    }

    /**
     * A liked-list iterator
     */
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

            E item = current.item;
            current = current.next;

            return item;
        }
    }

}
