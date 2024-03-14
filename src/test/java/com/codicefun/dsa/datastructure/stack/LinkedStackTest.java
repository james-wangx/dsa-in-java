package com.codicefun.dsa.datastructure.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedStackTest {

    final int n = 10;
    LinkedStack<Integer> stack = new LinkedStack<>();

    @BeforeEach
    void init() {
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
    }

    @Test
    void Push_Normal_SizeIsEqual() {
        assertEquals(n, stack.size());
    }

    @Test
    void Pop_Normal_ItemAndSizeIsEqual() {
        for (int i = n - 1; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertEquals(0, stack.size());
    }

    @Test
    void Pop_More_Underflow() {
        for (int i = n - 1; i >= 0; i--) {
            stack.pop();
        }

        assertThrows(NoSuchElementException.class, stack::pop);
    }

    @Test
    void Peek_Normal_ItemIsEqual() {
        assertEquals(n - 1, stack.peek());
    }

}
