package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor71.pathfinder.common.skills.SimpleSkillProvider;
import com.mendor71.pathfinder.common.types.SkillType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CharacterSkillDetailsTest {
    private CharacterSkillDetails details = new CharacterSkillDetails(SimpleSkillProvider.getInstance().getSkillByType(SkillType.Acrobatics), 5);

    /*private long id;

    ;
    private PathfinderCharacter character;

    private long trainedPoints;
    private long modifier;
    private long bonus;

    private long temporarySkillValueModifier = 0;*/


    @Test
    public void testIncreaseTrainedPoints() {
        assertEquals(details.increaseTrainedPoints(2, false), 7);
        assertEquals(details.increaseTrainedPoints(2, true), 9);
    }

    @Test
    public void testIncreaseStableBonus() {
        assertEquals( details.increaseStableBonus(1), 1);
    }

    @Test
    public void testDecreaseStableBonus() {
        assertEquals( details.decreaseStableBonus(1), -1 );
    }

    @Test
    public void testIncreaseTemporarySkillModifier() {
        assertEquals( details.increaseTemporarySkillModifier(1), 1 );
    }

    @Test
    public void testDecreaseTemporarySkillModifier() {
        assertEquals( details.decreaseTemporarySkillModifier(1l), -1 );
    }

    @Test
    public void testResetTemporarySkillModifier() {
        details.resetTemporarySkillModifier();
        assertEquals( details.getTemporarySkillValueModifier() , 0 );
    }

    @Test
    public void testGetSummaryValue() {
        assertEquals( details.getSummaryValue(),  5 );
    }

    @Test
    public void complexChangesTest() {
        details.increaseTrainedPoints(1, true); //6
        details.increaseStableBonus(1); //7
        details.decreaseTemporarySkillModifier(5); //2
        details.update(5, 3); //5

        assertEquals(details.getSummaryValue(), 5);
    }
}