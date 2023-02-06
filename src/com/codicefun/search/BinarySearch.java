package com.codicefun.search;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 10, 10, 10, 10, 89, 1000, 1234};

        int index = binarySearch(arr, 0, arr.length - 1, 7);
        System.out.println(index);

        System.out.println(binarySearchAll(arr, 0, arr.length - 1, 10));
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

    /**
     * 二分查找，返回全部索引
     *
     * @param arr   待查找的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param val   要找的值
     * @return 找到返回下标，否则返回 -1
     */
    public static Object binarySearchAll(int[] arr, int left, int right, int val) {
        if (left > right) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(-1);
            return list;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (val < midVal) {
            return binarySearchAll(arr, left, mid - 1, val);
        } else if (val > midVal) {
            return binarySearchAll(arr, mid + 1, right, val);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int pos = mid;
            while (arr[--mid] == val) {
                list.add(mid);
            }
            while (arr[pos] == val) {
                list.add(pos++);
            }
            list.sort(Comparator.comparingInt(o -> o));
            return list;
        }
    }
}
