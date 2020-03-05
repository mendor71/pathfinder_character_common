package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.exceptions.CharacterRaceNotSetException;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClass;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor71.pathfinder.common.pathfinderclasses.IClassManager;
import com.mendor71.pathfinder.common.pathfinderclasses.PersonifiedClassManager;
import com.mendor71.pathfinder.common.types.ClassType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CharacterClassManagerTest {
    private IClassManager classManager;

    @Before
    public void before() {
        Set<CharacterClassDetails> classDetails = new HashSet<>();

        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.RANGER), 1));
        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.FIGHTER), 2));

        classManager = new PersonifiedClassManager(UUID.randomUUID().toString());
        classManager.setClassesOnControl(classDetails);
    }

    @Test
    public void testCharacterLevel() {
        assertEquals(classManager.getCharacterSummaryLevel(), 3);
    }

    @Test
    public void testCharacterSkillPoints() {
        assertEquals(classManager.getSummaryCharacterSkillPointsProvidedByClasses(), 10);
    }

    @Test
    public void testLevelInClass() {
        assertEquals(classManager.getCharacterClassDetails(ClassType.FIGHTER).getLevel(), 2);
    }
}
