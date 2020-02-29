package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.races.ElfRace;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class RaceBonusesTest {
    private ElfRace elfRace;

    @Before
    public void before() {
        elfRace = new ElfRace();
    }

    @Test
    public void testBonusesNonEmpty() {
        assertFalse(elfRace.getBonuses().isEmpty());
    }
}
