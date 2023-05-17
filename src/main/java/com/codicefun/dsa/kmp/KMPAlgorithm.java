package com.codicefun.dsa.kmp;

public class KMPAlgorithm {

    /**
     * Get the Partial Match Table
     */
    public static int[] kmpNext(String dest) {
        // Array to save the Partial Match Value
        int[] next = new int[dest.length()];
        next[0] = 0;

        int i = 1; // dest str index
        int j = 0; // prefix str index
        while (i < dest.length()) {
            if (dest.charAt(i) == dest.charAt(j)) {
                next[i++] = ++j;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                i++;
            }
        }

        return next;
    }

    /**
     * KMP Search Algorithm
     *
     * @param str1 dest str
     * @param str2 search str
     * @param next Partial Match Table
     * @return first index or -1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        int i = 0; // str1 index
        int j = 0; // str2 index

        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                i++;
            }
        }

        if (j == str2.length()) {
            return i - j;
        }
        return -1;
    }
}
