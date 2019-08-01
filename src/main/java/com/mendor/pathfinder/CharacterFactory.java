package com.mendor.pathfinder;

import com.mendor.pathfinder.attributes.CharacterAttributeDetails;
import com.mendor.pathfinder.attributes.ICharacterAttributeManager;
import com.mendor.pathfinder.pathfinderclasses.CharacterClassDetails;
import com.mendor.pathfinder.pathfinderclasses.ICharacterClassManager;
import com.mendor.pathfinder.races.IRace;
import com.mendor.pathfinder.skills.ICharacterSkillManager;

import java.util.Set;

public class CharacterFactory {
    private ICharacterClassManager classManager;
    private ICharacterAttributeManager attributeManager;
    private ICharacterSkillManager skillManager;

    public CharacterFactory(ICharacterClassManager classManager, ICharacterAttributeManager attributeManager, ICharacterSkillManager skillManager) {
        this.classManager = classManager;
        this.attributeManager = attributeManager;
        this.skillManager = skillManager;
    }

    public PathfinderCharacter newCharacter(IRace race
            , Set<CharacterClassDetails> characterClassDetails
            , Set<CharacterAttributeDetails> characterAttributeDetails) {
        PathfinderCharacter character = new PathfinderCharacter();

        character.setSkillManager(skillManager);
        character.setClassManager(classManager);
        character.setAttributeManager(attributeManager);

        classManager.setOnControl(character, characterClassDetails);
        attributeManager.setOnControl(character, characterAttributeDetails);
        skillManager.setCharacterBaseSkillPoints(character);
        skillManager.setOnControlClassSkills(character);

        character.setRace(race);
        character.setLevel(classManager.getCharacterSummaryLevel(character));
        character.setStartHitPoints();

        return character;
    }
}
