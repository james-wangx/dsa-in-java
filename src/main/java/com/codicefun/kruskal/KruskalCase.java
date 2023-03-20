package com.codicefun.kruskal;

import java.util.Arrays;

public class KruskalCase {
    // 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;
    private final char[] vertexes; // 顶点数组
    private final int[][] matrix; // 邻接矩阵
    private int edgeNum; // 边的个数

    public KruskalCase(char[] vertexes, int[][] matrix) {
        int verLen = vertexes.length;
        this.vertexes = new char[verLen];
        System.arraycopy(vertexes, 0, this.vertexes, 0, verLen);
        this.matrix = new int[verLen][verLen];
        for (int i = 0; i < verLen; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, verLen);
        }

        // 统计边
        for (int i = 0; i < verLen; i++) {
            for (int j = i + 1; j < verLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0; // 表示最后结果数组的索引
        int[] ends = new int[edgeNum]; // 用于保存 "已有 MST" 中每个顶点在 MST 的终点
        // 创建结果数组，保存最后的 MST
        EData[] res = new EData[edgeNum];
        // 获取图中所有边的集合
        EData[] edges = getEdges();
        sortEdges(edges); // 按权值升序排序

        // 遍历 edges 数组，将边添加到 MST 中时，判断是否形成回路，如果没有就加入 res，否则抛弃
        for (int i = 0; i < edgeNum; i++) {
            // 获取该边的两个顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            // 获取 p1 在 MST 中的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            // 判断是否构成回路
            if (m != n) {
                ends[m] = n; // 设置 m 在 "已有 MST" 的终点
                res[index++] = edges[i];
            }
        }

        // 统计并打印 MST
        System.out.println("最小生成树为：");
        System.out.println(Arrays.toString(res));
    }

    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 按权值对边进行升序排序
     */
    public void sortEdges(EData[] edges) {
        EData tmp;
        boolean exchange;

        for (int i = 0; i < edges.length - 1; i++) {
            exchange = false;
            for (int j = 0; j < edges.length -1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                    exchange = true;
                }
            }
            if (!exchange) {
                break;
            }
        }
    }

    /**
     * 获取顶点所在数组的下标
     *
     * @param vertex 顶点
     * @return 下标，不存在为 -1
     */
    public int getPosition(char vertex) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == vertex) {
                return i;
            }
        }

        return -1;
    }

    public EData[] getEdges() {
        EData[] edges = new EData[edgeNum];

        int index = 0;
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }

        return edges;
    }

    /**
     * 获取下标为 i 的顶点的终点
     * @param ends 记录了各个顶点的终点是哪个，是在遍历过程中动态生成的
     * @param i 表示传入顶点的下标
     * @return 下标为 i 的顶点的终点的下标
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }

        return i;
    }
}
