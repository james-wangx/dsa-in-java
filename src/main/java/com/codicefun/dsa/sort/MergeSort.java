package com.codicefun.dsa.sort;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        // 测试一下冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数的数组
        int[] array = new int[80000];
        int[] temp = new int[array.length];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); // 生成一个 [0, 8000000] 范围的随机数
        }

        long start = System.currentTimeMillis();
        mergeSort(array, 0, array.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.printf("排序时间: %.3fs\n", (end - start) / 1000.0);

        // 通过指定 VM 选项：-ea，启动断言
        for (int i = 0; i < array.length - 1; i++) {
            assert array[i] <= array[i + 1];
        }
    }

    /**
     * 归并排序
     *
     * @param array 待排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param array 待排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left; // 左边序列的索引
        int j = mid + 1; // 右边序列的索引
        int t = 0; // temp 数组的索引

        // 先把左右两边数据按顺序填到 temp 中，直到有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[t++] = array[i++];
            } else {
                temp[t++] = array[j++];
            }
        }

        // 把有剩余的数据加到 temp 后边
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        while (j <= right) {
            temp[t++] = array[j++];
        }

        // 将 temp 数组元素拷贝到 array
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            array[tempLeft++] = temp[t++];
        }
    }
}
