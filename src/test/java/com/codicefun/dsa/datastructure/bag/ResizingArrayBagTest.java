package com.codicefun.dsa.datastructure.bag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResizingArrayBagTest {

    ResizingArrayBag<Integer> bag;

    @Test
    void Add_Normal_SizeIsEqual() {
        bag = new ResizingArrayBag<>();
        final int n = 7;
        for (int i = 0; i < n; i++) {
            bag.add(i);
        }

        assertEquals(n, bag.size());
    }

    @Test
    void Add_Grown_SizeIsEqual() {
        bag = new ResizingArrayBag<>();
        final int n = 10;
        for (int i = 0; i < n; i++) {
            bag.add(i);
        }

        assertEquals(n, bag.size());
    }

}