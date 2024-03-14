package com.codicefun.dsa.algorithm.sort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SortTest {

    private static final int MAX_SIZE = 1000;
    private static final int[] ARRAY = new int[MAX_SIZE];
    private Sort sort;

    @BeforeAll
    public static void initRandomArray() {
        for (int i = 0; i < MAX_SIZE; i++) {
            ARRAY[i] = (int) (Math.random() * MAX_SIZE * 10);
        }
    }

    @Test
    void testBubbleSort() {
        sort = new BubbleSort();
        int[] arr = sort.sort(ARRAY);

        for (int i = 0; i < arr.length - 1; i++) {
            assertTrue(arr[i] <= arr[i + 1]);
        }
    }

    @Test
    void testInsertionSort() {
        sort = new InsertionSort();
        int[] arr = sort.sort(ARRAY);

        for (int i = 0; i < arr.length - 1; i++) {
            assertTrue(arr[i] <= arr[i + 1]);
        }
    }

}
