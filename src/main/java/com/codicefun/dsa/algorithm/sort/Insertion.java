package com.codicefun.dsa.algorithm.sort;

import java.util.Comparator;

/**
 * The {@code Insertion} class provides static methods for sorting an
 * array using insertion sort.
 * <p>
 * In the worst case, this implementation makes ~ &frac12; <em>n</em><sup>2</sup>
 * compares and ~ &frac12; <em>n</em><sup>2</sup> exchanges to sort an array
 * of length <em>n</em>. So, it is not suitable for sorting large arbitrary
 * arrays. More precisely, the number of exchanges is exactly equal to the
 * number of inversions. So, for example, it sorts a partially-sorted array
 * in linear time.
 * <p>
 * This sorting algorithm is stable.
 * It uses &Theta;(1) extra memory (not including the input array).
 */
public class Insertion {

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a the array to sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Rearranges the subarray a[lo...hi) in ascending order, using the natural order.
     *
     * @param a  the array to be sorted
     * @param lo left endpoint (inclusive)
     * @param hi right endpoint (exclusive)
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(j, j - 1); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Rearranges the array in ascending order, using a comparator.
     *
     * @param a          the array to be sorted
     * @param comparator the comparator specifying the order
     */
    public static void sort(Comparable[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(comparator, j, j - 1); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    /**
     * Rearrange the subarray a[lo...hi) in ascending order, using a comparator.
     *
     * @param a          the array to be sorted
     * @param lo         left endpoint (inclusive)
     * @param hi         right endpoint (exclusive)
     * @param comparator the comparator specifying the order
     */
    public static void sort(Comparable[] a, int lo, int hi, Comparator comparator) {
        for (int i = 1; i < hi; i++) {
            for (int j = i; j > lo && less(j, j - 1); j--) {
                swap(a, j, j - 1);
            }
        }
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    private static boolean less(Comparator comparator, Comparable v, Comparable w) {
        return comparator.compare(v, w) < 0;
    }

    // exchange a[i] and a[j]
    private static void swap(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
