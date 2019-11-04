package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.exceptions.NotEnoughSkillPointsException;
import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.PathfinderCharacter;

import java.util.Set;

public interface ICharacterSkillManager {

    void setOnControlClassSkills(PathfinderCharacter character);
    void addAttributeSkillListeners(PathfinderCharacter character, Set<CharacterSkillDetails> skillDetails);
    void setOnControl(PathfinderCharacter character, Set<CharacterSkillDetails> skillDetails);
    CharacterSkillDetails getCharacterSkillDetails(PathfinderCharacter character, SkillType type);
    Set<CharacterSkillDetails> getCharacterSkillDetailsSet(PathfinderCharacter character);
    boolean containsCharacterSkill(PathfinderCharacter character, SkillType type);
    long increaseSkillPoints(PathfinderCharacter character, SkillType type, long value) throws NotEnoughSkillPointsException;
    long increaseClassSkillPoints(PathfinderCharacter character, SkillType type);
    long getSkillPoints(PathfinderCharacter character, SkillType type);
    void setCharacterBaseSkillPoints(PathfinderCharacter character);
    void setFreeAndUsedSkillPoints(PathfinderCharacter character, long free, long used);
    long getFreeSkillPoints(PathfinderCharacter character);
    long getUsedSkillPoints(PathfinderCharacter character);
    long increaseFreeSkillPoints(PathfinderCharacter character, long value);
}
