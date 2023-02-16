package com.codicefun.graph;

import java.util.ArrayList;

public class Graph {
    private final ArrayList<String> vertexList; // 存储顶点集合
    private final int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges; // 表示边的数目

    /**
     * 构造器
     *
     * @param n 顶点个数
     */
    public Graph(int n) {
        // 初始化矩阵和 vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    /**
     * 返回顶点的个数
     *
     * @return 顶点个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的个数
     *
     * @return 边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回指定索引的节点
     *
     * @param i 索引
     * @return 节点
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 获取 v1 和 v2 的权值
     *
     * @param v1 节点1
     * @param v2 节点2
     * @return 权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] line : edges) {
            for (int item : line) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    /**
     * 插入节点
     *
     * @param vertex 顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     第一个顶点对应的下标
     * @param v2     第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
