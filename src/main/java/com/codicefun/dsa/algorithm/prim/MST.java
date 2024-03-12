package com.codicefun.dsa.algorithm.prim;

import java.util.Arrays;

public class MST {
    public void createGraph(MGraph graph, int num, char[] nodes, int[][] weight) {
        for (int i = 0; i < num; i++) {
            graph.nodes[i] = nodes[i];
            System.arraycopy(weight[i], 0, graph.weight[i], 0, num);
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] line : graph.weight) {
            System.out.println(Arrays.toString(line));
        }
    }

    public void prim(MGraph graph, int v) {
        // Mark whether the node is visited, default is 0.
        int[] visited = new int[graph.num];

        visited[v] = 1; // visited
        // Record the index of two nodes
        int h1 = -1;
        int h2 = -1;

        for (int k = 1; k < graph.num; k++) {
            int miniWeight = 10000;
            for (int i = 0; i < graph.num; i++) {
                for (int j = 0; j < graph.num; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < miniWeight) {
                        miniWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.printf("edge<%c, %c> weight:%d\n", graph.nodes[h1], graph.nodes[h2], graph.weight[h1][h2]);
            visited[h2] = 1;
        }
    }
}
