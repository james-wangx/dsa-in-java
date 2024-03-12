package com.codicefun.dsa.algorithm.recursion;

/**
 * 递归解决迷宫问题
 */
public class Maze {
    public static void main(String[] args) {
        // 先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];

        // 使用 1 表示墙
        // 上下全部置为 1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 左右全部置为 1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        setWay(map, 1, 1);

        // 输出地图
        System.out.println("地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 小球找路，如果小球能够到达 map[6][5]，则通路找到
     * 当 map[i][j] 为 0 表示没有走过，1 表示墙，2 表示通路可以走，3 表示该路走不通
     * 在走迷宫时，需要确定一个策略：下 -> 右 -> 上 -> 左
     *
     * @param map 地图
     * @param i   行
     * @param j   列
     * @return 找到通路返回 true，否则返回 false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果该点还没有走过，则按照策略走
                map[i][j] = 2; // 假定该点可以走通
                if (setWay(map, i + 1, j)) {
                    // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 向左走
                    return true;
                } else {
                    // 说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
