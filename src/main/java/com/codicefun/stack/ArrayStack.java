package com.codicefun.stack;

import java.util.Iterator;

public class ArrayStack<E> implements Iterable<E> {
    private final int maxSize;
    private final Object[] array;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new Object[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(E value) {
        if (isFull()) {
            System.out.println("Stack is full!");
            return;
        }

        array[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return (E) array[top--];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
        }

        for (int i = top; i > -1; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private class StackIterator implements Iterator<E> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index <= top;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E) array[index++];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
}
