package com.codicefun.dsa.recursion;

/**
 * 递归解决八皇后问题
 */
public class Queue8 {

    static int count;
    // 定义一个 max 表示共有多少皇后
    int max = 8;
    // 定义数组 array，保存皇后放置位置的结果，比如 array = {0, 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有 %d 种解法", count);
    }

    // 放置第 n 个皇后
    private void check(int n) {
        if (n == max) {
            // 正在放第 9 个皇后，也就是 8 个皇后已经放好
            print();
            count++;
            return;
        }

        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前的皇后放在该行的第一列
            array[n] = i;
            // 判断当是否冲突
            if (judge(n)) {
                // 接着放 n + 1 个皇后，开始递归
                check(n + 1);
            }
            // 如果冲突，接着放到下一列
        }
    }

    /**
     * 判断当前放置的皇后是否与之前的冲突
     *
     * @param n 皇后所在的行
     * @return 判断结果 true of false
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 判断是否在同一列或同一斜线上
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }

        return true;
    }

    // 将皇后摆放的位置输出
    private void print() {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
