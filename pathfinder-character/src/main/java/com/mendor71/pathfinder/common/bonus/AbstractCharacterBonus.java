package com.mendor71.pathfinder.common.bonus;

public class AbstractCharacterBonus {
    protected long value;
    protected boolean temporary;

    public AbstractCharacterBonus(long value, boolean temporary) {
        this.value = value;
        this.temporary = temporary;
    }
}
