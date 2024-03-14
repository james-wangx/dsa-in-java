package com.codicefun.dsa.datastructure.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResizingArrayQueueTest {

    ResizingArrayQueue<Integer> queue;

    @BeforeEach
    void init() {
        queue = new ResizingArrayQueue<>();
    }

    @Test
    void In_Normal_SizeIsEqual() {
        final int n = 7;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        assertEquals(n, queue.size());
    }

    @Test
    void In_Grown_SizeIsEqual() {
        final int n = 10;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        assertEquals(n, queue.size());
    }

    @Test
    void Out_Normal_SizeAndElementIsEqual() {
        final int n = 7;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < n; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertEquals(0, queue.size());
    }

    @Test
    void Out_Shrink_SizeAndElementIsEqual() {
        final int n = 100;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < n - n / 4; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertEquals(n / 4, queue.size());
    }

    @Test
    void Out_More_Underflow() {
        final int n = 7;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < n; i++) {
            queue.dequeue();
        }

        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void Peek_Normal_ElementIsEqual() {
        final int n = 7;
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        assertEquals(0, queue.peek());
    }

    @Test
    void Print_Normal_Equal() {
        final int n = 7;
        final String s = "0 1 2 3 4 5 6";
        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        assertEquals(s, queue.toString());
    }

}
