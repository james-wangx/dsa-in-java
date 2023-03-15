package com.codicefun.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {

    public ArrayStack<String> stack = new ArrayStack<>(10);

    @Test
    public void testPush() {
        for (int i = 1; i <= 10; i++) {
            stack.push(String.valueOf(i * 10));
        }

        assertThrows(StackFullException.class, () -> stack.push("1"));

        int i = 1;
        for (String str : stack) {
            assertEquals(str, String.valueOf(i++ * 10));
        }
    }

    @Test
    public void testPop() {
        for (int i = 1; i <= 10; i++) {
            stack.push(String.valueOf(i * 10));
        }

        for (int i = 10; i >= 1; i--) {
            assertEquals(stack.pop(), String.valueOf(i * 10));
        }

        assertThrows(StackEmptyException.class, () -> stack.pop());
    }
}
