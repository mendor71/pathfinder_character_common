package com.mendor71.pathfinder.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.IAttributeManager;
import com.mendor71.pathfinder.common.attributes.PersonifiedAttributeManager;
import com.mendor71.pathfinder.common.exceptions.CharacterRaceNotSetException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillAlreadyExistsException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillListIllegalStateException;
import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClass;
import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
import com.mendor71.pathfinder.common.pathfinderclasses.IClassManager;
import com.mendor71.pathfinder.common.pathfinderclasses.PersonifiedClassManager;
import com.mendor71.pathfinder.common.races.ElfRace;
import com.mendor71.pathfinder.common.races.RaceManager;
import com.mendor71.pathfinder.common.races.Races;
import com.mendor71.pathfinder.common.skills.CharacterSkillDetails;
import com.mendor71.pathfinder.common.skills.ISkillManager;
import com.mendor71.pathfinder.common.skills.PersonifiedSkillManager;
import com.mendor71.pathfinder.common.skills.SimpleSkillProvider;
import com.mendor71.pathfinder.common.types.AttributeType;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.util.*;
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
    private static Character testCharacter;

    @BeforeClass
    public static void prepareUtils() throws CharacterSkillListIllegalStateException, CharacterSkillAlreadyExistsException, CharacterRaceNotSetException, RaceNotExiststException {
        CharacterBase characterBase = CharacterBase
            .newBuilder()
            .setName("Vadgast")
            .setAge(34)
            .setHeight(182)
            .setWeight(80)
            .setEyeColor(Color.GRAY)
            .setHairColor(Color.BLACK)
            .setRace(RaceManager.get(Races.ELF)).build();

        IClassManager classManager = new PersonifiedClassManager(characterBase.getUuid());
        IAttributeManager attributeManager = new PersonifiedAttributeManager(characterBase.getUuid());
        ISkillManager skillManager = new PersonifiedSkillManager(characterBase.getUuid());

        Set<CharacterAttributeDetails> characterAttributeDetails = new HashSet<>();
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.ENDURANCE, 14));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.STRENGTH, 16));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.AGILITY, 12));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.WISDOM, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.INTELLIGENCE, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.CHARISMA, 12));

        Set<CharacterClassDetails> characterClassDetails = new HashSet<>();
        characterClassDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.FIGHTER), 4));
        characterClassDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.RANGER), 3));

        Set<CharacterSkillDetails> skillSet = new HashSet<>();
        SimpleSkillProvider skillProvider = SimpleSkillProvider.getInstance();

        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.Survival)));
        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.DisableDevice)));
        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.Heal)));

        testCharacter = Character.newBuilder()
            .manageAttributes(attributeManager, characterAttributeDetails)
            .manageClasses(classManager, characterClassDetails)
            .manageSkills(skillManager, skillSet)
            .setCharacterBase(characterBase)
            .build();
    }

    @Test
    public void testDecoratorBasedSerializerAndDeserializer() throws IOException, CharacterSkillListIllegalStateException, CharacterSkillAlreadyExistsException, RaceNotExiststException {
        ObjectMapper mapper = new ObjectMapper();

        IJSONSerializer sBase = new CustomCharacterJSONSerializer();
        IJSONSerializer sWithClasses = new CustomCharacterJSONSerializerWithClasses(sBase);
        IJSONSerializer sWithAttributes = new CustomCharacterJSONSerializerWithAttributes(sWithClasses);
        IJSONSerializer sWithSkills = new CustomCharacterJSONSerializerWithSkills(sWithAttributes);

        String serializedData = mapper.writeValueAsString(sWithSkills.serialize(testCharacter));

        CustomCharacterJSONDeserializer dBase = new CustomCharacterJSONDeserializer(Character.Builder.class);
        CustomCharacterJSONDeserializer dWithClasses = new CustomCharacterJSONDeserializerWithClasses(dBase);
        CustomCharacterJSONDeserializer dWithAttributes = new CustomCharacterJSONDeserializerWihAttributes(dWithClasses);
        CustomCharacterJSONDeserializer dWithSkills = new CustomCharacterJSONDeserializerWithSkills(dWithAttributes);

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Character.Builder.class, dWithSkills);
        objectMapper.registerModule(module);

        Character.Builder builder = objectMapper.readValue(serializedData, Character.Builder.class);
        Character deserializedCharacter = builder.build();

        assertEquals(testCharacter.getName(), deserializedCharacter.getName());
        assertEquals(testCharacter.getUuid(), deserializedCharacter.getUuid());
        assertEquals(testCharacter.getLevel(), deserializedCharacter.getLevel());
        assertEquals(testCharacter.getCharacterClasses().size(), deserializedCharacter.getCharacterClasses().size());
        assertEquals(testCharacter.getArmorClass(), deserializedCharacter.getArmorClass());
        assertEquals(testCharacter.getFreeSkillPoints(), deserializedCharacter.getFreeSkillPoints());
        assertEquals(testCharacter.getAttributeModifier(AttributeType.ENDURANCE), deserializedCharacter.getAttributeModifier(AttributeType.ENDURANCE));
        assertEquals(testCharacter.getCharacterSkillDetails(SkillType.Survival).getSummaryValue(), deserializedCharacter.getCharacterSkillDetails(SkillType.Survival).getSummaryValue());
    }
}
