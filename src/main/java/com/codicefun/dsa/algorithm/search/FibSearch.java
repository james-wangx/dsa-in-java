package com.codicefun.dsa.algorithm.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 */
public class FibSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        int index = fibSearch(arr, 10);
        System.out.println(index);
    }

    /**
     * 得到斐波那契数组
     *
     * @return 斐波那契数组
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

    /**
     * 斐波那契查找（非递归）
     *
     * @param arr 待查找的数组
     * @param key 需要找的关键码（值）
     * @return 找到返回下标，否则返回 -1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; // 标识斐波那契分割数值的下标
        int mid;
        int[] f = fib();

        // 获取斐波那契分割数值下标
        while (high > f[k] - 1) {
            k++;
        }

        // 因为 f[k] 值可能大于 a 的长度，因此需要构建一个新的数组
        int[] temp = Arrays.copyOf(arr, f[k]);

        // 用数组随后的元素填充 temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = temp[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, high);
            }
        }

        return -1;
    }
}
