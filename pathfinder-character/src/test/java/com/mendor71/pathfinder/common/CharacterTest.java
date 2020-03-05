package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.attributes.CharacterAttributeDetails;
import com.mendor71.pathfinder.common.attributes.IAttributeManager;
import com.mendor71.pathfinder.common.attributes.PersonifiedAttributeManager;
import com.mendor71.pathfinder.common.exceptions.*;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnit4.class)
public class CharacterTest {

    private Character character;

    @Before
    public void before() throws CharacterSkillListIllegalStateException, CharacterSkillAlreadyExistsException, CharacterRaceNotSetException, RaceNotExiststException {
        CharacterBase characterBase = CharacterBase.newBuilder().setRace(RaceManager.get(Races.ELF)).build();

        IClassManager classManager = new PersonifiedClassManager(characterBase.getUuid());
        IAttributeManager attributeManager = new PersonifiedAttributeManager(characterBase.getUuid());
        ISkillManager skillManager = new PersonifiedSkillManager(characterBase.getUuid());

        Set<CharacterAttributeDetails> characterAttributeDetails = new HashSet<>();
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.INTELLIGENCE, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.AGILITY, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.CHARISMA, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.STRENGTH, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.WISDOM, 10));
        characterAttributeDetails.add(new CharacterAttributeDetails(AttributeType.ENDURANCE, 10));

        Set<CharacterClassDetails> characterClassDetails = new HashSet<>();
        characterClassDetails.add(new CharacterClassDetails(CharacterClass.getInstance(ClassType.PALADIN), 1));

        Set<CharacterSkillDetails> skillSet = new HashSet<>();
        SimpleSkillProvider skillProvider = SimpleSkillProvider.getInstance();

        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.Survival)));
        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.DisableDevice)));
        skillSet.add(new CharacterSkillDetails(skillProvider.getSkillByType(SkillType.Heal)));

        character = Character.newBuilder()
            .manageAttributes(attributeManager, characterAttributeDetails)
            .manageClasses(classManager, characterClassDetails)
            .manageSkills(skillManager, skillSet)
            .setCharacterBase(characterBase)
            .build();
    }

    @Test
    public void testCharacterUntrainedSkillsHasDefaultvalue0() {
        assertEquals(character.getSumarySkillValue(SkillType.Survival), 0);
        assertEquals(character.getSumarySkillValue(SkillType.Acrobatics), 0);
    }

    @Test
    public void testCharacterTrainClassSkillValueIsCorrect() throws NotEnoughSkillPointsException {
        character.trainSkill(SkillType.Heal, 1);
        assertEquals( character.getSumarySkillValue(SkillType.Heal), 4);
    }
}
