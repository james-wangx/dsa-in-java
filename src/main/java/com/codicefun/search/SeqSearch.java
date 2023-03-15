package com.codicefun.search;

/**
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 11);

        if (index == -1) {
            System.out.println("没找到");
        } else {
            System.out.println("索引为 " + index);
        }
    }

    /**
     * 线性查找，找到一个满足条件的值就返回
     *
     * @param arr   待查找的数组
     * @param value 要查找的数
     * @return 找到返回索引，否则返回 -1
     */
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;
    }
}
