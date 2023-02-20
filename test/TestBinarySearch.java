import com.codicefun.binarysearchnorecursion.BinarySearchNoRecursion;
import org.junit.Test;

public class TestBinarySearch {

    int[] arr = {1, 3, 8, 10, 11, 67, 100};

    @Test
    public void testBinarySearchNoRecursion() {
        assert BinarySearchNoRecursion.binarySearch(arr, 1) == 0;
        assert BinarySearchNoRecursion.binarySearch(arr, 3) == 1;
        assert BinarySearchNoRecursion.binarySearch(arr, 8) == 2;
        assert BinarySearchNoRecursion.binarySearch(arr, 10) == 3;
        assert BinarySearchNoRecursion.binarySearch(arr, 11) == 4;
        assert BinarySearchNoRecursion.binarySearch(arr, 67) == 5;
        assert BinarySearchNoRecursion.binarySearch(arr, 100) == 6;
        assert BinarySearchNoRecursion.binarySearch(arr, 50) == -1;
    }
}
