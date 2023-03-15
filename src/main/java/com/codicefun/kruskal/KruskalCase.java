package com.codicefun.kruskal;

public class KruskalCase {
    private int edgeNum; // 边的个数
    private char[] vertexes; // 顶点数组
    private int[][] matrix; // 邻接矩阵
    // 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

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

    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%10d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i; j++) {
                if (edges[j].weight > edges[i].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[i];
                    edges[i] = tmp;
                }
            }
        }
    }

    private int getPosition(char vertex) {
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
}
