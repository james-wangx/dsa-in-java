package com.codicefun.dsa.sort;

/**
 * 插入排序
 */
public class InsertSort {
    static int count;

    public static void main(String[] args) {
        // 测试一下冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数的数组
        int[] array = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); // 生成一个 [0, 8000000] 范围的随机数
        }

        long start = System.currentTimeMillis();
        insertSort(array);
        long end = System.currentTimeMillis();
        System.out.printf("排序时间: %.3fs\n", (end - start) / 1000.0);
        System.out.printf("移动了 %d 次", count);
    }

    /**
     * 插入排序
     *
     * @param array 待排序的数组
     */
    public static void insertSort(int[] array) {
        int insertVal;
        int insertIndex;

        for (int i = 1; i < array.length; i++) {
            insertVal = array[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
                count++;
            }
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertVal;
            }
        }
    }
}
