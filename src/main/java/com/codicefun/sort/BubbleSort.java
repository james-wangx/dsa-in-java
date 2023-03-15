package com.codicefun.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        // int[] array = {3, 9, -1, 10, -2};

        // 测试一下冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数的数组
        int[] array = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); // 生成一个 [0, 8000000] 范围的随机数
        }

        long start = System.currentTimeMillis();
        bubbleSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("排序时间: %.3fs", (end - start) / 1000.0);
    }

    public static void bubbleSort(int[] array) {
        int temp;
        boolean exchange; // 是否进行过交换

        for (int i = 0; i < array.length - 1; i++) {
            exchange = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    exchange = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (!exchange) {
                // 如果没有进行过交换，标识排序完毕，退出循环
                break;
            }
        }
    }
}
