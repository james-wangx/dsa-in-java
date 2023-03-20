package com.codicefun.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; // 表示不可连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);

        graph.showGraph();

        graph.dijkstra(6);
        graph.show();
    }

}

class Graph {

    private final char[] vertex; // 顶点数组
    private final int[][] matrix; // 邻接矩阵
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉（Dijkstra）算法实现
     *
     * @param index 出发顶点的下标
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index); // 更新 index 顶点到周围顶点的距离

        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();
            update(index);
        }
    }

    /**
     * 更新 dis 和 preVisited
     *
     * @param index 顶点下标
     */
    private void update(int index) {
        // 出发顶点到 index 顶点的距离 + 从 index 顶点到 j 顶点的距离
        int len;

        for (int j = 0; j < matrix[index].length; j++) {
            len = vv.getDis(index) + matrix[index][j];
            // 如果 j 顶点没有被访问过，并且 len 小于出发顶点到 j 顶点的距离，就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);
                vv.updateDis(j, len);
            }
        }
    }

    public void show() {
        vv.show();
    }
}

class VisitedVertex {

    // 记录各个顶点是否访问过
    // 1 表示访问过，0 表示未访问，会动态更新
    public int[] alreadyArr;

    // 记录前驱节点的下标，会动态更新
    public int[] preVisited;

    // 记录出发顶点到其他所有顶点的距离
    // 比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求得最短距离就会放到 dis
    public int[] dis;

    /**
     * 构造方法
     *
     * @param length 顶点的个数
     * @param index  出发顶点的下标
     */
    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis, 65535);
        this.alreadyArr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * 判断 index 下标的顶点是否被访问过
     *
     * @param index 顶点的下标
     * @return 访问过返回 true，否则为 false
     */
    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到 index 下标顶点的距离
     *
     * @param index 顶点下标
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新 pre 顶点的前驱节点为 index 顶点
     *
     * @param pre   顶点下标
     * @param index 顶点下标
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 获取出发顶点到 index 顶点的距离
     *
     * @param index 目的顶点
     * @return 出发顶点到 index 顶点的距离
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如这里的 G 完后，就是 A 作为新的访问顶点
     *
     * @return 顶点下标
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }

        alreadyArr[index] = 1;

        return index;
    }

    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.println("========================");
        System.out.println("出发顶点为 G：");
        System.out.println(Arrays.toString(alreadyArr));
        System.out.println(Arrays.toString(preVisited));

        for (int i = 0; i < dis.length; i++) {
            if (dis[i] != 65535) {
                System.out.printf("%c: %d\n", vertex[i], dis[i]);
            } else {
                System.out.printf("%c: N", vertex[i]);
            }
        }
    }
}
