package com.codicefun.kmp;

import org.junit.Test;

import static org.junit.Assert.*;

public class KMPAlgorithmTest {

    @Test
    public void testKmpNext() {
        String dest = "ababcababa";
        int[] kmpNext = KMPAlgorithm.kmpNext(dest);

        assertArrayEquals(kmpNext, new int[]{0, 0, 1, 2, 0, 1, 2, 3, 4, 3});
    }

    @Test
    public void testKmpSearch() {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = KMPAlgorithm.kmpNext(str2);

        assertEquals(KMPAlgorithm.kmpSearch(str1, str2, next), 15);
    }
}
