package com.mendor71.pathfinder.common.pathfinderclasses;

import com.mendor71.pathfinder.common.skills.CharacterSkill;
import com.mendor71.pathfinder.common.types.ClassType;
import com.mendor71.pathfinder.common.types.SkillType;

import java.util.Set;

public interface ICharacterClass {
    Long getId();
    String getName();
    ClassType getType();
    long getMaxHitDiceValue();
    long getMinHitDiceValue();
    String getHitDiceName();
    long getSkillPointsByLevel();
    Set<SkillType> getClassSkills();

    default long rollHitPointsBonus(long level) {
        if (level == 1)
            return getMinHitDiceValue();
        else
            return getMinHitDiceValue() + (long) (Math.random() * (getMaxHitDiceValue() - getMinHitDiceValue()));
    }
}
