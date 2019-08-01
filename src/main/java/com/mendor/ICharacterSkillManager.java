package com.mendor;

import com.mendor.exceptions.NotEnoughSkillPointsException;
import com.mendor.types.SkillType;

import java.util.Set;

public interface ICharacterSkillManager {

    void setOnControlClassSkills(PathfinderCharacter character);
    void addAttributeSkillListeners(PathfinderCharacter character, Set<CharacterSkillDetails> skillDetails);
    void setOnControl(PathfinderCharacter character, Set<CharacterSkillDetails> skillDetails);
    CharacterSkillDetails getCharacterSkillDetails(PathfinderCharacter character, SkillType type);
    Set<CharacterSkillDetails> getCharacterSkillDetailsSet(PathfinderCharacter character);
    boolean containsCharacterSkill(PathfinderCharacter character, SkillType type);
    long  increaseSkillPoints(PathfinderCharacter character, SkillType type, long value) throws NotEnoughSkillPointsException;
    long getSkillPoints(PathfinderCharacter character, SkillType type);

    void setCharacterBaseSkillPoints(PathfinderCharacter character);

    void setFreeAndUsedSkillPoints(PathfinderCharacter character, long free, long used);

    long getFreeSkillPoints(PathfinderCharacter character);
    long getUsedSkillPoints(PathfinderCharacter character);

    long increaseFreeSkillPoints(PathfinderCharacter character, long value);
}
