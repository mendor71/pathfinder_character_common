package com.mendor71.pathfinder.utils.rolls;

public class AccuracyRoll extends AbstractRoll {
    public AccuracyRoll() {
        this(Dice.d20);
    }

    private AccuracyRoll(Dice dice) {
        super(dice);
    }
}
