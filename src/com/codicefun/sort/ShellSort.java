package com.codicefun.sort;

/**
 * 希尔排序/缩小增量排序
 */
public class ShellSort {
    static int count;

    public static void main(String[] args) {
        // 测试一下冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数的数组
        int[] array = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); // 生成一个 [0, 8000000] 范围的随机数
        }

        long start = System.currentTimeMillis();
        shellSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("排序时间: %.3fs\n", (end - start) / 1000.0);
        System.out.printf("移动了 %d 次", count);
    }

    /**
     * 希尔排序
     *
     * @param array 待排序的数组
     */
    public static void shellSort(int[] array) {
        int insertVal;
        int insertIndex;

        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                insertVal = array[i];
                insertIndex = i - gap;
                while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                    array[insertIndex + gap] = array[insertIndex];
                    insertIndex -= gap;
                    count++;
                }
                array[insertIndex + gap] = insertVal;
            }
        }
    }
}
