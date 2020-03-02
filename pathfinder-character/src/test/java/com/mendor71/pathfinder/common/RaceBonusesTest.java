package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;
import com.mendor71.pathfinder.common.races.ElfRace;
import com.mendor71.pathfinder.common.races.IRace;
import com.mendor71.pathfinder.common.races.RaceManager;
import com.mendor71.pathfinder.common.races.Races;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class RaceBonusesTest {
    private IRace elfRace;

    @Before
    public void before() throws RaceNotExiststException {
        elfRace = RaceManager.get(Races.ELF);
    }

    @Test
    public void testBonusesNonEmpty() {
        assertFalse(elfRace.getBonuses().isEmpty());
    }
}
