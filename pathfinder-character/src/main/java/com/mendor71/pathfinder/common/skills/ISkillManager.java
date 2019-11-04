package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.exceptions.NotEnoughSkillPointsException;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.Set;

public interface ISkillManager {
    void setClassSkills(Set<SkillType> classSkills);

    void setSkillsOnControl(Set<CharacterSkillDetails> skillDetails);

    CharacterSkillDetails getCharacterSkillDetails(SkillType type);

    Set<CharacterSkillDetails> getCharacterSkillDetailsSet();

    boolean containsCharacterSkill(SkillType type);

    long increaseSkillPoints(SkillType type, long value) throws NotEnoughSkillPointsException;

    long getUsedSkillPointsBySkill(SkillType type);

    long setSumSkillPoints(long value);

    long getFreeSkillPoints();

    long getUsedSkillPoints();

    long getSkillValue(SkillType type);

    long getSkillTrainedPoints(SkillType type);

    long increaseFreeSkillPoints(long value);

    CharacterSkillDetails getCharacterSkillDetailsByTypeOrThrowException(SkillType type) throws IllegalStateException;
}
