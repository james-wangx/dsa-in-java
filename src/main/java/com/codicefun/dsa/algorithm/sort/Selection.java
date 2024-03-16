package com.codicefun.dsa.algorithm.sort;

import java.util.Comparator;

/**
 * The {@code Selection} class provides static methods for sorting an
 * array using <em>selection sort</em>.
 * This implementation makes ~ &frac12; <em>n</em><sup>2</sup> compares to sort
 * any array of length <em>n</em>, so it is not suitable for sorting large arrays.
 * It performs exactly <em>n</em> exchanges.
 * <p>
 * This sorting algorithm is not stable. It uses &Theta;(1) extra memory
 * (not including the input array).
 */
public class Selection {

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) min = j;
            }
            swap(a, i, min);
        }
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    // exchange a[i] and a[j]
    private static void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
