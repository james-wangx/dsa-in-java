package com.codicefun.dsa.algorithm.floyd;

import java.util.Arrays;

public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; // 表示不可连接
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

class Graph {

    private final char[] vertex; // 存放顶点的数组
    private final int[][] dis; // 保存从各个顶点出发到其它顶点的距离，最后的结果也是保存在该数组中
    private final int[][] pre; // 保存到目标顶点的前驱顶点

    /**
     * 构造方法
     *
     * @param length 长度
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对 pre 数组初始化，存放的是前驱顶点的下标，而不是 A，B，C...
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 显示 pre 数组和 dis 数组
     */
    public void show() {
        System.out.println("dis 数组：");
        showHead();

        for (int i = 0; i < dis.length; i++) {
            System.out.print(vertex[i] + " ");
            for (int j = 0; j < dis[i].length; j++) {
                int len = dis[i][j];
                if (len == 65535) {
                    System.out.print("N  ");
                } else {
                    System.out.printf("%2d ", len);
                }
            }
            System.out.println();
        }

        System.out.println("\npre 数组：");
        showHead();

        for (int i = 0; i < pre.length; i++) {
            System.out.print(vertex[i] + "  ");
            for (int j = 0; j < pre[i].length; j++) {
                System.out.print(pre[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void showHead() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.print("   ");
        for (char v : vertex) {
            System.out.print(v + "  ");
        }
        System.out.println();
    }

    public void floyd() {
        int len; // 距离

        // 对中间顶点的遍历
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}
