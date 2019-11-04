//package com.mendor71.pathfinder.common;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.mendor71.pathfinder.common.CharacterFactory;
//import com.mendor71.pathfinder.common.PathfinderCharacter;
//import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
//import com.mendor71.pathfinder.common.attributes.ICharacterAttributeManager;
//import com.mendor71.pathfinder.common.attributes.SimpleCharacterAttributeManager;
//import com.mendor71.pathfinder.common.exceptions.NotEnoughSkillPointsException;
//import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClass;
//import com.mendor71.pathfinder.common.pathfinderclasses.CharacterClassDetails;
//import com.mendor71.pathfinder.common.pathfinderclasses.ICharacterClassManager;
//import com.mendor71.pathfinder.common.pathfinderclasses.SimpleCharacterClassManager;
//import com.mendor71.pathfinder.common.races.HumanRace;
//import com.mendor71.pathfinder.common.skills.ICharacterSkillManager;
//import com.mendor71.pathfinder.common.skills.SimpleCharacterSkillManager;
//import com.mendor71.pathfinder.common.types.AttributeType;
//import com.mendor71.pathfinder.common.types.ClassType;
//import com.mendor71.pathfinder.common.types.SkillType;
//import com.mendor71.pathfinder.common.util.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import java.awt.*;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
//@RunWith(JUnit4.class)
//public class PathfinderCharacterTest {
//    private PathfinderCharacter testCharacter;
//
//    private ICharacterClassManager characterClassManager ;
//    private ICharacterAttributeManager characterAttributeManager;
//    private ICharacterSkillManager characterSkillManager;
//
//    private Set<CharacterClassDetails> classDetails;
//    private Set<CharacterAttributeDetails> attributeDetails;
//
//    private CharacterFactory characterFactory;
//
//    @Before
//    public void prepareUtils() {
//        characterClassManager = new SimpleCharacterClassManager();
//        characterAttributeManager = new SimpleCharacterAttributeManager();
//        characterSkillManager = new SimpleCharacterSkillManager();
//
//        classDetails = new HashSet<>();
//        attributeDetails = new HashSet<>();
//
//        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.FIGHTER), 4));
//        classDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.RANGER), 3));
//
//        attributeDetails.add(new CharacterAttributeDetails(AttributeType.ENDURANCE, 14));
//        attributeDetails.add(new CharacterAttributeDetails(AttributeType.STRENGTH, 16));
//        attributeDetails.add(new CharacterAttributeDetails(AttributeType.AGILITY, 12));
//        attributeDetails.add(new CharacterAttributeDetails(AttributeType.WISDOM, 10));
//        attributeDetails.add(new CharacterAttributeDetails(AttributeType.INTELLIGENCE, 10));
//        attributeDetails.add(new CharacterAttributeDetails(AttributeType.CHARISMA, 12));
//
//        characterFactory = new CharacterFactory(characterClassManager, characterAttributeManager, characterSkillManager);
//
//        testCharacter = characterFactory.newCharacter(new HumanRace(), classDetails, attributeDetails);
//
//        testCharacter.setName("Vadgast");
//        testCharacter.setAge(34);
//        testCharacter.setHeight(182);
//        testCharacter.setWeight(80);
//        testCharacter.setEyeColor(Color.GRAY);
//        testCharacter.setHairColor(Color.BLACK);
//    }
//
//    @Test
//    public void characterClassManagerTest() {
//        assertEquals(characterClassManager.getCharacterSummaryLevel(testCharacter), testCharacter.getLevel().longValue());
//        assertTrue(characterClassManager.containsCharacterClass(testCharacter, CharacterClass.getInstance(ClassType.FIGHTER)));
//        assertTrue(characterClassManager.containsCharacterClass(testCharacter, CharacterClass.getInstance(ClassType.RANGER)));
//    }
//
//    @Test
//    public void characterSkillManagerTest() throws NotEnoughSkillPointsException {
//        assertTrue(testCharacter.getSkillManager().containsCharacterSkill(testCharacter, SkillType.Survival));
//
//        assertEquals(testCharacter.getFreeSkillPoints(), 26);
//        assertEquals(testCharacter.getUsedSkillPoints(), 0);
//
//        assertEquals(testCharacter.getSkillPoints(SkillType.Survival).longValue(), 0);
//
//        testCharacter.increaseSkillPoints(SkillType.Survival, 5);
//        assertEquals(testCharacter.getSkillPoints(SkillType.Survival).longValue(), 8);
//
//        assertEquals(testCharacter.getFreeSkillPoints(), 21);
//        assertEquals(testCharacter.getUsedSkillPoints(), 5);
//
//        testCharacter.increaseSkillPoints(SkillType.DisableDevice, 4);
//        assertEquals(testCharacter.getSkillPoints(SkillType.DisableDevice).longValue(), 4);
//
//        assertEquals(testCharacter.getFreeSkillPoints(), 17);
//        assertEquals(testCharacter.getUsedSkillPoints(), 9);
//
//        testCharacter.increaseSkillPoints(SkillType.DisableDevice, 17);
//        assertEquals(testCharacter.getFreeSkillPoints(), 0);
//        assertEquals(testCharacter.getUsedSkillPoints(), 26);
//        assertEquals(testCharacter.getSkillPoints(SkillType.DisableDevice).longValue(), 21);
//    }
//
//    @Test(expected = NotEnoughSkillPointsException.class)
//    public void characterSkillManagerUsePointsMoreThanFreeException() throws NotEnoughSkillPointsException {
//        testCharacter.increaseSkillPoints(SkillType.Survival, 90);
//    }
//
//    @Test
//    public void characterAttributeManagerTest() {
//        assertEquals(testCharacter.getAttributeModifier(AttributeType.STRENGTH), 3);
//        testCharacter.getAttributeManager().increaseAttributeValue(testCharacter, AttributeType.STRENGTH, 4);
//        assertEquals(testCharacter.getAttributeModifier(AttributeType.STRENGTH), 5);
//    }
//}
