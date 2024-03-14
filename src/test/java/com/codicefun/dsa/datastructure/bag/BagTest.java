package com.codicefun.dsa.datastructure.bag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BagTest {

    @Test
    void Add_Normal_SizeIsEqual() {
        Bag<Integer> bag = new Bag<>();
        final int n = 10;
        for (int i = 0; i < n; i++) {
            bag.add(i);
        }

        assertEquals(n, bag.size());
    }

}
