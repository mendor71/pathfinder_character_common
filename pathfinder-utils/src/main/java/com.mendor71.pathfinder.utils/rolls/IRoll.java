package com.mendor71.pathfinder.utils.rolls;

public interface IRoll {
    float roll(int times, long criteriaValue);
    float roll(int times, long criteriaValue, long bonus);
    long roll();
}
