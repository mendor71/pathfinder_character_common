package com.mendor.pathfinder.common.pathfinderclasses;

import com.mendor.pathfinder.common.skills.CharacterSkill;

import java.util.Set;

public interface ICharacterClass {
    Long getId();
    String getName();
    long getMaxHitDiceValue();
    long getMinHitDiceValue();
    String getHitDiceName();
    long getSkillPointsByLevel();
    Set<CharacterSkill> getClassSkills();

    default long rollHitPointsBonus(long level) {
        if (level == 1)
            return getMinHitDiceValue();
        else
            return getMinHitDiceValue() + (long) (Math.random() * (getMaxHitDiceValue() - getMinHitDiceValue()));
    }
}
