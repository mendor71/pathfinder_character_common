package com.mendor71.pathfinder.common.skills;

import com.mendor71.pathfinder.common.exceptions.CharacterSkillAlreadyExistsException;
import com.mendor71.pathfinder.common.exceptions.CharacterSkillListIllegalStateException;
import com.mendor71.pathfinder.common.exceptions.NotEnoughSkillPointsException;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.Set;

public interface ISkillManager {
    void setClassSkills(Set<SkillType> classSkills);

    void setSkillsOnControl(Set<CharacterSkillDetails> skillDetails);

    void addCharacterSkill(SkillType type, long trainedPoints) throws CharacterSkillAlreadyExistsException;

    void addCharacterSkill(SkillType type) throws CharacterSkillAlreadyExistsException;

    CharacterSkillDetails getCharacterSkillDetails(SkillType type) throws CharacterSkillListIllegalStateException;

    Set<CharacterSkillDetails> getCharacterSkillDetailsSet();

    boolean containsCharacterSkill(SkillType type);

    long setSumSkillPoints(long value);

    long getFreeSkillPoints();

    long getUsedSkillPoints();

    long increaseFreeSkillPoints(long value);

    long trainSkill(SkillType type, long value) throws NotEnoughSkillPointsException;

    long increaseSkillStableBonusValue(SkillType type, long value) throws CharacterSkillListIllegalStateException;

    long decreaseSkillStableBonusValue(SkillType type, long value) throws CharacterSkillListIllegalStateException;

    long increaseSkillTemporaryModifierValue(SkillType type, long value) throws CharacterSkillListIllegalStateException;

    long decreaseSkillTemporaryModifierValue(SkillType type, long value) throws CharacterSkillListIllegalStateException;

    void resetSkillTemporaryModifierValue(SkillType type) throws CharacterSkillListIllegalStateException;

    long getSumarySkillValue(SkillType type);

    long getSkillTrainedPoints(SkillType type);

    long getSkillStableBonusValue(SkillType type);

    long getSkillTemporaryModiferValue(SkillType type);

    CharacterSkillDetails getCharacterSkillDetailsByTypeOrThrowException(SkillType type) throws IllegalStateException, CharacterSkillListIllegalStateException;
}
