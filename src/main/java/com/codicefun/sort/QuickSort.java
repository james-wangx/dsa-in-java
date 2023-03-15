package com.codicefun.sort;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        // 测试一下冒泡排序的速度 O(n^2)，给 80000 个数据，测试
        // 创建 80000 个随机数的数组
        int[] array = new int[80000];

        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); // 生成一个 [0, 8000000] 范围的随机数
        }

        long start = System.currentTimeMillis();
        // quickSortMiddle(array, 0, array.length - 1);
        quickSortLeft(array, 0, array.length - 1);
        long end = System.currentTimeMillis();
        System.out.printf("排序时间: %.3fs\n", (end - start) / 1000.0);

        // 通过指定 VM 选项：-ea，启动断言
        for (int i = 0; i < array.length - 1; i++) {
            assert array[i] <= array[i + 1];
        }
    }

    /**
     * 快速排序，基准值取数组中间的数
     *
     * @param array 待排序的数组
     * @param left  左索引
     * @param right 右索引
     */
    public static void quickSortMiddle(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int l = left;
        int r = right;
        // 中间值
        int pivot = array[(left + right) / 2];

        // 让比 pivot 小的放在其左边，比 pivot 大的放在其右边
        while (true) {
            // 找到比 pivot 大的
            while (array[l] < pivot) {
                l++;
            }
            // 找到比 pivot 小的
            while (array[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            if (array[l] == array[r] && array[l] == pivot) {
                if (l + 1 == r) {
                    break;
                }
                l++;
            }
            swap(array, l, r);
        }

        quickSortMiddle(array, left, l - 1);
        quickSortMiddle(array, l + 1, right);
    }

    /**
     * 快速排序，基准值取数组最左边的数
     *
     * @param array 待排序的数组
     * @param left  左索引
     * @param right 右索引
     */
    public static void quickSortLeft(int[] array, int left, int right) {
        if (left < right) {
            int index = partition(array, left, right);
            quickSortLeft(array, left, index - 1);
            quickSortLeft(array, index + 1, right);
        }
    }

    /**
     * 数组分区
     *
     * @param array 待分区的数组
     * @param left  左索引
     * @param right 右索引
     * @return 分区临界索引
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    public static int partition(int[] array, int left, int right) {
        int pivot = left;
        int index = pivot + 1;

        for (int i = index; i <= right; i++) {
            if (array[i] < array[pivot]) {
                if (i != index) {
                    swap(array, i, index);
                }
                index++;
            }
        }

        if (pivot != index - 1) {
            swap(array, pivot, index - 1);
        }

        return index - 1;
    }

    /**
     * 数组交换元素
     *
     * @param array 待交换元素的数组
     * @param left  左索引
     * @param right 右索引
     */
    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}
