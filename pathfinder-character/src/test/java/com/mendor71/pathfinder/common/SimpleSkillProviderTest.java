package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.skills.Skill;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.skills.SimpleSkillProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class SimpleSkillProviderTest {
    @Test
    public void testSkillFactoryReturnCorrectSkillInstance() {
        SimpleSkillProvider simpleSkillProvider = SimpleSkillProvider.getInstance();

        Skill skill = simpleSkillProvider.getSkillByType(SkillType.Survival);

        assertTrue(skill.useUntrained());
        assertEquals(skill.getAttributeType(), AttributeType.WISDOM);
    }
}
