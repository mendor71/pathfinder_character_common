package roll;

import com.mendor.pathfinder.rolls.AccuracyRoll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit4.class)
public class AccuracyRollTest {

    @Test
    public void testAccuracyRoll20Times() {

        for (int i = 0; i < 20000; i++) {
            long result = new AccuracyRoll().roll();
            assertTrue(result > 0);
            assertTrue(result < 21);
        }
    }

    @Test
    public void testChanseRoll20Times() {
        for (int i = 0; i < 200; i++) {
            float result = new AccuracyRoll().roll(5, 15);
            assertTrue(result >= 0);
        }
    }
}
