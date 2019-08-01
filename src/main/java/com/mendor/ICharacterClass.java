package com.mendor;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;
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
