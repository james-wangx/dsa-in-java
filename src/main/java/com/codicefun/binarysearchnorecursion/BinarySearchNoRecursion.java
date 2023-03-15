package com.codicefun.binarysearchnorecursion;

/**
 * 二分查找的非递归实现
 */
public class BinarySearchNoRecursion {

    /**
     * 二分查找非递归实现
     *
     * @param arr    待查找的数组，arr 是升序排列的
     * @param target 需要查找的数
     * @return 返回对应下标，-1 表示没找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        // 继续查找
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
                // 去左边
            } else if (target < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
