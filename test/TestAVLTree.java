import com.codicefun.avl.AVLTree;
import org.junit.Test;

public class TestAVLTree {
    public static final int[] arr = {4, 3, 6, 5, 7, 8};
    public static final AVLTree tree = new AVLTree();

    @Test
    public void testHeight() {
        tree.createTree(arr);
        assert tree.height() == 3;
        assert tree.leftHeight() == 2;
        assert tree.rightHeight() == 2;
    }

    @Test
    public void testAdd() {
        tree.createTree(arr);
        assert tree.height() == 3;
        assert tree.leftHeight() == 2;
        assert tree.rightHeight() == 2;

        tree.createTree(new int[]{10, 12, 8, 9, 7, 6});
        assert tree.height() == 3;
        assert tree.leftHeight() == 2;
        assert tree.rightHeight() == 2;
    }
}
