package com.codicefun.dsa.datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    private final ArrayList<String> vertexList; // 存储顶点集合
    private final int[][] edges; // 存储图对应的邻接矩阵
    private int numOfEdges; // 表示边的数目
    private boolean[] isVisited; // 记录哪个节点被访问

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
        isVisited = new boolean[n];
    }

    /**
     * 得到第一个邻接节点的下标
     *
     * @param index 当前节点的下标
     * @return 如果存在就返回下一个邻接节点的下标，否则返回 -1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 根据前一个邻接节点和当前节点的下标来获取下一个邻接节点
     *
     * @param v1 前一个邻接节点
     * @param v2 当前节点
     * @return 如果存在就返回下一个邻接节点的下标，否则返回 -1
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 深度优先遍历算法
     *
     * @param i 当前节点索引
     */
    public void dfs(int i) {
        // 首先我们访问该节点（输出）
        System.out.print(getValueByIndex(i) + "->");
        // 将该节点设置为已经访问过
        isVisited[i] = true;
        // 查找 i 的第一个邻接节点
        int w = getFirstNeighbor(i);
        // 如果 w 存在
        while (w != -1) {
            // 如果 w 未被访问
            if (!isVisited[w]) {
                // 从 w 开始 dfs
                dfs(w);
            } else {
                // 如果 w 已经被访问过
                w = getNextNeighbor(i, w);
            }
        }
    }

    /**
     * 对 dfs 进行一个重载，遍历所有节点，并进行 dfs
     */
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }

    // 对一个节点进行广度优先遍历
    private void bfs(int i) {
        int u; // 表示队列头结点对应的下标
        int w; // 邻接节点下标
        // 队列，记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问节点，输出信息
        System.out.print(getValueByIndex(i) + "->");
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列头节点下标
            u = queue.removeFirst();
            // 得到第一个邻接节点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                } else {
                    w = getNextNeighbor(u, w); // 体现出广度优先
                }
            }
        }
    }

    // 遍历所有节点，都进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(i);
            }
        }
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
