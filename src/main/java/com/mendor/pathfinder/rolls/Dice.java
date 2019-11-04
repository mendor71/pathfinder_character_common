package com.mendor.pathfinder.rolls;

public enum Dice {
    d2(1, 2, "d2")
    , d4(1, 4, "d4")
    , d6(1, 6, "d6")
    , d8(1, 8, "d8")
    , d10(1, 10, "d10")
    , d12(1, 12, "d12")
    , d20(1, 20, "d20")
    , d100(1, 100, "d100");

    long min;
    long max;
    String name;

    Dice(long min, long max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
