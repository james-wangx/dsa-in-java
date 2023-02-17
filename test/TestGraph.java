import com.codicefun.graph.Graph;
import org.junit.Test;

public class TestGraph {
    int n = 5;
    String[] vertexValue = {"A", "B", "C", "D", "E"};
    Graph graph = new Graph(n);

    @Test
    public void testInsert() {
        for (String str : vertexValue) {
            graph.insertVertex(str);
        }

        // 添加边
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();
    }

    @Test
    public void testDfs() {
        for (String str : vertexValue) {
            graph.insertVertex(str);
        }

        // 添加边
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.dfs();
    }
}
