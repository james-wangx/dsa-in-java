package com.codicefun.dsa.datastructure.bag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedBagTest {

    final int n = 10;
    LinkedBag<Integer> bag = new LinkedBag<>();

    @Test
    void Add_Normal_SizeIsEqual() {
        for (int i = 0; i < n; i++) {
            bag.add(i);
        }

        assertEquals(n, bag.size());
    }

}
