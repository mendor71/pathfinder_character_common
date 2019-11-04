package com.mendor.pathfinder.common.types;

public enum  ClassType {
    FIGHTER, PALADIN, RANGER;

    public static ClassType byName(String name) {
        for (ClassType t: values()) {
            if (t.toString().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
