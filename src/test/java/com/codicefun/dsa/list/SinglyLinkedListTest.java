package com.codicefun.dsa.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SinglyLinkedListTest {

    SinglyLinkedList<String> list = new SinglyLinkedList<>();

    @Test
    public void testAdd() {
        for (int i = 1; i <= 10; i++) {
            assertTrue(list.add(String.valueOf(i * 10)));
        }

        int i = 1;
        for (String item : list) {
            assertEquals(item, String.valueOf((i++) * 10));
        }
    }

    @Test
    public void testRemove() {
        for (int i = 1; i <= 10; i++) {
            list.add(String.valueOf(i * 10));
        }

        for (int i = 1; i <= 10; i++) {
            assertTrue(list.remove(String.valueOf(i * 10)));
        }

        for (int i = 1; i <= 10; i++) {
            list.add(String.valueOf(i * 10));
        }

        for (int i = 10; i >= 1; i--) {
            assertTrue(list.remove(String.valueOf(i * 10)));
        }
    }

    @Test
    public void testSize() {
        for (int i = 1; i <= 10; i++) {
            list.add(String.valueOf(i * 10));
        }

        assertEquals(list.size(), 10);
    }

}
