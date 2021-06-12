package com.mendor71.pathfinder.utils.rolls;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AccuracyRollTest {

    @Test
    public void testAccuracyRoll20Times() {

        for (int i = 0; i < 20000; i++) {

            long result = new AccuracyRoll().roll();
            TestCase.assertTrue(result > 0);
            TestCase.assertTrue(result < 21);
        }
    }

    @Test
    public void testChanseRoll20Times() {
        for (int i = 0; i < 200; i++) {
            float result = new AccuracyRoll().roll(5, 15);
            TestCase.assertTrue(result >= 0);
        }
    }
}
