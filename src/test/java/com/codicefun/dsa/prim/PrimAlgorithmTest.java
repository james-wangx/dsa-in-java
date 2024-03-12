package com.codicefun.dsa.prim;

import com.codicefun.dsa.algorithm.prim.MGraph;
import com.codicefun.dsa.algorithm.prim.MST;
import org.junit.jupiter.api.Test;

public class PrimAlgorithmTest {
    // max means that tow points are not connected
    public final int max = 10000;

    @Test
    public void testShowGraph() {
        char[] nodes = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int num = nodes.length;
        int[][] weight = new int[][]{
                {max, 5, 7, max, max, max, 2},
                {5, max, max, 9, max, max, 3},
                {7, max, max, max, 8, max, max},
                {max, 9, max, max, max, 4, max},
                {max, max, 8, max, max, 5, 4},
                {max, max, max, 4, 5, max, 6},
                {2, 3, max, max, 4, 6, max},
        };
        MGraph graph = new MGraph(num);
        MST mst = new MST();
        mst.createGraph(graph, num, nodes, weight);
        mst.showGraph(graph);
    }

    @Test
    public void testPrim() {
        char[] nodes = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int num = nodes.length;
        int[][] weight = new int[][]{
                {max, 5, 7, max, max, max, 2},
                {5, max, max, 9, max, max, 3},
                {7, max, max, max, 8, max, max},
                {max, 9, max, max, max, 4, max},
                {max, max, 8, max, max, 5, 4},
                {max, max, max, 4, 5, max, 6},
                {2, 3, max, max, 4, 6, max},
        };
        MGraph graph = new MGraph(num);
        MST mst = new MST();
        mst.createGraph(graph, num, nodes, weight);
        mst.prim(graph, 1);
    }
}
