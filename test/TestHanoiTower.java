import com.codicefun.dac.HanoiTower;
import org.junit.Test;

public class TestHanoiTower {

    @Test
    public void testMove() {
        HanoiTower.move(1, 'A', 'B', 'C');
        System.out.println();
        HanoiTower.move(2, 'A', 'B', 'C');
        System.out.println();
        HanoiTower.move(3, 'A', 'B', 'C');
        System.out.println();
        HanoiTower.move(4, 'A', 'B', 'C');
    }
}
