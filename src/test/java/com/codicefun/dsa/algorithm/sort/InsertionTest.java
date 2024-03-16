package com.codicefun.dsa.algorithm.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InsertionTest {

    @Test
    void test() {
        int n = 100;
        Integer[] array = new Integer[n];

        // assignment
        for (int i = 0; i < n; i++) {
            array[i] = (int) (Math.random() * n * 10);
        }

        Insertion.sort(array);

        for (int i = 0; i < n - 1; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

}
