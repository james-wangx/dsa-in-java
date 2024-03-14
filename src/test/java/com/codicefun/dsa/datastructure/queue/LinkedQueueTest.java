package com.codicefun.dsa.datastructure.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedQueueTest {

    final int n = 10;
    LinkedQueue<Integer> queue;

    @BeforeEach
    void init() {
        queue = new LinkedQueue<>();
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
    }

    @Test
    void In_Normal_SizeIsEqual() {
        assertEquals(n, queue.size());
    }

    @Test
    void Out_Normal_SizeAndElementIsEqual() {
        for (int i = 0; i < n; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertEquals(0, queue.size());
    }

    @Test
    void Out_More_Underflow() {
        for (int i = 0; i < n; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void Peek_Normal_ElementIsEqual() {
        assertEquals(0, queue.peek());
    }

}
