package com.codicefun.dsa.datastructure.stack;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResizingArrayStackTest {

    @Test
    void Push_Normal_SizeIsEqual() {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        final int n = 7;

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        assertEquals(n, stack.size());
    }

    @Test
    void Push_OutOfCapacity_SizeIsEqual() {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        final int n = 100;

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        assertEquals(n, stack.size());
    }

    @Test
    void Pop_Normal_ItemAndSizeIsEqual() {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        final int n = 7;

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
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        final int n = 7;

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void Peek_Normal_ItemIsEqual() {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        final int n = 7;

        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        assertEquals(n - 1, stack.peek());
    }

}
