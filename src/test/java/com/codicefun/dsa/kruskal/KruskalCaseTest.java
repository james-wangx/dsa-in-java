package com.codicefun.dsa.kruskal;

import java.util.Arrays;

import com.codicefun.dsa.kruskal.EData;
import com.codicefun.dsa.kruskal.KruskalCase;
import org.junit.jupiter.api.Test;

public class KruskalCaseTest {

    private static final int INF = Integer.MAX_VALUE;
    private final char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private final int[][] matrix = {
            {0, 12, INF, INF, INF, 16, 14},
            {12, 0, 10, INF, INF, 7, INF},
            {INF, 10, 0, 3, 5, 6, INF},
            {INF, INF, 3, 0, 4, INF, INF},
            {INF, INF, 5, 4, 0, 2, 8},
            {16, 7, 6, INF, 2, 0, 9},
            {14, INF, INF, INF, 8, 9, 0}};
    private final KruskalCase kruskal = new KruskalCase(vertexes, matrix);

    @Test
    public void test() {
        kruskal.print();
        EData[] edges = kruskal.getEdges();
        System.out.println(Arrays.toString(edges));

        kruskal.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
    }

    @Test
    public void testKruskal() {
        kruskal.kruskal();
    }
}
