import com.codicefun.binarysorttree.BinarySortTree;
import com.codicefun.binarysorttree.Node;
import org.junit.Test;

public class TestBinarySortTree {
    public static final int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
    public static final BinarySortTree tree = new BinarySortTree();

    @Test
    public void testAdd() {
        for (int item : arr) {
            tree.add(new Node(item));
        }

        String order = tree.infixOrder();
        assert order.equals("1 2 3 5 7 9 10 12 ");
    }
}
