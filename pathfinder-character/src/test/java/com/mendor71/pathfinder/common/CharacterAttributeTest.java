package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.types.AttributeType;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class CharacterAttributeTest {
    private CharacterAttributeDetails characterAttribute;

    @Before
    public void before() {
        characterAttribute = new CharacterAttributeDetails(AttributeType.STRENGTH, 10);
    }

    @Test
    public void testValueNormalDefault() {
        assertEquals(characterAttribute.getValue(), 10);
    }

    @Test
    public void testSetValue() {
        characterAttribute.setValue(16);
        assertEquals(characterAttribute.getValue(), 16);
    }

    @Test
    public void testIncreaseValue() {
        characterAttribute.increaseValue(10);
        assertEquals(characterAttribute.getValue(), 20);
    }

    @Test
    public void testDecreaseValue() {
        characterAttribute.decreaseValue(2);
        assertEquals(characterAttribute.getValue(), 8);
    }

    @Test
    public void testIncreaseTempValueBonus() {
        characterAttribute.setTempValueBonus(5);
        assertEquals(characterAttribute.getValue(), 15);

        characterAttribute.increaseTempValueBonus(5);
        assertEquals(characterAttribute.getValue(), 20);

        assertEquals(characterAttribute.getModifier(), 5);

        characterAttribute.resetTempValueBonus();
        assertEquals(characterAttribute.getValue(), 10);
    }

    @Test
    public void testDecreaseTempValueBonus() {
        characterAttribute.setTempValueBonus(10);
        assertEquals(characterAttribute.getValue(), 20);

        characterAttribute.decreaseTempValueBonus(5);
        assertEquals(characterAttribute.getValue(), 15);

        assertEquals(characterAttribute.getModifier(), 2);

        characterAttribute.resetTempValueBonus();
        assertEquals(characterAttribute.getValue(), 10);
    }

    @Test
    public void testModifierUpdateIsCorrect() {
        characterAttribute.increaseValue(10);
        assertEquals(characterAttribute.getModifier(), 5);
    }

    @Test
    public void testIncreaseTempModifiedBonus() {
        characterAttribute.setTempModifierBonus(10);
        assertEquals(characterAttribute.getModifier(), 10);

        characterAttribute.increaseTempModifierBonus(2);
        assertEquals(characterAttribute.getModifier(), 12);
    }

    @Test
    public void testDecreaseTempModifierBonus() {
        characterAttribute.setTempModifierBonus(10);
        assertEquals(characterAttribute.getModifier(), 10);

        characterAttribute.decreaseTempModifierBonus(2);
        assertEquals(characterAttribute.getModifier(), 8);
    }

}
