package com.mendor71.pathfinder.utils.rolls;

public abstract class AbstractRoll implements IRoll {
    private Dice dice;

    public AbstractRoll(Dice dice) {
        this.dice = dice;
    }

    @Override
    public long roll() {
        return 1 + (long) (Math.random() * (dice.max));
    }

    @Override
    public float roll(int times, long criteriaValue) {
        return roll(times, criteriaValue, 0);
    }

    @Override
    public float roll(int times, long criteriaValue, long bonus) {
        float success = 0;
        for (int i = 0; i < times; i++) {
            long result = (bonus + 1 + (long) (Math.random() * (dice.max)));
            if (criteriaValue <= result) {
                success ++;
            }
        }
        return success / times;
    }
}
