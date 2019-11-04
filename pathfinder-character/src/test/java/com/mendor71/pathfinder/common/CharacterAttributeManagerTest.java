package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.IAttributeManager;
import com.mendor71.pathfinder.common.attributes.PersonifiedCharacterAttributeManager;
import com.mendor71.pathfinder.common.races.HumanRace;
import com.mendor71.pathfinder.common.types.AttributeType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CharacterAttributeManagerTest {
    private Character character;

    @Before
    public void createCharacter() {
        CharacterBase characterBase = CharacterBase.newBuilder()
                .setAge(35)
                .setWeight(80)
                .setHeight(180)
                .setRace(new HumanRace())
                .setName("Victorian")
                .setLevel(1)
                .setHitPointsMax(100)
                .setHitPointsCurrent(100)
                .setHairColor(Color.BLACK)
                .setEyeColor(Color.BLUE)
                .setArmorClass(100)
                .build();

        Set<CharacterAttributeDetails> characterAttributeDetails = new HashSet<>();

        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.INTELLIGENCE, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.AGILITY, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.CHARISMA, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.STRENGTH, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.WISDOM, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.ENDURANCE, 10));

        IAttributeManager attributeManager = new PersonifiedCharacterAttributeManager(characterBase.getUuid());

        character = Character.newBuilder()
                .setCharacterBase(characterBase)
                .manageAttributes(attributeManager, characterAttributeDetails)
                .build();
    }

    @Test
    public void testGetAgilityAttributeValueDefault10() {
        assertEquals(character.getAttributeValue(AttributeType.AGILITY), 10);
    }

    @Test
    public void testGetAgilityAttributeModifier() {
        assertEquals(character.getAttributeModifier(AttributeType.AGILITY), 0);
    }

    @Test
    public void testSetAgilityAttributeValue12() {
        character.setAttributeValue(AttributeType.AGILITY, 12);
        assertEquals(character.getAttributeValue(AttributeType.AGILITY), 12);
    }

    @Test
    public void testIncreaseAgilityAttributeValueBy4() {
        character.increaseAttributeValue(AttributeType.AGILITY, 4);
        assertEquals(character.getAttributeValue(AttributeType.AGILITY), 14);
    }

    @Test
    public void testDecreaseAgilityAttributeValueBy4() {
        character.decreaseAttributeValue(AttributeType.AGILITY, 4);
        assertEquals(character.getAttributeValue(AttributeType.AGILITY), 6);
    }

    @Test
    public void testCharacterChangeAttributeValue() {
        character.increaseAttributeValue(AttributeType.AGILITY, 6);

        assertEquals(character.getAttributeValue(AttributeType.AGILITY), 16);
        assertEquals(character.getAttributeModifier(AttributeType.AGILITY), 3);

        character.decreaseAttributeValue(AttributeType.AGILITY, 2);
        assertEquals(character.getAttributeModifier(AttributeType.AGILITY), 2);
    }
}
