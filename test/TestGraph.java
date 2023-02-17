import com.codicefun.graph.Graph;
import org.junit.Test;

public class TestGraph {
    int n = 8;
    String[] vertexValue = {"1", "2", "3", "4", "5", "6", "7", "8"};
    Graph graph = new Graph(n);

    {
        for (String str : vertexValue) {
            graph.insertVertex(str);
        }

        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
    }

    @Test
    public void testInsert() {
        graph.showGraph();
    }

    @Test
    public void testDfs() {
        graph.dfs();
    }

    @Test
    public void testBfs() {
        graph.bfs();
    }
}
