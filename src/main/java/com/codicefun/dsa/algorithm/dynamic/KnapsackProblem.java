package com.codicefun.dsa.algorithm.dynamic;

import java.util.Arrays;

/**
 * 背包问题
 */
public class KnapsackProblem {

    public static int solve(int[] weight, int[] value, int capacity) {
        int number = value.length; // 物品的个数
        int[][] records = new int[number + 1][capacity + 1]; // 记录表
        int[][] path = new int[number + 1][capacity + 1]; // 物品存放情况

        // 将第一行和第一列置为1
        for (int i = 0; i < records.length; i++) {
            records[i][0] = 0;
        }
        Arrays.fill(records[0], 0);

        // 动态规划处理
        for (int i = 1; i < records.length; i++) {
            for (int j = 1; j < records[0].length; j++) {
                if (weight[i - 1] > j) { // 当前物品的重量 > 背包重量
                    // 使用上一行的记录
                    records[i][j] = records[i - 1][j];
                } else {
                    // 对比上一行的记录的价值
                    // 和本物品的价值 + 本物品外剩余重量的最大价值
                    int temp;
                    if (records[i - 1][j] < (temp = (value[i - 1] + records[i - 1][j - weight[i - 1]]))) {
                        records[i][j] = temp;
                        path[i][j] = 1;
                    } else {
                        records[i][j] = records[i - 1][j];
                    }
                }
            }
        }

        // 获取最大值
        int max = 0;
        for (int[] line : records) {
            for (int record : line) {
                if (max < record) {
                    max = record;
                }
            }
        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) { // 从 path 的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第 %d 个物品放入背包\n", i);
                j -= weight[i - 1]; // find in remaining weight
            }
            i--;
        }

        return max;
    }


    /**
     * 解决背包问题
     *
     * @param weight   重量
     * @param value    价值
     * @param capacity 容量
     */
    public static int mySolve(int[] weight, int[] value, int capacity) {
        int number = value.length; // 物品的个数
        class Record {
            int[] backpack;

            public Record() {
                backpack = new int[number];
            }

            public int getValue() {
                int sum = 0;
                for (int i = 0; i < backpack.length; i++) {
                    sum += backpack[i] * value[i];
                }

                return sum;
            }
        }
        // 记录所有情况的二维数组
        Record[][] records = new Record[number + 1][capacity + 1];

        // 初始化背包记录
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < records[0].length; j++) {
                records[i][j] = new Record();
            }
        }

        // 动态规划处理
        for (int i = 1; i < records.length; i++) {
            for (int j = 1; j < records[0].length; j++) {
                // i 表示放入的物品（i - 1 是 value 表的索引）
                // j 是当前的重量
                if (weight[i - 1] > j) { // 如果新增商品重量大于当前背包重量
                    // 直接使用上一行的策略
                    records[i][j].backpack = records[i - 1][j].backpack.clone();
                } else {
                    // 上一行（不包括本物品的重量）和 当前物品的重量 + 上一行（不包括本物品时剩余重量）的最大值
                    if (records[i - 1][j].getValue()
                            < value[i - 1] + records[i - 1][j - weight[i - 1]].getValue()) {
                        // 加入新物品后价值更高
                        records[i][j].backpack = records[i - 1][j - weight[i - 1]].backpack.clone();
                        records[i][j].backpack[i - 1]++;
                    } else {
                        records[i][j].backpack = records[i - 1][j].backpack.clone();
                    }
                }
            }
        }

        int max = 0, line = 0, column = 0, temp;
        for (int i = 0; i < records.length; i++) {
            for (int j = 0; j < records[0].length; j++) {
                if ((temp = records[i][j].getValue()) > max) {
                    line = i;
                    column = j;
                    max = temp;
                }
            }
        }

        System.out.println("背包中总价值最大的情况为：");
        int[] backpack = records[line][column].backpack;
        for (int i = 0; i < backpack.length; i++) {
            System.out.printf("%d 个 %d号物品\n", backpack[i], i + 1);
        }

        return max;
    }
}
