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

    @Test
    public void testDel() {
        // 叶子节点
        tree.createTree(arr);
        tree.del(2);
        assert tree.infixOrder().equals("1 3 5 7 9 10 12 ");
        tree.del(12);
        assert tree.infixOrder().equals("1 3 5 7 9 10 ");

        // 仅有一颗子树的节点
        tree.del(10);
        assert tree.infixOrder().equals("1 3 5 7 9 ");
        tree.createTree(arr);
        tree.del(1);
        assert tree.infixOrder().equals("2 3 5 7 9 10 12 ");
        tree.createTree(new int[]{2, 4, 3, 5});
        tree.del(2);
        assert tree.infixOrder().equals("3 4 5 ");
        tree.createTree(new int[]{5, 3, 2, 4});
        tree.del(5);
        assert tree.infixOrder().equals("2 3 4 ");

        // 有两颗子树的节点
        tree.createTree(arr);
        tree.del(10);
        assert tree.infixOrder().equals("1 2 3 5 7 9 12 ");
        tree.del(3);
        assert tree.infixOrder().equals("1 2 5 7 9 12 ");
        tree.del(7);
        assert tree.infixOrder().equals("1 2 5 9 12 ");
    }
}
