package com.codicefun.search;

/**
 * 插值查找
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int index = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(index);
    }

    /**
     * 插值查找
     *
     * @param arr   待查找的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param val   要找的值
     * @return 找到返回下标，否则返回 -1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int val) {
        // val < arr[0] || val > arr[arr.length - 1] 防止 mid 越界
        if (left > right || val < arr[0] || val > arr[arr.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (val < midVal) {
            return insertValueSearch(arr, left, mid - 1, val);
        } else if (val > midVal) {
            return insertValueSearch(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }
}
