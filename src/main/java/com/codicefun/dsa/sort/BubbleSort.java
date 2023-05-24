package com.codicefun.dsa.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort implements Sort {

    public int[] sort(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);

        int temp;
        boolean exchange; // 是否进行过交换

        for (int i = 0; i < arr.length - 1; i++) {
            exchange = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    exchange = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!exchange) {
                // 如果没有进行过交换，标识排序完毕，退出循环
                break;
            }
        }

        return arr;
    }

}
