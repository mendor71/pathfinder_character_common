package com.mendor71.pathfinder.common.pathfinderclasses;

import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.Set;

public interface IClassManager {
    Set<CharacterClassDetails> getCharacterClasses();
    CharacterClassDetails getCharacterClassDetails(ClassType type);
    boolean containsCharacterClass(ClassType type);
    void levelUpAtClass(ClassType type);
    void addNewClassDetails(CharacterClassDetails classDetails);
    long getCharacterSummaryLevel();
    long getSummaryCharacterSkillPointsProvidedByClasses();
    void setClassesOnControl(Set<CharacterClassDetails> characterClassDetails);
    boolean isClassSkill(SkillType skillType);
    Set<SkillType> getClassSkills();
    public CharacterClassDetails getClassDetailsByTypeOrThrowException(ClassType type) throws IllegalStateException;
}
