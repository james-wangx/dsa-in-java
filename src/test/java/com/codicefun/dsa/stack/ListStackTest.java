package com.codicefun.dsa.stack;

import com.codicefun.dsa.datastructure.stack.ListStack;
import com.codicefun.dsa.datastructure.stack.StackEmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListStackTest {

    public ListStack<String> stack = new ListStack<>();

    @Test
    public void shouldThrowStackEmptyException() {
        assertThrows(StackEmptyException.class, () -> stack.pop());
    }

    @Test
    public void shouldBeEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPush() {
        for (int i = 1; i <= 10; i++) {
            stack.push(String.valueOf(i * 10));
        }

        int i = 10;
        for (String str : stack) {
            assertEquals(str, String.valueOf(i * 10));
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
    }

    @Test
    public void testPeek() {
        stack.push("first");
        assertEquals(stack.peek(), "first");

        stack.push("second");
        assertEquals(stack.peek(), "second");
    }
}
