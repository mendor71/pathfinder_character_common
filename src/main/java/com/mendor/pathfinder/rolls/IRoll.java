package com.mendor.pathfinder.rolls;

public interface IRoll {
    float roll(int times, long criteriaValue);
    float roll(int times, long criteriaValue, long bonus);
    long roll();
}
