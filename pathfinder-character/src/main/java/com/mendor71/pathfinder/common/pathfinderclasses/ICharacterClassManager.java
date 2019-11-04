package com.mendor71.pathfinder.common.pathfinderclasses;

import com.mendor71.pathfinder.common.types.SkillType;
import com.mendor71.pathfinder.common.PathfinderCharacter;

import java.util.Set;

public interface ICharacterClassManager {
    Set<CharacterClassDetails> getCharacterClasses(PathfinderCharacter character);
    CharacterClassDetails getCharacterClassDetails(PathfinderCharacter character, ICharacterClass characterClass);
    boolean containsCharacterClass(PathfinderCharacter character, ICharacterClass characterClass);
    void levelUpAtClass(PathfinderCharacter pathfinderCharacter, ICharacterClass characterClass);
    void addCharacterClassDetails(PathfinderCharacter pathfinderCharacter, CharacterClassDetails characterClass);
    long getCharacterSummaryLevel(PathfinderCharacter character);
    long getSummaryCharacterSkillPointsProvidedByClasses(PathfinderCharacter character);
    void setOnControl(PathfinderCharacter pathfinderCharacter, Set<CharacterClassDetails> characterClassDetails);
    boolean isClassSkill(PathfinderCharacter pathfinderCharacter, SkillType skillType);
}
