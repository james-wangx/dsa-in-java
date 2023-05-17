package com.codicefun.dsa.sort;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        // 测试一下冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数的数组
        int[] array = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); // 生成一个 [0, 8000000] 范围的随机数
        }

        long start = System.currentTimeMillis();
        radixSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("排序时间: %.3fs\n", (end - start) / 1000.0);

        // 通过指定 VM 选项：-ea，启动断言
        for (int i = 0; i < array.length - 1; i++) {
            assert array[i] <= array[i + 1];
        }
    }

    /**
     * 基数排序
     *
     * @param array 待排序的数组
     */
    public static void radixSort(int[] array) {
        // 为了防止益处，每一个桶大小为 array.length
        int[][] buckets = new int[10][array.length];
        // 为了记录每个桶实际存放了多少数据，定义一个一位数组，记录每个桶放入元素的个数
        int[] counts = new int[10];

        int max = array[0];
        int maxLength;
        for (int item : array) {
            if (item > max) {
                max = item;
            }
        }
        maxLength = (max + "").length();

        for (int i = 0; i < maxLength; i++) {
            for (int item : array) {
                int digit = item / (int) Math.pow(10, i) % 10;
                buckets[digit][counts[digit]++] = item;
            }

            int index = 0; // 数组索引
            /*
             * j: 计数索引/一维数组索引
             * k: 二维数组索引
             */
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < counts[j]; k++) {
                    array[index++] = buckets[j][k];
                }
                counts[j] = 0;
            }
        }
    }
}
