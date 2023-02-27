import com.codicefun.dynamic.KnapsackProblem;
import org.junit.Test;

public class TestKnapsackProblem {
    int[] weight = {1, 4, 3};  // 物品的重量
    int[] value = {1500, 3000, 2000}; // 物品的价值
    int capacity = 4; // 背包的容量

    @Test
    public void testMySolve() {
        int res = KnapsackProblem.mySolve(weight, value, capacity);
        assert res == 3500;
    }

    @Test
    public void testSolve() {
        int res = KnapsackProblem.solve(weight, value, capacity);
        assert res == 3500;
    }
}
