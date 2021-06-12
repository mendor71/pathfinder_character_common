package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.IAttributeManager;
import com.mendor71.pathfinder.common.attributes.PersonifiedAttributeManager;
import com.mendor71.pathfinder.common.exceptions.CharacterRaceNotSetException;
import com.mendor71.pathfinder.common.types.AttributeType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CharacterAttributeManagerTest {
    private IAttributeManager attributeManager;

    @Before
    public void createCharacter() {
        Set<CharacterAttributeDetails> characterAttributeDetails = new HashSet<>();

        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.INTELLIGENCE, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.AGILITY, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.CHARISMA, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.STRENGTH, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.WISDOM, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.ENDURANCE, 10));

        attributeManager = new PersonifiedAttributeManager(UUID.randomUUID().toString());
        attributeManager.setAttributesOnControl(characterAttributeDetails);
    }

    @Test
    public void testGetAgilityAttributeValueDefault10() {
        assertEquals(attributeManager.getAttributeValue(AttributeType.AGILITY), 10);
    }

    @Test
    public void testGetAgilityAttributeModifier() {
        assertEquals(attributeManager.getAttributeModifier(AttributeType.AGILITY), 0);
    }

    @Test
    public void testSetAgilityAttributeValue12() {
        attributeManager.setAttributeValue(AttributeType.AGILITY, 12);
        assertEquals(attributeManager.getAttributeValue(AttributeType.AGILITY), 12);
    }

    @Test
    public void testIncreaseAgilityAttributeValueBy4() {
        attributeManager.increaseAttributeValue(AttributeType.AGILITY, 4);
        assertEquals(attributeManager.getAttributeValue(AttributeType.AGILITY), 14);
    }

    @Test
    public void testDecreaseAgilityAttributeValueBy4() {
        attributeManager.decreaseAttributeValue(AttributeType.AGILITY, 4);
        assertEquals(attributeManager.getAttributeValue(AttributeType.AGILITY), 6);
    }

    @Test
    public void testCharacterChangeAttributeValue() {
        attributeManager.increaseAttributeValue(AttributeType.AGILITY, 6);

        assertEquals(attributeManager.getAttributeValue(AttributeType.AGILITY), 16);
        assertEquals(attributeManager.getAttributeModifier(AttributeType.AGILITY), 3);

        attributeManager.decreaseAttributeValue(AttributeType.AGILITY, 2);
        assertEquals(attributeManager.getAttributeModifier(AttributeType.AGILITY), 2);
    }
}
