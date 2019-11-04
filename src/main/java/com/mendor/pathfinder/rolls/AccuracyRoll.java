package com.mendor.pathfinder.rolls;

public class AccuracyRoll extends AbstractRoll {
    public AccuracyRoll() {
        this(Dice.d20);
    }

    private AccuracyRoll(Dice dice) {
        super(dice);
    }
}
