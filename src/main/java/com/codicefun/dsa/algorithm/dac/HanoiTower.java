package com.codicefun.dsa.algorithm.dac;

/**
 * 汉诺塔
 */
public class HanoiTower {

    /**
     * 汉诺塔的移动方法（使用分治算法）
     *
     * @param num 移动盘子的数目
     * @param a   起始盘
     * @param b   工具盘
     * @param c   终点盘
     */
    public static void move(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.printf("第 1 个盘：%c -> %c\n", a, c);
        } else {
            // 如果我们有 n >= 2 的情况，我们总是可以看作是两个盘 1.最下边的盘 2.上面的盘
            // 1、先把上面的盘 A -> B，移动过程中会使用 C
            move(num - 1, a, c, b);
            // 2、把最下面的盘 A -> C
            System.out.printf("第 %d 个盘：%c -> %c\n", num, a, c);
            // 3、把 B -> C，移动过程中会使用 A
            move(num - 1, b, a, c);
        }
    }
}
