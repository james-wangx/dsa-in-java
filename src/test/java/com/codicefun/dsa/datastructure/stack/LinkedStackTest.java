package com.codicefun.dsa.datastructure.stack;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedStackTest {

    @Test
    void Push_Normal_SizeIsEqual() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        final int n = 10;

        for (int i = 0; i < n; i++) {
            stack.push(n);
        }

        assertEquals(n, stack.size());
    }

    @Test
    void Pop_Normal_ItemAndSizeIsEqual() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        final int n = 10;

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertEquals(0, stack.size());
    }

    @Test
    void Pop_More_Underflow() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        final int n = 10;

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            stack.pop();
        }

        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void Peek_Normal_ItemIsEqual() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        final int n = 10;

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        assertEquals(n - 1, stack.peek());
    }

}