import com.mendor.util.Tuple2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class Tuple2Test {
    @Test
    public void testTupple2() {

        Tuple2<Long, Boolean> t2 = new Tuple2<Long, Boolean>(1L, false);
        assertTrue(!t2.getValue2());
    }
}
