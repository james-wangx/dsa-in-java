package com.codicefun.dsa.datastructure.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Stack} class represents a last-in-first-out (LIFO) stack of generic items.
 * It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 * for peeking at the top item, testing if the stack is empty, and iterating through
 * the items in LIFO order.
 * <p>
 * This implementation uses a singly linked list with a static nested class for
 * linked-list nodes. See {@link LinkedStack} for the version that uses a non-static nested class.
 * See {@link ResizingArrayStack} for a version that uses a resizing array.
 * The <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 * operations all take constant time in the worst case.
 *
 * @param <E> the generic type each element in this stack
 */
public class Stack<E> implements Iterable<E> {

    private Node<E> first; // top of stack
    private int n; // size of stack

    /**
     * Initializes an empty stack.
     */
    public Stack() {
        first = null;
        n = 0;
    }

    /**
     * Returns ture if this stack is empty.
     *
     * @return true if this stack is empty
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the element to this stack.
     *
     * @param element the element to add
     */
    public void push(E element) {
        Node<E> oldFirst = first;
        first = new Node<>();
        first.element = element;
        first.next = oldFirst;
        n++;
    }

    /**
     * Removes and returns the element most recently added to this stack.
     *
     * @return the element most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        E element = first.element;
        first = first.next;
        n--;

        return element;
    }

    /**
     * Returns (but not remove) the element most recently added to this stack.
     *
     * @return the element most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        return first.element;
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
    private static class Node<E> {
        private E element;
        private Node<E> next;
    }

    private class LinkedIterator implements Iterator<E> {
        private Node<E> current;

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
