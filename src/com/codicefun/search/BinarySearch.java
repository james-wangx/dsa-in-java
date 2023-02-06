package com.codicefun.search;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int index = binarySearch(arr, 0, arr.length - 1, 7);
        System.out.println(index);
    }

    /**
     * 二分查找
     *
     * @param arr   待查找的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param val   要找的值
     * @return 找到返回下标，否则返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int val) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (val < midVal) {
            return binarySearch(arr, left, mid - 1, val);
        } else if (val > midVal) {
            return binarySearch(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }
}
