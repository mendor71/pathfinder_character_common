package com.mendor71.pathfinder.common.bonus;

import com.mendor71.pathfinder.common.Character;

public class DescribedCharacterBonus implements ICharacterBonus {
    private String value;

    public DescribedCharacterBonus(String value) {
        this.value = value;
    }

    @Override
    public void apply(Character character) {
        character.addBonusDescription(value);
    }
}
