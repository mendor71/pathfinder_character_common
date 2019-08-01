package com.mendor;

public class RandomRoll {
    public static long roll(long maxValue, long ... bonus) {
        long rollValue = 1 + (long) (Math.random() * (1 - maxValue));
        if (bonus != null) {
            for (long b : bonus)
                rollValue += b;
        }
        return rollValue;
    }
}
