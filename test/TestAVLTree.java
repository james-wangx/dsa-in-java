import com.codicefun.avl.AVLTree;
import org.junit.Test;

public class TestAVLTree {
    public static final int[] arr = {4, 3, 6, 5, 7, 8};
    public static final AVLTree tree = new AVLTree();

    @Test
    public void testHeight() {
        tree.createTree(arr);
        assert tree.height() == 4;
        assert tree.leftHeight() == 1;
        assert tree.rightHeight() == 3;
    }
}
