package com.mendor71.pathfinder.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.ICharacterAttributeManager;
import com.mendor71.pathfinder.common.attributes.SimpleCharacterAttributeManager;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClass;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor71.pathfinder.common.pathfinderclasses.ICharacterClassManager;
import com.mendor71.pathfinder.common.pathfinderclasses.SimpleCharacterClassManager;
import com.mendor71.pathfinder.common.races.HumanRace;
import com.mendor71.pathfinder.common.skills.ICharacterSkillManager;
import com.mendor71.pathfinder.common.skills.SimpleCharacterSkillManager;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.util.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SerializationTest {
    private static PathfinderCharacter testCharacter;

    @BeforeClass
    public static void prepareUtils() {
        ICharacterClassManager characterClassManager = new SimpleCharacterClassManager();
        ICharacterAttributeManager characterAttributeManager = new SimpleCharacterAttributeManager();
        ICharacterSkillManager characterSkillManager = new SimpleCharacterSkillManager();

        Set<CharacterClassDetails> classDetails = new HashSet<>();
        Set<CharacterAttributeDetails> attributeDetails = new HashSet<>();

        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.FIGHTER), 4));
        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.RANGER), 3));

        attributeDetails.add(new CharacterAttributeDetails(AttributeType.ENDURANCE, 14));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.STRENGTH, 16));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.AGILITY, 12));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.WISDOM, 10));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.INTELLIGENCE, 10));
        attributeDetails.add(new CharacterAttributeDetails(AttributeType.CHARISMA, 12));

        CharacterFactory characterFactory = new CharacterFactory(characterClassManager, characterAttributeManager, characterSkillManager);

        testCharacter = characterFactory.newCharacter(new HumanRace(), classDetails, attributeDetails);

        testCharacter.setName("Vadgast");
        testCharacter.setAge(34);
        testCharacter.setHeight(182);
        testCharacter.setWeight(80);
        testCharacter.setEyeColor(Color.GRAY);
        testCharacter.setHairColor(Color.BLACK);
    }

    @Test
    public void testDecoratorBasedSerializerAndDeserializer() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        IJSONSerializer sBase = new CustomCharacterJSONSerializer();
        IJSONSerializer sWithClasses = new CustomCharacterJSONSerializerWithClasses(sBase);
        IJSONSerializer sWithAttributes = new CustomCharacterJSONSerializerWithAttributes(sWithClasses);
        IJSONSerializer sWithSkills = new CustomCharacterJSONSerializerWithSkills(sWithAttributes);

        String serializedData = mapper.writeValueAsString(sWithSkills.serialize(testCharacter));

        CustomCharacterJSONDeserializer dBase = new CustomCharacterJSONDeserializer(PathfinderCharacter.class);
        CustomCharacterJSONDeserializer dWithClasses = new CustomCharacterJSONDeserializerWithClasses(dBase);
        CustomCharacterJSONDeserializer dWithAttributes = new CustomCharacterJSONDeserializerWihAttributes(dWithClasses);
        CustomCharacterJSONDeserializer dWithSkills = new CustomCharacterJSONDeserializerWithSkills(dWithAttributes);

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(PathfinderCharacter.class, dWithSkills);
        objectMapper.registerModule(module);

        PathfinderCharacter deserializedCharacter = objectMapper.readValue(serializedData, PathfinderCharacter.class);

        assertEquals(testCharacter.getName(), deserializedCharacter.getName());
        assertEquals(testCharacter.getUUID(), deserializedCharacter.getUUID());
        assertEquals(testCharacter.getLevel(), deserializedCharacter.getLevel());
        assertEquals(testCharacter.getCharacterClasses().size(), deserializedCharacter.getCharacterClasses().size());
        assertEquals(testCharacter.getArmorClass(), deserializedCharacter.getArmorClass());
        assertEquals(testCharacter.getFreeSkillPoints(), deserializedCharacter.getFreeSkillPoints());
        assertEquals(testCharacter.getAttributeModifier(AttributeType.ENDURANCE), deserializedCharacter.getAttributeModifier(AttributeType.ENDURANCE));
        assertEquals(testCharacter.getSkillPoints(SkillType.Survival), deserializedCharacter.getSkillPoints(SkillType.Survival));
    }
}
