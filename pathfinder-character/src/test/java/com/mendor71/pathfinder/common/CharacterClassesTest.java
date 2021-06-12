package com.mendor71.pathfinder.common;


import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClass;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor71.pathfinder.common.types.ClassType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CharacterClassesTest {
    private CharacterClassDetails characterClassDetails;

    @Before
    public void before() {
        characterClassDetails = new CharacterClassDetails(CharacterClass.getInstance(ClassType.RANGER), 1);
    }

    @Test
    public void testClassName() {
        assertEquals(characterClassDetails.getClassName(), ClassType.RANGER.toString());
    }

    @Test
    public void testClassLevel() {
        assertEquals(characterClassDetails.getLevel(), 1);
    }

    @Test
    public void testHitPointsValues() {
        assertEquals( characterClassDetails.getCharacterClass().getMinHitDiceValue(), 1);
        assertEquals( characterClassDetails.getCharacterClass().getMaxHitDiceValue(), 10);
    }

    @Test
    public void testSkillPoints() {
        assertEquals(characterClassDetails.getCharacterClass().getSkillPointsByLevel(), 6);
    }

    @Test
    public void testSkillsCount() {
        assertEquals(characterClassDetails.getCharacterClass().getClassSkills().size(), 15);
    }
}
