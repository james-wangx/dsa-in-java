package com.codicefun.dsa.algorithm.euclidean;

/**
 * Euclidean Algorithm
 */
public class Euclidean {

    /**
     * Recursive implementation of the GCD algorithm
     *
     * @param a bigger
     * @param b smaller
     * @return Greatest Common Divisor
     */
    public static int gcdRecursive(int a, int b) {
        return b == 0 ? a : gcdRecursive(b, a % b);
    }

    /**
     * GCD algorithm
     *
     * @param a bigger
     * @param b smaller
     * @return Greatest Common Divisor
     */
    public static int gcd(int a, int b) {
        int t = 1;

        while (t != 0) {
            t = a % b;
            a = b;
            b = t;
        }

        return a;
    }

}
