package com.codicefun.dsa.kmp;


import com.codicefun.dsa.algorithm.kmp.KMPAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
