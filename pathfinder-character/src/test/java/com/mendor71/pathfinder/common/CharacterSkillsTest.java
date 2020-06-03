package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.attributes.CharacterAttribute;
import com.mendor71.pathfinder.common.skills.CharacterSkill;
import com.mendor71.pathfinder.common.skills.SimpleSkillProvider;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.SkillType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CharacterSkillsTest {
    private CharacterSkill skillDetails;
    private CharacterAttribute attributeDetails;

    @Before
    public void before() {
        skillDetails = new CharacterSkill(SimpleSkillProvider.getInstance().getSkillByType(SkillType.Survival));
        attributeDetails = new CharacterAttribute(AttributeType.WISDOM, 10);
        attributeDetails.addListener(skillDetails);
    }

    @Test
    public void testDefaultValue() {
        assertEquals(skillDetails.getTrainedPoints(), 0);
    }

    @Test
    public void testUpdateModifierByAttributeValue() {
        attributeDetails.increaseTempValueBonus(10);

        assertEquals(skillDetails.getAttributeModifier(), 5);
    }

    @Test
    public void testGetSumSkillValue() {
        attributeDetails.increaseTempValueBonus(10);
        skillDetails.setStableBonus(2);
        skillDetails.increaseTrainedPoints(3, true);

        assertEquals(skillDetails.getSummaryValue(), 13);
        assertEquals(skillDetails.getTrainedPoints(), 3);
    }
}
