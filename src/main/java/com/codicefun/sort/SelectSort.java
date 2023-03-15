package com.codicefun.sort;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        // 测试一下冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数的数组
        int[] array = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); // 生成一个 [0, 8000000] 范围的随机数
        }

        long start = System.currentTimeMillis();
        selectSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("排序时间: %.3fs", (end - start) / 1000.0);
    }

    /**
     * 选择排序
     *
     * @param array 待排序的数组
     */
    public static void selectSort(int[] array) {
        int minIndex;
        int min;

        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            min = array[minIndex];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    minIndex = j;
                    min = array[minIndex];
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
    }
}
