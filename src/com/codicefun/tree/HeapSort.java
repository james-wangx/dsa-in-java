package com.codicefun.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        heapSort(arr);
    }

    /**
     * 堆排序
     *
     * @param arr 待排序的数组
     */
    public static void heapSort(int[] arr) {
        // 转为大顶堆，从最后一个叶子开始
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        System.out.println(Arrays.toString(arr));

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树）调整成一个大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示多少个元素继续调整
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];

        // arr[i * 2 + 1] 是 arr[i] 的左节点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[j] > temp) {
                // 如果子节点大于父节点，将较大的值赋给当前节点
                arr[i] = arr[j];
                i = j; // i 指向 j 继续循环比较
            } else {
                break;
            }
        }

        // for 循环后，我们已经将以 i 为夫节点的树的最大值，放在了最顶部
        arr[i] = temp; // 将 temp 值放到调整后的位置
    }

    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}
