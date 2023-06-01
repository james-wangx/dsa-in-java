package com.codicefun.dsa.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort implements Sort {

    /**
     * 插入排序
     *
     * @param array 待排序的数组
     */
    public int[] sort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        int insertVal;
        int insertIndex;

        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) { // 解决索引为 -1 和元素相等的情况
                arr[insertIndex + 1] = insertVal;
            }
        }

        return arr;
    }

}
