package com.codicefun.dsa.graph;

import com.codicefun.dsa.datastructure.graph.Graph;
import org.junit.jupiter.api.Test;

class GraphTest {

    final Graph graph = new Graph(10);

    @Test
    void dfsTest() {
        graph.dfs();
    }

}
