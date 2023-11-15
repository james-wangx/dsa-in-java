package com.codicefun.dsa.algorithm.euclidean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EuclideanTest {

    @Test
    public void testGcd() {
        assertEquals(Euclidean.gcd(252, 105), 21);
        assertEquals(Euclidean.gcd(26, 14), 2);
        assertEquals(Euclidean.gcd(33, 44), 11);
        assertEquals(Euclidean.gcd(160, 7), 1);
    }

    @Test
    public void testGcdRecursive() {
        assertEquals(Euclidean.gcdRecursive(252, 105), 21);
        assertEquals(Euclidean.gcdRecursive(26, 14), 2);
        assertEquals(Euclidean.gcdRecursive(33, 44), 11);
        assertEquals(Euclidean.gcdRecursive(160, 7), 1);
    }

}
