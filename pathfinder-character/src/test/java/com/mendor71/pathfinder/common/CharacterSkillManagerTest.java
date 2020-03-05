package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.exceptions.CharacterRaceNotSetException;
import com.mendor71.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor71.pathfinder.common.skills.ISkillManager;
import com.mendor71.pathfinder.common.skills.PersonifiedSkillManager;
import com.mendor71.pathfinder.common.skills.SimpleSkillProvider;
import com.mendor71.pathfinder.common.types.SkillType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CharacterSkillManagerTest {
    private ISkillManager skillManager;

    @Before
    public void before()  {
        skillManager = new PersonifiedSkillManager(UUID.randomUUID().toString());

        Set<CharacterSkillDetails> skillSet = new HashSet<>();
        SimpleSkillProvider skillProvider = SimpleSkillProvider.getInstance();

        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.Survival), 3));
        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.DisableDevice), 5));
        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.Heal), 2));

        Set<SkillType> classSkills = EnumSet.noneOf(SkillType.class);
        classSkills.add(SkillType.DisableDevice);

        skillManager.setSkillsOnControl(skillSet);
        skillManager.setClassSkills(classSkills);
        skillManager.setSumSkillPoints(15);
    }

    @Test
    public void testDefaultPoints() {
        assertEquals(skillManager.getFreeSkillPoints(), 5);
        assertEquals(skillManager.getUsedSkillPoints(), 10);
    }

    @Test
    public void testSumSkillValue() {
        assertEquals( skillManager.getSumarySkillValue(SkillType.DisableDevice), 8);
        assertEquals( skillManager.getSkillTrainedPoints(SkillType.DisableDevice), 5);
    }

    @Test
    public void testGetUsedSkillPointsBySkill() {
        assertEquals( skillManager.getSkillTrainedPoints(SkillType.DisableDevice), 5);
    }
}


